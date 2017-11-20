package com.conung.vic.bot.client.beans.payments

import com.conung.vic.bot.client.beans.User
import com.fasterxml.jackson.annotation.JsonProperty

data class PreCheckoutQuery (
        @JsonProperty("id") var id: String,
        @JsonProperty("from") var from: User,
        @JsonProperty("currency") var currency: String,
        @JsonProperty("total_amount") var totalAmount: Int,
        @JsonProperty("invoice_payload") var invoicePayload: String,
        @JsonProperty("shipping_option_id") var shippingOptionId: String?,
        @JsonProperty("order_info") var orderInfo: OrderInfo?
)