package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class UserProfilePhotos (
        @JsonProperty("total_count") var totalCount: Int,
        @JsonProperty("photos") var photos: List<List<PhotoSize>>
)