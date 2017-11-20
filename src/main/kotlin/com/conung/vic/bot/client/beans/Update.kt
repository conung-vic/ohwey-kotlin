package com.conung.vic.bot.client.beans

import com.conung.vic.bot.client.beans.inline.ChosenInlineResult
import com.conung.vic.bot.client.beans.inline.InlineQuery
import com.conung.vic.bot.client.beans.payments.PreCheckoutQuery
import com.conung.vic.bot.client.beans.payments.ShippingQuery
import com.fasterxml.jackson.annotation.JsonProperty

// https://core.telegram.org/bots/api
data class Update (
        @JsonProperty("update_id") var updateId: Int,
        @JsonProperty("message") var message: Message?,
        @JsonProperty("edited_message") var editedMessage: Message?,
        @JsonProperty("channel_post") var channelPost: Message?,
        @JsonProperty("edited_channel_post") var editedChannelPost: Message?,
        @JsonProperty("inline_query") var inlineQuery: InlineQuery?,
        @JsonProperty("chosen_inline_result") var chosenInlineResult: ChosenInlineResult?,
        @JsonProperty("callback_query") var callbackQuery: CallbackQuery?,
        @JsonProperty("shipping_query") var shippingQuery: ShippingQuery?,
        @JsonProperty("pre_checkout_query") var preCheckoutQuery: PreCheckoutQuery?
)