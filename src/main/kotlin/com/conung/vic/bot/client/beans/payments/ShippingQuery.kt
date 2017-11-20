package com.conung.vic.bot.client.beans.payments

import com.conung.vic.bot.client.beans.User
import com.fasterxml.jackson.annotation.JsonProperty

data class ShippingQuery (
        @JsonProperty("id") var id: String,
        @JsonProperty("from") var from: User,
        @JsonProperty("invoice_payload") var invoicePayload: String,
        @JsonProperty("shipping_address") var shippingAddress: ShippingAddress
)