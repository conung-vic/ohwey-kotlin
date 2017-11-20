package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Document (
        @JsonProperty("file_id") var fileId: String,
        @JsonProperty("thumb") var thumb: PhotoSize?,
        @JsonProperty("file_name") var fileName: String?,
        @JsonProperty("mime_type") var mimeType: String?,
        @JsonProperty("file_size") var fileSize: Int?
)