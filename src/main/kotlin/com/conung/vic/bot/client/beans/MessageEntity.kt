package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageEntity (
        @JsonProperty("type") var type: String,
        @JsonProperty("offset") var offset: Int,
        @JsonProperty("length") var length: Int,
        @JsonProperty("url") var url: String?,
        @JsonProperty("user") var user: User?
)