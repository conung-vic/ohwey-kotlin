package com.conung.vic.bot.client.beans.inline

import com.fasterxml.jackson.annotation.JsonProperty
import com.conung.vic.bot.client.beans.User

data class ChosenInlineResult (
        @JsonProperty("result_id") var resultId: String,
        @JsonProperty("from") var from: User,
        @JsonProperty("location") var location: Location?,
        @JsonProperty("inline_message_id") var inlineMessageId: String?,
        @JsonProperty("query") var query: String
)