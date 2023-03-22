package co.deltapay.customer

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import co.deltapay.onboarding.ui.login.SplashState
import co.deltapay.onboarding.ui.login.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import co.deltapay.home.R as HomeR
import co.deltapay.onboarding.R as OnboardingR

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    private var contentHasLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_graph) as NavHostFragment
        val navController = navHostFragment.navController
        val mainGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        val onBoardingGraph = navController.navInflater.inflate(OnboardingR.navigation.onboarding_navigation)
        val homeGraph = navController.navInflater.inflate(HomeR.navigation.home_navigation_graph)

        // Main graph should have all destinations
        mainGraph.addAll(onBoardingGraph)
        mainGraph.addAll(homeGraph)

        lifecycleScope.launch {
            splashViewModel.state.flowWithLifecycle(lifecycle).collect { state ->
                when (state) {
                    SplashState.Log -> {
                        mainGraph.setStartDestination(OnboardingR.id.loginFragment)
                    }
                    SplashState.Reg -> {
                        //mainGraph.setStartDestination(OnboardingR.id.welcomeFragment)
                    }
                    SplashState.Idle -> {}
                }

                navController.graph = mainGraph
                contentHasLoaded = true
            }
        }

        setupSplashScreen(splashScreen)
    }

    private fun setupSplashScreen(splashScreen: SplashScreen) {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (contentHasLoaded) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false
                }
            }
        )
    }
}
