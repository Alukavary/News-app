package com.example.newsapp.presentation.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.R
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.components.CustomIconButton
import com.example.newsapp.presentation.components.LikeCustomButton
import com.example.newsapp.ui.theme.LightPrimary


@OptIn(ExperimentalGlideComposeApi::class)

@Composable
fun DetailsScreen(
    data: ArticleModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 30.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(0.4f)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .alpha(0.5F)
            ) {
                GlideImage(
                    model = data.urlToImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 10.dp)
            ) {
                CustomIconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    icon = R.drawable.arrow_back,
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
                    text = data.title,
                    fontSize = 25.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Gray, offset = Offset(2f, 2f), blurRadius = 3f
                        )
                    ),
                    modifier = Modifier.padding(bottom = 20.dp)

                )
                Box(
                    modifier = Modifier
                        .background(LightPrimary)
                        .padding(horizontal = 3.dp)
                ) {
                    Text(
                        text = data.publishedAtFormatted, fontSize = 14.sp, color = Color.White
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = 10.dp)
            ) {
                LikeCustomButton(
                    data = data,
                    isFav = data.isFavorite
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 30.dp)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = data.content,
                fontSize = 16.sp,
                lineHeight = 20.sp,
            )
        }
    }
}