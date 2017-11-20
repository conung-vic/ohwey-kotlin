package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class PhotoSize (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("width") var width: Int,
        @JsonProperty("height") var height: Int,
        @JsonProperty("file_size") var fileSize: Int?
)