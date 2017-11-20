package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class User (
        @JsonProperty("id") var id: Int,
        @JsonProperty("is_bot") var isBot: Boolean,
        @JsonProperty("first_name") var firstName: String,
        @JsonProperty("last_name") var lastName: String?,
        @JsonProperty("username") var username: String?,
        @JsonProperty("language_code") var languageCode: String?
)