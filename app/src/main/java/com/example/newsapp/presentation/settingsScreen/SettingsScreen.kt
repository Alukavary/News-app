package com.example.newsapp.presentation.settingsScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.components.Title
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightPrimary

@Preview(showBackground = true)
@Composable
fun SettingsScreen() {
    var checked by rememberSaveable { mutableStateOf<Boolean>(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp, bottom = 70.dp)
    ) {
        Column {
            Title("Settings")
            Spacer(Modifier.padding(bottom = 20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(10.dp)
                    )

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Dark mood",
                        fontSize = 25.sp,
                    )
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }, colors = SwitchDefaults.colors(
                            checkedThumbColor = LightPrimary,
                            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        )
                    )
                }

            }
        }

    }
}