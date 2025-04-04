package com.example.newsapp.domain.model


data class GenerateItem(val type: ItemType, val item: ArticleModel)

enum class ItemType {
    TYPE1, TYPE2
}
