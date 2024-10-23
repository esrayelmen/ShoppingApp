package com.esrayelmen.e_market.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ProductResponse(

    @ColumnInfo("createdAt")
    val createdAt: String?,

    @ColumnInfo("name")
    val name: String?,

    @ColumnInfo("image")
    @SerializedName("image")
    val imageUrl: String?,

    @ColumnInfo("price")
    val price: String?,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("model")
    val model: String?,

    @ColumnInfo("brand")
    val brand: String,

    @ColumnInfo("id")
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo("favoriteStatus")
    var isFavorite: Boolean = false

)