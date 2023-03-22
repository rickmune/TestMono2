package co.deltapay.home.ui

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.deltapay.common.ui.BaseFragment
import co.deltapay.home.ui.home.HomeViewModel
import co.deltapay.home.ui.home.PendingScreen
import co.deltapay.home.ui.home.PendingState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    var status = "DONE"
    private val homeViewModel: HomeViewModel by viewModels()

    private val _scope = CoroutineScope(Job() + Dispatchers.IO)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            status = it.getString("status").toString()
        }
    }

    @Composable
    override fun setContent() {
        println("HomeFragment setContent ")
        when (homeViewModel.pending.collectAsState().value) {
            PendingState.GoHome -> {
                status = "DONE"
                startUpHome()
            }
            PendingState.Mono -> {
                startMono()
            }
            else -> {
            }
        }
        homeViewModel.getProfileStatus()
        if (status == "PENDING") {
            PendingScreen()
        } else {
            startUpHome()
        }
    }
    @Composable
    private fun startMono() {
        findNavController().navigate(HomeFragmentDirections.toMonoFragment())
    }

    @Composable
    fun startUpHome() {

    }


}
