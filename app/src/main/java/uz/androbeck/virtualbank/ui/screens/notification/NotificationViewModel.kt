package uz.androbeck.virtualbank.ui.screens.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationUIModel
import uz.androbeck.virtualbank.domain.ui_models.notification.toNotificationTransferUIModel
import uz.androbeck.virtualbank.domain.useCases.history.LastTransfersUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.screens.notification.events.NotificationTransferUiEvent
import uz.androbeck.virtualbank.ui.screens.notification.events.NotificationUiEvent
import uz.androbeck.virtualbank.utils.Constants
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val lastTransfersUseCase: LastTransfersUseCase,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _lastTransfers =
        MutableLiveData<NotificationTransferUiEvent>(NotificationTransferUiEvent.Loading)
    val lastTransfers: LiveData<NotificationTransferUiEvent> get() = _lastTransfers

    private val _notifications = MutableLiveData<List<NotificationUIModel>>()
    val notifications: LiveData<List<NotificationUIModel>> get() = _notifications

    private val _uiEvent = MutableStateFlow<NotificationUiEvent>(NotificationUiEvent.Loading)
    val uiEvent: StateFlow<NotificationUiEvent> get() = _uiEvent.asStateFlow()

    fun getTransfers() {
        viewModelScope.launch {
            lastTransfersUseCase.invoke()
                .onEach { model ->
                    val updatedModel = model.map { it.toNotificationTransferUIModel() }
                    _lastTransfers.value = NotificationTransferUiEvent.Success(updatedModel)
                }
                .catch { e ->
                    errorHandler.handleError(e)
                }
                .launchIn(this)
        }
    }

    fun fetchNotifications() {
        val database =
            FirebaseDatabase.getInstance(Constants.Firebase.FIREBASE_REAL_TIME_DATABASE_URL).reference.child(Constants.Firebase.FIREBASE_NOTIFICATION)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val notifications = mutableListOf<NotificationUIModel>()
                for (dataSnapshot in snapshot.children) {
                    val notification = dataSnapshot.getValue(NotificationUIModel::class.java)
                    notification?.let { notifications.add(it) }
                }
                _notifications.value = notifications
                _uiEvent.value = NotificationUiEvent.Success(notifications)
            }

            override fun onCancelled(error: DatabaseError) {
                _uiEvent.value = NotificationUiEvent.Error(error.toException())
            }
        })
    }
}