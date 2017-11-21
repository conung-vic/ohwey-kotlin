package com.conung.vic.bot.client.beans.stickers

import com.fasterxml.jackson.annotation.JsonProperty

data class MaskPosition(
        @JsonProperty("point") var point: String,
        @JsonProperty("x_shift") var xShift: Float,
        @JsonProperty("y_shift") var yShift: Float,
        @JsonProperty("scale") var scale: Float
)