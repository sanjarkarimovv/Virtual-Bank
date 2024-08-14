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
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.utils.extentions.getLanguageByCode
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    @Inject
    lateinit var preferencesProvider: PreferencesProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        changeLanguage()
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        println("onCreate:App")
        vm.setNavGraphEvent()
        setupObservers(navHostFragment)
    }

    fun changeLanguage() {
        val language = preferencesProvider.language.getLanguageByCode()
        val config = resources.configuration
        val locale = Locale(language.code)
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun setupObservers(navHostFragment: NavHostFragment) {
        vm.observeNavGraphEvent().onEach { event ->
                when (event) {
                    NavGraphEvent.Auth -> {
                        val authGraph = navHostFragment.navController.navInflater.inflate(R.navigation.auth_nav_graph)
                        authGraph.setStartDestination(R.id.chooseLanguageFragment)
                        defaultNavHostTrue(navHostFragment)
                        navHostFragment.navController.graph = authGraph
                        binding.bottomNavigation.gone()
                    }

                    NavGraphEvent.Main -> {
                        val mainGraph = navHostFragment.navController.navInflater.inflate(R.navigation.main_nav_graph)
                        mainGraph.setStartDestination(R.id.mainFragment)
                        defaultNavHostTrue(navHostFragment)
                        navHostFragment.navController.graph = mainGraph
                        binding.bottomNavigation.visible()
                    }

                    NavGraphEvent.PinCode -> {
                        val pinCodeGraph = navHostFragment.navController.navInflater.inflate(R.navigation.pin_code_nav_graph)
                        pinCodeGraph.setStartDestination(R.id.pinCodeFragment)
                        defaultNavHostTrue(navHostFragment)
                        navHostFragment.navController.graph = pinCodeGraph
                        binding.bottomNavigation.gone()
                    }
                }
        }.launchIn(lifecycleScope)
    }

    private fun defaultNavHostTrue(navHostFragment: NavHostFragment) {
        supportFragmentManager
            .beginTransaction()
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }
}