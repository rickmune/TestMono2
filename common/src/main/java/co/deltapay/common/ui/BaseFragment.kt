package co.deltapay.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import co.deltapay.common.theme.ComposeTheme
import co.deltapay.common.utils.composeView

abstract class BaseFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        ComposeTheme {
            setContent()
        }
    }

    @Composable
    abstract fun setContent(): Unit

    @Composable
    open fun openDialog(isShow: Boolean, title: String, message: String, onDismiss: () -> Unit) {
        if (isShow) {
            DialogMessage(
                title = title,
                message = message,
                onDismiss = {
                    onDismiss()
                })
        }
    }

    @Composable
    open fun showToast(message: String, duration: Int ) {
        Toast.makeText(
            context,
            message,
            duration
        ).show()
    }
}