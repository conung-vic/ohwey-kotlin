package com.conung.vic.bot.client.beans.stickers

import com.conung.vic.bot.client.beans.PhotoSize
import com.fasterxml.jackson.annotation.JsonProperty

data class Sticker (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("width") var width: Int,
        @JsonProperty("height") var height: Int,
        @JsonProperty("thumb") var thumb: PhotoSize?,
        @JsonProperty("emoji") var emoji: String?,
        @JsonProperty("set_name") var setName: String?,
        @JsonProperty("mask_position") var maskPosition: MaskPosition?,
        @JsonProperty("file_size") var fileSize: Int?
)