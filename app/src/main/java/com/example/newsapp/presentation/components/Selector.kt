package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selector(
    list: List<String>,
    mapList: Map<String, String>,
    selector: String,
    onListSelector: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var currentCountry by rememberSaveable { mutableStateOf<String>(selector) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = currentCountry,
            onValueChange = {
                currentCountry = it
                onListSelector(it)
            },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(start = 5.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = LightPrimary,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            list.forEach { country ->
                DropdownMenuItem(
                    text = { Text(country, fontSize = 20.sp) },
                    onClick = {
                        currentCountry = mapList.getValue(country).uppercase()
                        onListSelector(country)
                        expanded = false
                    }
                )
            }
        }
    }

}