package com.example.newsapp.presentation.components

import android.R.attr.tint
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.newsapp.R
import com.example.newsapp.ui.theme.LightPrimary


@Composable
fun LikeCustomButton(
    onClickAdd: () -> Unit,
    onClickDelete: () -> Unit,
    isFav: Boolean
) {
    var isClicked by rememberSaveable { mutableStateOf(isFav) }

    IconButton(
        onClick = {
            if (isFav) {
                isClicked = false
                onClickDelete.invoke()
            } else {
                isClicked = true
                onClickAdd.invoke()
            }
        }
    ) {
        Icon(
            modifier = Modifier.size(160.dp),
            painter = if (!isClicked) painterResource(id = R.drawable.stars)
            else painterResource(id = R.drawable.baseline_star_24),

            contentDescription = null,
            tint = LightPrimary,
        )
    }
}
