package com.conung.vic.bot.client.beans.games

import com.conung.vic.bot.client.beans.MessageEntity
import com.conung.vic.bot.client.beans.PhotoSize
import com.fasterxml.jackson.annotation.JsonProperty

data class Game (
        @JsonProperty("title") var title: String,
        @JsonProperty("description") var description: String,
        @JsonProperty("photo") var photo: List<PhotoSize>,
        @JsonProperty("text") var text: String?,
        @JsonProperty("text_entities") var textEntities: List<MessageEntity>?,
        @JsonProperty("animation") var animation: Animation?
)