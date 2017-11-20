package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Audio (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("duration") var duration: Int,
        @JsonProperty("performer") var performer: String?,
        @JsonProperty("title") var title: String?,
        @JsonProperty("mime_type") var mimeType: String?,
        @JsonProperty("file_size") var fileSize: Int
)