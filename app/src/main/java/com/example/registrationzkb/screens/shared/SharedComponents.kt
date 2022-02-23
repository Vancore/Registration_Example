package com.example.registrationzkb.screens.shared

import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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