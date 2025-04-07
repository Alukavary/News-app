package com.example.newsapp.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.detailsScreen.DetailsVM
import com.example.newsapp.ui.theme.LightPrimary


@Composable
fun LikeCustomButton(
    viewModel: DetailsVM = hiltViewModel(),
    data: ArticleModel,
) {
    var isFav by rememberSaveable { mutableStateOf(data.isFavorite) }
    val context = LocalContext.current

    IconButton(
        onClick = {
            isFav = !isFav
            viewModel.resetIsFavoriteOrNot(data, isFav)
            Toast.makeText(
                context,
                if (isFav) "Added to favorites" else "Remove from favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
    ) {
        Icon(
            modifier = Modifier.size(160.dp),
            painter = if (!isFav) painterResource(id = R.drawable.stars)
            else painterResource(id = R.drawable.baseline_star_24),
            contentDescription = null,
            tint = LightPrimary,
        )
    }
}
