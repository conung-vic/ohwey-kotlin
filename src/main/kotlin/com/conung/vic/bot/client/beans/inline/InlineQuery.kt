package com.conung.vic.bot.client.beans.inline

import com.conung.vic.bot.client.beans.User
import com.fasterxml.jackson.annotation.JsonProperty

data class InlineQuery (
        @JsonProperty("id") var id: String,
        @JsonProperty("from") var from: User,
        @JsonProperty("location") var location: Location?,
        @JsonProperty("query") var query: String,
        @JsonProperty("offset") var offset: String
)