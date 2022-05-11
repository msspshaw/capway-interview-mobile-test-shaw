package com.example.capway

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.capway.data.Formatter
import com.example.capway.data.Transaction
import com.example.capway.data.TransactionType

class TransactionCell (context: Context, val transaction: Transaction) :
    FrameLayout(context) {
    private val positiveSymbol = "+"
    private val negativeSymbol = "-"

    init {
        val view = View.inflate(context, R.layout.transaction_cell_layout, this)
        val transactionImage = view.findViewById<ImageView>(R.id.transaction_image)
        val description = view.findViewById<TextView>(R.id.description)
        val date = view.findViewById<TextView>(R.id.date)
        val amount = view.findViewById<TextView>(R.id.amount)
        val status = view.findViewById<TextView>(R.id.status)

        val imageResource = when (transaction.transactionType) {
            TransactionType.DIRECT_DEPOSIT -> R.drawable.bank
            // The image shown in the video is not included in assets, using card here
            TransactionType.DEPOSIT -> R.drawable.card
            TransactionType.WITHDRAWAL -> R.drawable.card
        }
        transactionImage.setImageResource(imageResource)

        description.text = transaction.description
        date.text = transaction.date
        val symbol = getPositiveOrNegativeSymbol()
        amount.text = context.getString(
            R.string.positive_negative_money_text,
            symbol,
            Formatter.formatDoubleToMoney(transaction.amount)
        )

        when (transaction.transactionType) {
            TransactionType.DEPOSIT, TransactionType.DIRECT_DEPOSIT ->
                amount.setTextColor(context.getColor(R.color.positive))
            TransactionType.WITHDRAWAL ->
                amount.setTextColor(context.getColor(R.color.negative))
        }

        if (transaction.pending) {
            status.visibility = VISIBLE
        } else {
            status.visibility = GONE
        }
    }

    private fun getPositiveOrNegativeSymbol(): String {
        return when (transaction.transactionType) {
            TransactionType.DEPOSIT, TransactionType.DIRECT_DEPOSIT -> positiveSymbol
            TransactionType.WITHDRAWAL -> negativeSymbol
        }
    }
}