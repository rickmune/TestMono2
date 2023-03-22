package co.deltapay.home.ui.integrations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.deltapay.common.theme.ComposeTheme
import co.deltapay.common.utils.composeView
import co.deltapay.home.R
import mono.connect.kit.ConnectEvent
import mono.connect.kit.ConnectKit
import mono.connect.kit.ConnectedAccount
import mono.connect.kit.Mono
import mono.connect.kit.MonoConfiguration

class MonoFragment : Fragment() {

    private lateinit var mConnectKit: ConnectKit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme{
            try {
                monoConfig()
            } catch (e: Exception){e.printStackTrace()}
        }
    }

    private fun monoConfig() {
        println("MonoFragment monoConfig")
        val key = this.getString(R.string.connect_public_key)
        val config: MonoConfiguration = MonoConfiguration.Builder(
            requireContext(),
            key
        ) { code: ConnectedAccount -> println("Successfully linked account. Code: " + code.code) }
            .addReference("test")
            .addOnEvent { event: ConnectEvent ->
                println("Triggered: " + event.eventName)
                if (event.data.has("reference")) {
                    println("ref: " + event.data.getString("reference"))
                }
            }
            .addOnClose { findNavController().navigateUp() }
            .build()
        mConnectKit = Mono.create(config)
        mConnectKit.show()
    }

}