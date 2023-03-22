package co.deltapay.onboarding.ui.login

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.UssdResponseCallback
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import co.deltapay.common.ui.BaseFragment
import co.deltapay.common.ui.DialogMessage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment: BaseFragment() {

    private val authViewModel: AuthViewModel by activityViewModels()
    private val MY_PERMISSIONS_REQUEST_READ_MEDIA: Int = 1000

    @Composable
    override fun setContent() {
        var state = authViewModel.loginState.collectAsState().value
        val passOnly = remember { mutableStateOf(false) }
        when(state) {
            is LoginStates.Success -> {
                val uri = Uri.parse("myApp://homeFragment?status=${state.status}")
                findNavController().navigate(uri)
            }
            is LoginStates.Error -> {
                DialogMessage(title = "Something went wrong", message = state.msg) {
                    
                }
            }
            is LoginStates.HasEmail -> {
                passOnly.value = true
            }
            else -> {}
        }
        LoginScreen(
            state = state,
            onRegisterPress = {

            },
            onLoginClicked = { username, password ->
                authViewModel
                    .actionPerformed(LoginActions.LoginClicked(username, password))
            }
        ) {
            // TODO: forgot password
        }

        findNavController().enableOnBackPressed(true)

        val permissionCheck: Int  = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                MY_PERMISSIONS_REQUEST_READ_MEDIA
            );
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ussdCall()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            MY_PERMISSIONS_REQUEST_READ_MEDIA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ussdCall()
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ussdCall() {
        val responseCallback: UssdResponseCallback = @RequiresApi(Build.VERSION_CODES.O)
        object : UssdResponseCallback() {
            override fun onReceiveUssdResponse(
                telephonyManager: TelephonyManager,
                request: String,
                response: CharSequence
            ) {
                super.onReceiveUssdResponse(telephonyManager, request, response)
                Log.d("LoginFragment", "onReceiveUssdResponse: ${response.toString()}")
                Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onReceiveUssdResponseFailed(
                telephonyManager: TelephonyManager,
                request: String,
                failureCode: Int
            ) {
                super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode)
                Log.d("LoginFragment", "onReceiveUssdResponseFailed: ${failureCode.toString()}")
                Toast.makeText(requireContext(), failureCode.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        val handler = Handler(Looper.getMainLooper())
        val tm = requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager;
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        tm.sendUssdRequest("*100${Uri.encode("#")}", responseCallback, handler)
    }
}