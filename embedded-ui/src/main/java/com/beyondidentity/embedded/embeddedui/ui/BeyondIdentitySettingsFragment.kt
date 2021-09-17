package com.beyondidentity.embedded.embeddedui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.Group
import com.beyondidentity.embedded.embeddedui.R
import com.beyondidentity.embedded.embeddedui.ui.BeyondIdentityCredentialInfoFragment.CredentialInfoListener
import com.beyondidentity.embedded.sdk.EmbeddedSdk

class BeyondIdentitySettingsFragment : BiBaseBottomSheetDialogFragment(), CredentialInfoListener {
    private lateinit var noCredGroup: Group
    private lateinit var credPresentGroup: Group

    private lateinit var addCred: AppCompatTextView
    private lateinit var showCred: AppCompatTextView
    private lateinit var showQrCode: LinearLayoutCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_bi_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        checkCredOnDevice()
    }

    private fun setupViews(view: View) {
        noCredGroup = view.findViewById(R.id.no_cred_group)
        credPresentGroup = view.findViewById(R.id.cred_present_group)

        addCred = view.findViewById(R.id.settings_add_credential)
        showCred = view.findViewById(R.id.settings_view_credential)
        showQrCode = view.findViewById(R.id.show_qr_container)

        addCred.setOnClickListener {
        }

        showCred.setOnClickListener {
            val credInfoFragment =
                BeyondIdentityCredentialInfoFragment
                    .newInstance()
            credInfoFragment.credentialInfoListener = this
            credInfoFragment.show(parentFragmentManager, BeyondIdentityCredentialInfoFragment.TAG)
        }

        showQrCode.setOnClickListener {
            EmbeddedSdk.getCredentials { result ->
                result.onSuccess { credList ->
                    if (credList.isNotEmpty()) {
                        BiShowQrCodeFragment
                            .newInstance(credList[0].handle)
                            .show(parentFragmentManager, BiShowQrCodeFragment.TAG)
                    }
                }
                result.onFailure {
                    Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkCredOnDevice() {
        EmbeddedSdk.getCredentials { result ->
            result.onSuccess { credList ->
                if (credList.isEmpty()) {
                    noCredGroup.visible()
                    credPresentGroup.gone()
                } else {
                    noCredGroup.gone()
                    credPresentGroup.visible()
                }
            }
        }
    }

    override fun onCredDeleted() {
        checkCredOnDevice()
    }

    companion object {
        const val TAG = "bi-settings-fragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment [BeyondIdentitySettingsFragment].
         */
        @JvmStatic
        fun newInstance() = BeyondIdentitySettingsFragment()
    }
}
