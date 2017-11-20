package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Contact (
        @JsonProperty("phone_number") var phoneNumber: String,
        @JsonProperty("first_name") var firstName: String,
        @JsonProperty("last_name") var lastName: String?,
        @JsonProperty("user_id") var userId: Int?
)