package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Venue (
        @JsonProperty("location") var location: Location,
        @JsonProperty("title") var title: String,
        @JsonProperty("address") var address: String,
        @JsonProperty("foursquare_id") var foursquareId: Int?
)