package uz.androbeck.virtualbank.ui

import android.os.Bundle
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

                null -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    override fun onPause() {
        super.onPause()
        mainSharedVM.saveAwayLong()
    }

    override fun onResume() {
        super.onResume()
        mainSharedVM.checkIsAwayLong()
        mainSharedVM.isAwayLong.onEach {
            if (it == true) {
                mainSharedVM.setNavGraphEvent(NavGraphEvent.PinCode)
            }
        }.launchIn(lifecycleScope)
    }
}