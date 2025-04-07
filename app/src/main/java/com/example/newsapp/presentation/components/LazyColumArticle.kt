package com.example.newsapp.presentation.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightPrimary
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.room.util.TableInfo
import com.example.newsapp.domain.model.ArticleModel

@Composable
fun LazyColumArticle(
    articleContext: String,
    data: ArticleModel,

    ) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 30.dp)
            .padding(horizontal = 10.dp)
    ) {
        item {
            Text(
                text = articleContext, modifier = Modifier.padding(bottom = 10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End

                ) {
                    Text(
                        text = data.author,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        color = LightPrimary
                    )
                    Text(
                        text = "Read original article",
                        color = LightPrimary,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
                            context.startActivity(intent)
                        })
                }

            }
        }

    }
}