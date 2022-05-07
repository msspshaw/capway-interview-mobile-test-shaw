package com.example.capway.data

import java.util.*

class Account (
    val balance: Double,
    val pendingCharges: Double,
    val card: Card,
    val transactions: TransactionList
    ) {
    companion object {
        private val testCard = Card(2881, CardStatus.INACTIVE)
        private val testTransactions = TransactionList(listOf(
            // Arbitrarily large set of transactions
            Transaction("Dominos", Calendar.getInstance(), 2.toFloat(), TransactionType.WITHDRAWL, false),
            Transaction("Deposit from bank", Calendar.getInstance(), 25.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 100.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Macys", Calendar.getInstance(), 132.toFloat(), TransactionType.WITHDRAWL, true),
            Transaction("Dominos", Calendar.getInstance(), 2.toFloat(), TransactionType.WITHDRAWL, false),
            Transaction("Deposit from bank", Calendar.getInstance(), 130.toFloat(), TransactionType.DEPOSIT, false),
            Transaction("Deposit from bank", Calendar.getInstance(), 25.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 100.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Macys", Calendar.getInstance(), 132.toFloat(), TransactionType.WITHDRAWL, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 130.toFloat(), TransactionType.DEPOSIT, false),
            Transaction("Dominos", Calendar.getInstance(), 2.toFloat(), TransactionType.WITHDRAWL, false),
            Transaction("Deposit from bank", Calendar.getInstance(), 25.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 100.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Macys", Calendar.getInstance(), 132.toFloat(), TransactionType.WITHDRAWL, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 130.toFloat(), TransactionType.DEPOSIT, false),
            Transaction("Dominos", Calendar.getInstance(), 2.toFloat(), TransactionType.WITHDRAWL, false),
            Transaction("Deposit from bank", Calendar.getInstance(), 25.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 100.toFloat(), TransactionType.DEPOSIT, true),
            Transaction("Macys", Calendar.getInstance(), 132.toFloat(), TransactionType.WITHDRAWL, true),
            Transaction("Deposit from bank", Calendar.getInstance(), 130.toFloat(), TransactionType.DEPOSIT, false),

            // Unique test transaction at end to verify no loss of data
            Transaction("Steakkkkkks", Calendar.getInstance(), 5132.toFloat(), TransactionType.WITHDRAWL, false),
        ))
        fun generateTestAccount(): Account {
            return Account((255.73).toDouble(), pendingCharges = 0.toDouble(), testCard, testTransactions)
        }
    }
}