package com.example.capway.data

class TransactionList(val transactions: List<Transaction>) {
    fun withdrawls() : List<Transaction> {
        return transactions.filter { transaction -> transaction.transactionType == TransactionType.WITHDRAWL }
    }

    fun deposits(): List<Transaction> {
        return transactions.filter { transaction -> transaction.transactionType == TransactionType.DEPOSIT }
    }
}