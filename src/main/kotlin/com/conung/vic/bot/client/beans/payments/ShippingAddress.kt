package com.conung.vic.bot.client.beans.payments

import com.fasterxml.jackson.annotation.JsonProperty

data class ShippingAddress (
        @JsonProperty("country_code") var countryCode: String,
        @JsonProperty("state") var state: String,
        @JsonProperty("city") var city: String,
        @JsonProperty("street_line1") var streetLine1: String,
        @JsonProperty("street_line2") var streetLine2: String,
        @JsonProperty("post_code") var postCode: String
)