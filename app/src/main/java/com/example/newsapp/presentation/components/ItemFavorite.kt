package com.example.newsapp.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE
import kotlinx.serialization.json.Json
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.newsapp.domain.model.ArticleModel


@Preview(showBackground = true)
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemFavorite(
    item: ArticleModel,
    navController: NavController,
) {

    var isClicked by remember { mutableStateOf<Boolean>(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(300.dp)
            .padding(bottom = 8.dp)
    ) {

        Box(
            modifier = Modifier
                .background(Color.Black)
                .alpha(0.5F)
                .clickable(onClick = {
                    isClicked = true
                    val json = Uri.encode(Json.encodeToString(ArticleModel.serializer(), item))
                    navController.navigate("$DETAILS_SCREEN_ROUTE/$json")
                }),
        )
        {
            GlideImage(
                model = item.urlToImage,
//                model = R.drawable.fake_news_icon,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                alpha = 0.7f
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)

        ) {
            Text(
                text = item.title,
                fontSize = 25.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(2f, 2f),
                        blurRadius = 3f
                    )
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp)

            )
        }
        Text(
            text = item.publishedAtFormatted,
            fontSize = 14.sp,
            color = Color.White,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    offset = Offset(2f, 2f),
                    blurRadius = 3f
                )
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }

}


