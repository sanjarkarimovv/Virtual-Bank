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
        mainSharedVM.observeNavGraphEvent().onEach { event ->
            println("::EVENT $event")
            navHostFragment.navController.apply {
                when (event) {
                    NavGraphEvent.Auth -> {
                        val authGraph = navInflater.inflate(R.navigation.auth_nav_graph)
                        defaultNavHostTrue(navHostFragment)
                        authGraph.setStartDestination(R.id.chooseLanguageFragment)
                        graph = authGraph
                        binding.bottomNavigation.gone()
                    }

                    NavGraphEvent.Main -> {
                        val mainGraph = navInflater.inflate(R.navigation.main_nav_graph)
                        defaultNavHostTrue(navHostFragment)
                        mainGraph.setStartDestination(R.id.mainFragment)
                        graph = mainGraph
                        binding.bottomNavigation.visible()
                    }

                    NavGraphEvent.PinCode -> {
                        val pinCodeGraph = navInflater.inflate(R.navigation.pin_code_nav_graph)
                        defaultNavHostTrue(navHostFragment)
                        pinCodeGraph.setStartDestination(R.id.pinCodeFragment)
                        graph = pinCodeGraph
                        binding.bottomNavigation.gone()
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun defaultNavHostTrue(navHostFragment: NavHostFragment) {
        supportFragmentManager.beginTransaction().setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }
}