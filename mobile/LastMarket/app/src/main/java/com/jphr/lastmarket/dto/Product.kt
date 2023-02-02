package com.jphr.lastmarket.dto

import java.time.LocalDateTime

data class Product(
    val createdDateTime: LocalDateTime,
    val dealState: String,
    val favoriteCnt: Long,
    val imgURI: String,
    val instantPrice: Long,
    val isFavorite: Boolean,
    val liveTime: LocalDateTime?,
    val location: String,
    val productId: Long,
    val sellerId: Long,
    val sellerNickname: String,
    val startingPrice: Long,
    val title: String
)