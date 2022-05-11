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
            // From required info, seperation between "Deposit at bank" and "Deposit at ATM" wasn't clear
            // made assumptions about Direct Deposit
            // Time is set as simple string - would be better to at least pass a timeInMillis and transform from there
            Transaction("Dominos", "12/13/2021 at 07:35 PM", 2.toDouble(), TransactionType.WITHDRAWAL, false),
            Transaction("Deposit from bank", "12/07/2021 at 06:00 PM", 25.toDouble(), TransactionType.DIRECT_DEPOSIT, true),
            Transaction("Deposit from bank", "12/07/2021 at 11:44 AM", 100.toDouble(), TransactionType.DIRECT_DEPOSIT, true),
            Transaction("Macys", "03/13/2021 at 02:23 PM", 132.toDouble(), TransactionType.WITHDRAWAL, true),
            Transaction("Dominos", "04/12/2022 at 01:25 PM", 2.toDouble(), TransactionType.WITHDRAWAL, false),
            Transaction("Deposit from bank", "09/23/2021 at 11:22 PM", 130.toDouble(), TransactionType.DIRECT_DEPOSIT, false),
            Transaction("Deposit from bank", "12/13/2021 at 07:35 PM", 25.toDouble(), TransactionType.DIRECT_DEPOSIT, true),
            Transaction("Deposit from bank", "09/23/2021 at 11:22 PM", 100.toDouble(), TransactionType.DIRECT_DEPOSIT, true),
            Transaction("Macys", "04/12/2022 at 01:25 PM", 132.toDouble(), TransactionType.WITHDRAWAL, true),
            Transaction("Deposit at ATM", "03/13/2021 at 02:23 PM", 130.toDouble(), TransactionType.DEPOSIT, false),
            Transaction("Dominos", "12/07/2021 at 11:44 AM", 2.toDouble(), TransactionType.WITHDRAWAL, false),
            Transaction("Deposit at ATM", "12/07/2021 at 06:00 PM", 25.toDouble(), TransactionType.DEPOSIT, true),
            Transaction("Deposit at ATM", "12/13/2021 at 07:35 PM", 100.toDouble(), TransactionType.DEPOSIT, true),
            Transaction("Macys", "12/13/2021 at 07:35 PM", 132.toDouble(), TransactionType.WITHDRAWAL, true),
            Transaction("Deposit from bank", "12/07/2021 at 06:00 PM", 130.toDouble(), TransactionType.DIRECT_DEPOSIT, false),
            Transaction("Dominos", "12/07/2021 at 11:44 AM", 2.toDouble(), TransactionType.WITHDRAWAL, false),
            Transaction("Deposit at ATM", "03/13/2021 at 02:23 PM", 25.toDouble(), TransactionType.DEPOSIT, true),
            Transaction("Deposit at ATM", "03/13/2021 at 02:23 PM", 100.toDouble(), TransactionType.DEPOSIT, true),
            Transaction("Macys", "04/12/2022 at 01:25 PM", 132.toDouble(), TransactionType.WITHDRAWAL, true),
            Transaction("Deposit from bank", "09/23/2021 at 11:22 PM", 130.toDouble(), TransactionType.DIRECT_DEPOSIT, false),

            // Unique test transaction at end to verify no loss of data
            Transaction("Steaks", "04/12/2022 at 01:25 PM", 5132.toDouble(), TransactionType.WITHDRAWAL, false),
        ))
        fun generateTestAccount(): Account {
            return Account((255.73).toDouble(), pendingCharges = 0.toDouble(), testCard, testTransactions)
        }
    }
}