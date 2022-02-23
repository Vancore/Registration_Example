package com.example.registrationzkb.screens.shared

import android.content.res.Configuration
import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.example.registrationzkb.utils.Utils

@Composable
fun ZKBDivider() {
    Divider(
        color = MaterialTheme.colors.onSurface,
        thickness = 0.5.dp,
        modifier = Modifier
            .alpha(0.7f)
            .padding(vertical = 16.dp)
    )
}

@Composable
fun ZKBCalendarView(datePicked: (Int, Int, Int) -> Unit) {
    AndroidView({
        val calendarView = CalendarView(it)
        calendarView.maxDate = Utils.maximumCalendarTime().time
        calendarView
    },
        Modifier.wrapContentSize(),
        update = { view ->
            view.setOnDateChangeListener { _, year, month, day ->
                datePicked(year, month, day)
            }
        }
    )
}

@Composable
fun DefaultTopBar(title: String) {
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
        DefaultTopBar(title = "Registration")
    }
}