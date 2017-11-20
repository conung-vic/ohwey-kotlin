package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Location (
        @JsonProperty("longitude") var longitude: Float,
        @JsonProperty("latitude") var latitude: Float
)