package com.example.capway

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capway.data.Account
import com.example.capway.data.Formatter


class TransactionsFragment: Fragment(), View.OnClickListener {
    // load test account
    private val account = Account.generateTestAccount()

    lateinit var accountBalance: TextView
    lateinit var pendingCharges: TextView
    lateinit var cardStatus: TextView
    lateinit var virtualCard: TextView
    lateinit var addFunds: TextView

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

        accountBalance.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.balance))
        pendingCharges.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.pendingCharges))
        cardStatus.text = getString(R.string.card_status, account.card.lastFour.toString(), account.card.status.statusReadable)

        virtualCard.setOnClickListener(this)
        addFunds.setOnClickListener(this)

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
}