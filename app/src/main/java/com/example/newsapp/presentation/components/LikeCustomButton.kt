package com.example.newsapp.presentation.components

import android.R.attr.tint
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.newsapp.R
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.detailsScreen.DetailsVM
import com.example.newsapp.ui.theme.LightPrimary
import javax.inject.Inject


@Composable
fun LikeCustomButton(
   viewModel: DetailsVM = hiltViewModel(),
   isFav: Boolean,
   data: ArticleModel
) {
    Log.d("MyLog", "isFav $isFav")

    var isClicked by rememberSaveable { mutableStateOf(isFav) }
    IconButton(
        onClick = {
            if (isFav) {
                viewModel.resetIsFavoriteOrNot(data, false)
                isClicked = false

            } else {
                viewModel.resetIsFavoriteOrNot(data, true)
                isClicked = true
                Log.d("MyLog", "отправили дату")

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
