package com.conung.vic.bot.client.beans

import com.conung.vic.bot.client.beans.games.Game
import com.conung.vic.bot.client.beans.payments.Invoice
import com.conung.vic.bot.client.beans.payments.SuccessfulPayment
import com.conung.vic.bot.client.beans.stickers.Sticker
import com.fasterxml.jackson.annotation.JsonProperty

data class Message (
        @JsonProperty("message_id") var messageId: Int,
        @JsonProperty("from") var from: User?,
        @JsonProperty("date") var date: Int,
        @JsonProperty("chat") var chat: Chat,
        @JsonProperty("forward_from") var forwardFrom: User?,
        @JsonProperty("forward_from_chat") var forwardFromChat: Chat?,
        @JsonProperty("forward_from_message_id") var forwardFromMessageId: Int?,
        @JsonProperty("forward_signature") var forwardSignature: String?,
        @JsonProperty("forward_date") var forwardDate: Int?,
        @JsonProperty("reply_to_message") var replyToMessage: Message?,
        @JsonProperty("edit_date") var editDate: Int?,
        @JsonProperty("media_group_id") var mediaGroupId: String?,
        @JsonProperty("author_signature") var authorSignature: String?,
        @JsonProperty("text") var text: String?,
        @JsonProperty("entities") var entities: List<MessageEntity>?,
        @JsonProperty("caption_entities") var captionEntities: List<MessageEntity>?,
        @JsonProperty("audio") var audio: Audio?,
        @JsonProperty("document") var document: Document?,
        @JsonProperty("game") var game: Game?,
        @JsonProperty("photo") var photo: List<PhotoSize>?,
        @JsonProperty("sticker") var sticker: Sticker?,
        @JsonProperty("video") var video: Video?,
        @JsonProperty("voice") var voice: Voice?,
        @JsonProperty("video_note") var videoNote: VideoNote?,
        @JsonProperty("caption") var caption: String?,
        @JsonProperty("contact") var contact: Contact?,
        @JsonProperty("location") var location: Location?,
        @JsonProperty("venue") var venue: Venue?,
        @JsonProperty("new_chat_members") var newChatMembers: List<User>?,
        @JsonProperty("left_chat_member") var leftChatMember: User?,
        @JsonProperty("new_chat_title") var newChatTitle: String?,
        @JsonProperty("new_chat_photo") var newChatPhoto: List<PhotoSize>?,
        @JsonProperty("delete_chat_photo") var deleteChatPhoto: Boolean?,
        @JsonProperty("group_chat_created") var groupChatCreated: Boolean?,
        @JsonProperty("supergroup_chat_created") var superGroupChatCreated: Boolean?,
        @JsonProperty("channel_chat_created") var channelChatCreated: Boolean?,
        @JsonProperty("migrate_to_chat_id") var migrateToChatId: Int?,
        @JsonProperty("migrate_from_chat_id") var migrateFromChatId: Int?,
        @JsonProperty("pinned_message") var pinnedMessage: Message?,
        @JsonProperty("invoice") var invoice: Invoice?,
        @JsonProperty("successful_payment") var successfulPayment: SuccessfulPayment?
)