package uz.androbeck.virtualbank.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ActivityMainBinding
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // I check this commit

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()
    private val mainSharedVM: MainSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        mainSharedVM.setNavGraphEvent()
        setupObservers(navHostFragment)
    }

    private fun setupObservers(navHostFragment: NavHostFragment) {
        mainSharedVM.navGraphEvent.onEach { event ->
            when (event) {
                NavGraphEvent.Auth -> {
                    navHostFragment.navController.setGraph(R.navigation.auth_nav_graph)
                    binding.bottomNavigation.gone()
                }

                NavGraphEvent.Main -> {
                    navHostFragment.navController.setGraph(R.navigation.main_nav_graph)
                    binding.bottomNavigation.visible()
                }

                NavGraphEvent.PinCode -> {
                    navHostFragment.navController.setGraph(R.navigation.pin_code_nav_graph)
                    binding.bottomNavigation.gone()
                }
                NavGraphEvent.Security -> {
                    navHostFragment.navController.setGraph(R.navigation.security_nav_graph)
                    binding.bottomNavigation.gone()
                }

                null -> Unit
            }
        }.launchIn(lifecycleScope)
    }
    @SuppressLint("CommitPrefEdits")
    override fun onStart() {
        super.onStart()
        val currentTime=System.currentTimeMillis()
        val sharedPreferences = getSharedPreferences("secure_shared_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putLong("current_time",currentTime)
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences("secure_shared_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("last_exit_time", System.currentTimeMillis())
        editor.apply()
    }

}