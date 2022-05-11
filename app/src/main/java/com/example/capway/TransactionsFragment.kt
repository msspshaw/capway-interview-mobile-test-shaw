package com.example.capway

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capway.data.Account
import com.example.capway.data.Formatter
import com.example.capway.data.Transaction
import com.google.android.material.tabs.TabLayout


class TransactionsFragment: Fragment(), View.OnClickListener {
    // load test account
    private val account = Account.generateTestAccount()

    lateinit var accountBalance: TextView
    lateinit var pendingCharges: TextView
    lateinit var cardStatus: TextView
    lateinit var virtualCard: TextView
    lateinit var addFunds: TextView
    lateinit var tabLayout: TabLayout
    lateinit var transactions: LinearLayout
    lateinit var currentTransactions: List<Transaction>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.transactions_fragment, container, false)
        accountBalance = view.findViewById(R.id.account_balance)
        pendingCharges = view.findViewById(R.id.pending_charges)
        cardStatus = view.findViewById(R.id.card_status)
        virtualCard = view.findViewById(R.id.virtual_card)
        addFunds = view.findViewById(R.id.add_funds)
        tabLayout = view.findViewById(R.id.tab_layout)
        transactions = view.findViewById(R.id.transactions)

        accountBalance.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.balance))
        pendingCharges.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.pendingCharges))
        cardStatus.text = getString(R.string.card_status, account.card.lastFour.toString(), account.card.status.statusReadable)

        virtualCard.setOnClickListener(this)
        addFunds.setOnClickListener(this)

        // default to all transactions
        currentTransactions = account.transactions.transactions
        displayTransactions()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                currentTransactions = when(tab?.text) {
                    getString(R.string.transactions) -> account.transactions.transactions
                    getString(R.string.withdrawals) -> account.transactions.withdrawals
                    getString(R.string.deposits) -> account.transactions.deposits
                    else -> account.transactions.transactions
                }
                transactions.removeAllViews()
                displayTransactions()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
                // No work needed here
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
                // No work needed here
            }
        })

        return view
    }



    override fun onClick(view: View?) {
        when (view) {
            virtualCard -> showVirtualCardClick()
            addFunds -> showAddFunds()
        }
    }

    private fun showVirtualCardClick() {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.setTitle("Virtual Card")
        alertDialog.setMessage("This is the virtual card")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

    private fun showAddFunds() {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.setTitle("Add Funds")
        alertDialog.setMessage("You are now adding funds")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

    // Note, given the design required this is the only quick way
    // to enable full screen scrolling.
    // With more time, the PROPER way to do this is to use a recycler view
    // and increase it's height as you scroll, until it's the full screen and can continue
    // scrolling from there
    private fun displayTransactions() {
        for (transaction in currentTransactions) {
            val transactionCell = context?.let { TransactionCell(it, transaction) }
            transactions.addView(transactionCell)
        }
    }
}