package com.conung.vic.bot.client.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class Chat (
        @JsonProperty("id") var id: Int,
        @JsonProperty("type") var type: String,
        @JsonProperty("title") var title: String?,
        @JsonProperty("username") var username: String?,
        @JsonProperty("first_name") var firstName: String?,
        @JsonProperty("last_name") var lastName: String?,
        @JsonProperty("all_members_are_administrators") var allMembersAreAdministrators: Boolean?,
        @JsonProperty("photo") var photo: ChatPhoto?,
        @JsonProperty("description") var description: String?,
        @JsonProperty("invite_link") var inviteLink: String?,
        @JsonProperty("pinned_message") var pinnedMessage: Message?,
        @JsonProperty("sticker_set_name") var stickerSetName: String?,
        @JsonProperty("can_set_sticker_set") var canSetStickerSet: Boolean?
)