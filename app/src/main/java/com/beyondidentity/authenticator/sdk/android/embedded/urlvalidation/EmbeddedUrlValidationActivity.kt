package com.beyondidentity.authenticator.sdk.android.embedded.urlvalidation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.beyondidentity.authenticator.sdk.android.composeui.components.BiAppBar
import com.beyondidentity.authenticator.sdk.android.composeui.components.BiDivider
import com.beyondidentity.authenticator.sdk.android.composeui.components.BiVersionText
import com.beyondidentity.authenticator.sdk.android.composeui.components.InteractionResponseInputView
import com.beyondidentity.authenticator.sdk.android.composeui.theme.BiSdkAndroidTheme

class EmbeddedUrlValidationActivity : FragmentActivity() {
    private val viewModel: EmbeddedUrlValidationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BiSdkAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(topBar = {
                        BiAppBar()
                    }) {
                        EmbeddedUrlValidationScreen(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun EmbeddedUrlValidationScreen(viewModel: EmbeddedUrlValidationViewModel) {
    EmbeddedUrlValidationLayout(
        state = viewModel.state,
        onUrlValidationBindCredentialUrlTextChange = viewModel::onUrlValidationBindCredentialUrlTextChange,
        onValidateBindCredentialUrl = viewModel::onValidateBindCredentialUrl,
        onUrlValidationAuthenticateUrlTextChange = viewModel::onUrlValidationAuthenticateUrlTextChange,
        onValidateAuthenticateUrl = viewModel::onValidateAuthenticateUrl,
    )
}

@Composable
fun EmbeddedUrlValidationLayout(
    state: EmbeddedUrlValidationState,
    onUrlValidationBindCredentialUrlTextChange: (String) -> Unit,
    onValidateBindCredentialUrl: () -> Unit,
    onUrlValidationAuthenticateUrlTextChange: (String) -> Unit,
    onValidateAuthenticateUrl: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
    ) {
        Text(
            text = "URL Validation",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        )

        BiVersionText()

        BiDivider(modifier = Modifier.padding(top = 32.dp))

        UrlValidationLayout(
            state,
            onUrlValidationBindCredentialUrlTextChange,
            onValidateBindCredentialUrl,
            onUrlValidationAuthenticateUrlTextChange,
            onValidateAuthenticateUrl,
        )
    }
}

@Composable
fun UrlValidationLayout(
    state: EmbeddedUrlValidationState,
    onUrlValidationBindCredentialUrlTextChange: (String) -> Unit,
    onValidateBindCredentialUrl: () -> Unit,
    onUrlValidationAuthenticateUrlTextChange: (String) -> Unit,
    onValidateAuthenticateUrl: () -> Unit,
) {
    Text(
        text = "Bind Credential URL",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = 32.dp),
    )

    InteractionResponseInputView(
        description = "Paste a Url here to validate if it's a bind credential url.",
        inputValue = state.urlValidationBindCredentialUrl,
        inputHint = "Bind Credential URL",
        inputTestTag = "Validate Bind Credential URL Input",
        onInputChanged = onUrlValidationBindCredentialUrlTextChange,
        buttonText = "Validate Url",
        testTag = "Validate Bind Credential URL",
        onSubmit = onValidateBindCredentialUrl,
        submitResult = state.validateBindCredentialUrlResult,
    )

    BiDivider(modifier = Modifier.padding(top = 32.dp))

    Text(
        text = "Authenticate URL",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = 32.dp),
    )

    InteractionResponseInputView(
        description = "Paste a Url here to validate if it's an authenticate url.",
        inputValue = state.urlValidationAuthenticateUrl,
        inputHint = "Authenticate URL",
        inputTestTag = "Validate Authenticate URL Input",
        onInputChanged = onUrlValidationAuthenticateUrlTextChange,
        buttonText = "Validate Url",
        testTag = "Validate Authenticate URL",
        onSubmit = onValidateAuthenticateUrl,
        submitResult = state.validateAuthenticateUrlResult,
    )
}

@Composable
@Preview(showBackground = true)
fun UrlValidationPreview() {
    BiSdkAndroidTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp),
        ) {
            UrlValidationLayout(
                EmbeddedUrlValidationState(),
                {},
                {},
                {},
                {},
            )
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
fun EmbeddedUrlValidationPreview() {
    BiSdkAndroidTheme {
        EmbeddedUrlValidationLayout(
            EmbeddedUrlValidationState(),
            {},
            {},
            {},
            {},
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun EmbeddedUrlValidationPreviewDark() {
    BiSdkAndroidTheme {
        EmbeddedUrlValidationLayout(
            EmbeddedUrlValidationState(),
            {},
            {},
            {},
            {},
        )
    }
}
