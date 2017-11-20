package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Video (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("width") var width: Int,
        @JsonProperty("height") var height: Int,
        @JsonProperty("duration") var duraton: Int,
        @JsonProperty("thumb") var thumb: PhotoSize?,
        @JsonProperty("mime_type") var mimeType: String?,
        @JsonProperty("file_size") var fileSize: Int?
)