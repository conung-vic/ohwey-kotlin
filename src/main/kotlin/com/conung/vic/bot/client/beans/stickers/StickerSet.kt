package com.conung.vic.bot.client.beans.stickers

import com.fasterxml.jackson.annotation.JsonProperty

data class StickerSet (
        @JsonProperty("name") var name: String,
        @JsonProperty("title") var title: String,
        @JsonProperty("contains_masks") var containsMasks: Boolean,
        @JsonProperty("stickers") var stickers: List<Sticker>
)