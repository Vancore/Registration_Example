package com.example.registrationzkb.screens.registration

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registrationzkb.screens.shared.ZKBDivider
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme

@Composable
fun RegistrationTopBar(title: String) {
    Column(modifier = Modifier.padding(top = 48.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 24.dp),
            color = MaterialTheme.colors.onSurface
        )

        ZKBDivider()
    }
}

@Preview(
    name = "Registration Top Bar - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Registration Top Bar - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun RegistrationTopBarPreview() {
    RegistrationZKBTheme {
        RegistrationTopBar(title = "Registration")
    }
}
