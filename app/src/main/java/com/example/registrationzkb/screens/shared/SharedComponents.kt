package com.example.registrationzkb.screens.shared

import android.content.res.Configuration
import android.widget.CalendarView
import android.widget.DatePicker
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
import java.util.*

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
fun ZKBCalendarView(
    lastPickedDate: Date,
    datePicked: (Int, Int, Int) -> Unit
) {
    AndroidView({
        val datePicker = DatePicker(it)
        datePicker.maxDate = Utils.maximumCalendarTime().time
        datePicker.updateDate(lastPickedDate.year + 1900, lastPickedDate.month, lastPickedDate.date)
        datePicker
    },
        Modifier.wrapContentSize(),
        update = { view ->
            view.setOnDateChangedListener { _, year, month, day ->
                if(day != 31) datePicked(year, month, day)
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