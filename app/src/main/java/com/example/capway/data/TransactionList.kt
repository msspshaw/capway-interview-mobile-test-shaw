package com.example.capway.data

class TransactionList(val transactions: List<Transaction>) {
    val withdrawals : List<Transaction> = transactions.filter { transaction -> transaction.transactionType == TransactionType.WITHDRAWAL }

    val deposits: List<Transaction> = transactions.filter { transaction -> transaction.transactionType == TransactionType.DEPOSIT }
}