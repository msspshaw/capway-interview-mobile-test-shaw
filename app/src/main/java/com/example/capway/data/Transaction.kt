package com.example.capway.data

import java.util.*

class Transaction (
    val description: String,
    val timestamp: Calendar,
    val amount: Float,
    val transactionType: TransactionType,
    val pending: Boolean
        ) {
}

enum class TransactionType {
    DEPOSIT,
    WITHDRAWL
}