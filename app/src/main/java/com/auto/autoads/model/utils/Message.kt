package com.auto.autoads.model.utils

data class Message(
    val id: String,
    val title: String,
    val body: String,
    val email: String,
    val senderName: String
) {
    constructor() : this(
        "-1",
        "title",
        "body",
        "email",
        "name"
    )
}