package com.conung.vic.bot.client.beans.payments

import com.fasterxml.jackson.annotation.JsonProperty

data class SuccessfulPayment(
        @JsonProperty("currency") var currency: String,
        @JsonProperty("total_amount") var totalAmount: Int,
        @JsonProperty("invoice_payload") var invoicePayload: String,
        @JsonProperty("shipping_option_id") var shippingOrderId: String?,
        @JsonProperty("order_info") var orderInfo: OrderInfo?,
        @JsonProperty("telegram_payment_charge_id") var telegramPaymentChargeId: String,
        @JsonProperty("provider_payment_charge_id") var providerPaymentChargeId: String
)