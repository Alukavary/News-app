package com.example.newsapp.presentation.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.data.models.NewArticle

import com.example.newsapp.domain.model.Article
import com.example.newsapp.ui.theme.LightPrimary
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE
import kotlinx.serialization.json.Json

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsViewStart(
    firstNews: Article,
    navController: NavController
) {
    var isClicked by rememberSaveable { mutableStateOf<Boolean>(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = {
                isClicked = true
                val json = Uri.encode(Json.encodeToString(Article.serializer(), firstNews))
                navController.navigate("$DETAILS_SCREEN_ROUTE/$json")
            })
    ) {
        Column(
        ) {
            GlideImage(
                model = firstNews.urlToImage,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
//                    error = painterResource(R.drawable.fake_news_icon),
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = firstNews.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = firstNews.description,
                    fontSize = 16.sp,
                    lineHeight = 21.sp
                )
            }

            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = firstNews.publishedAt,
                    fontSize = 14.sp,
                    color = LightPrimary

                )

            }
        }
    }


}