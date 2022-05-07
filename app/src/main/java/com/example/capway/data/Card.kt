package com.example.capway.data

class Card (val lastFour: Int, val status: CardStatus)

enum class CardStatus(val statusReadable: String) {
    INACTIVE("inactive"),
    ACTIVE("active")
}