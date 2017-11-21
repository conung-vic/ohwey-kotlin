package com.conung.vic.bot.client.beans.payments

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderInfo (
        @JsonProperty("name") var name: String?,
        @JsonProperty("phone_number") var phoneNumber: String?,
        @JsonProperty("email") var email: String?,
        @JsonProperty("shipping_address") var shippingAddress: ShippingAddress?
)