package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class File (
        @JsonProperty("file_id") var fileId: Int,
        @JsonProperty("file_size") var fileSize: Int?,

        // https://api.telegram.org/file/bot<token>/<file_path>
        @JsonProperty("file_path") var filePath: String?
)