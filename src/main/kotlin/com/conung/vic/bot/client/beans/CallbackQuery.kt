package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class CallbackQuery (
        @JsonProperty("id") var id: String,
        @JsonProperty("from") var from: User,
        @JsonProperty("message") var message: Message?,
        @JsonProperty("inline_message_id") var inlineMessageId: String?,
        @JsonProperty("chat_instance") var chatInstance: String,
        @JsonProperty("data") var data: String?,
        @JsonProperty("game_short_name") var gameShortName: String?
)