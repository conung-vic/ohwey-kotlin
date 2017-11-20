package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Voice (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("duration") var duration: Int,
        @JsonProperty("mime_type") var mimeType: String?,
        @JsonProperty("file_size") var fileSize: Int?
)