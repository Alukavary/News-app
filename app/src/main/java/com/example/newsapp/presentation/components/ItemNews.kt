package com.example.newsapp.presentation.components


import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.detailsScreen.DetailsScreen
import com.example.newsapp.ui.theme.LightPrimary
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE
import kotlinx.serialization.json.Json


@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
fun ItemNews(
    item: ArticleModel,
    navController: NavController,
) {

    var isClicked by remember { mutableStateOf<Boolean>(false) }

    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(bottom = 8.dp)
            .shadow(5.dp)
            .clickable(onClick = {
                isClicked = true
                val json = Uri.encode(Json.encodeToString(ArticleModel.serializer(), item))
                navController.navigate("$DETAILS_SCREEN_ROUTE/$json")
            }),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GlideImage(
                model = item.urlToImage,
//                model = R.drawable.fake_news_icon,
                contentDescription = "",
                modifier = Modifier
                    .height(170.dp)
                    .width(150.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = item.title + "...",
                            lineHeight = 15.sp,
                            fontSize = 10.sp,
                            maxLines = 4,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                        Text(
                            text = item.description + "...",
                            fontSize = 10.sp,
                            maxLines = 3,
                            lineHeight = 15.sp
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End

                ) {
                    Text(
                        text = item.publishedAtFormatted,
                        fontSize = 11.sp,
                        color = LightPrimary,
                    )
                }
            }
        }
    }

}


