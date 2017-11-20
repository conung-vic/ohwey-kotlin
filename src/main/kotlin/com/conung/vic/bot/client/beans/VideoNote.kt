package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class VideoNote (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("length") var length: Int,
        @JsonProperty("duration") var duration: Int,
        @JsonProperty("thumb") var thumb: PhotoSize?,
        @JsonProperty("file_size") var fileSize: Int?
)