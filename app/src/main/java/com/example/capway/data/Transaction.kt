package com.example.capway.data

import java.util.*

class Transaction (
    val description: String,
    // In reality the timestamping would be different here
    // leaving as a string for mock data simplicity
    val date: String,
    val amount: Double,
    val transactionType: TransactionType,
    val pending: Boolean
        ) {
}

enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    DIRECT_DEPOSIT
}