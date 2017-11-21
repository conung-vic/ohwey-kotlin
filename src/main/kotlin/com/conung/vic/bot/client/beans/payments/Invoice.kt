package com.conung.vic.bot.client.beans.payments

import com.fasterxml.jackson.annotation.JsonProperty

data class Invoice (
        @JsonProperty("title") var title: String,
        @JsonProperty("description") var description: String,
        @JsonProperty("start_parameter") var startParameter: String,
        @JsonProperty("currency") var currency: String,
        @JsonProperty("total_amount") var totalAmount: Int
)