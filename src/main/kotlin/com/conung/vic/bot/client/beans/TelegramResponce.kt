package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class TelegramResponce (
        @JsonProperty("ok") var ok: Boolean,
        @JsonProperty("result") var result: List<Update>
)