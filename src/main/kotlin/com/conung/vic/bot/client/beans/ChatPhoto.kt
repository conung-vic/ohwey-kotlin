package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatPhoto (
        @JsonProperty("small_file_id") var smallFileId: String,
        @JsonProperty("big_file_id") var bigFileId: String
)