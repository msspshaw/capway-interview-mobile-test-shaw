package com.example.capway

import android.app.AlertDialog
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.capway.data.Account
import com.example.capway.data.Formatter
import com.example.capway.data.Transaction
import com.google.android.material.tabs.TabLayout


class TransactionsFragment: Fragment(), View.OnClickListener, TextWatcher, View.OnScrollChangeListener {
    // load test account
    private val account = Account.generateTestAccount()

    lateinit var accountBalance: TextView
    lateinit var pendingCharges: TextView
    lateinit var cardStatus: TextView
    lateinit var virtualCard: TextView
    lateinit var addFunds: TextView
    lateinit var tabLayout: TabLayout
    lateinit var transactions: LinearLayout
    lateinit var searchText: EditText
    lateinit var searchEndImage: ImageView
    lateinit var searchEndImageFilled: ImageView
    lateinit var currentTransactions: List<Transaction>
    lateinit var scrollView: ScrollView
    lateinit var loading: ProgressBar
    lateinit var header: ConstraintLayout
    lateinit var headerAccountBalance: TextView

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
        searchText = view.findViewById(R.id.search_text)
        searchEndImage = view.findViewById(R.id.search_end_image)
        searchEndImageFilled = view.findViewById(R.id.search_end_image_filled)
        transactions = view.findViewById(R.id.transactions)
        scrollView = view.findViewById(R.id.scroll_view)
        loading = view.findViewById(R.id.loading)
        header = view.findViewById(R.id.header)
        headerAccountBalance = view.findViewById(R.id.header_account_balance)

        scrollView.setOnScrollChangeListener(this)

        accountBalance.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.balance))
        pendingCharges.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.pendingCharges))
        cardStatus.text = getString(R.string.card_status, account.card.lastFour.toString(), account.card.status.statusReadable)
        headerAccountBalance.text = getString(R.string.money_text, Formatter.formatDoubleToMoney(account.balance))

        virtualCard.setOnClickListener(this)
        addFunds.setOnClickListener(this)
        searchText.addTextChangedListener(this)

        resetScrollBelowTop()

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

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // Not needed
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val notEmpty = text?.length ?: 0 > 0
        if (notEmpty) {
            searchEndImage.visibility = View.GONE
            searchEndImageFilled.visibility = View.VISIBLE
        } else {
            searchEndImage.visibility = View.VISIBLE
            searchEndImageFilled.visibility = View.GONE
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        // Not needed
    }

    override fun onScrollChange(
        view: View?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int) {
        // If we're scrolling up, and we're near the top (25 pixels)
        // we show loading with a second timer, then hide it and make sure
        // we scroll to 1
        if (scrollY < oldScrollY && scrollY < 25) {
            // show loading
            loading.visibility = View.VISIBLE
            val handler = Handler()
            handler.postDelayed(Runnable { // Write whatever to want to do after delay specified (1 sec)
                loading.visibility = View.GONE
                resetScrollBelowTop()
            }, 1000)
        }
        // When search is no longer, we have scrolled high enough on screen to change
        // the visibility of the header
        if (!isVisible(searchText)) {
            header.visibility = View.VISIBLE
        } else {
            header.visibility = View.GONE
        }
    }

    // A simple implementation of pull to refresh, we simply need to scroll to
    // y = 1 in order to allow a scroll up at the top of the screen.
    private fun resetScrollBelowTop() {
        scrollView.post(Runnable { //setting position here :
            scrollView.scrollTo(0, 1)
        })
    }

    private fun isVisible(view: View?): Boolean {
        if (view == null) {
            return false
        }
        if (!view.isShown) {
            return false
        }
        val actualPosition = Rect()
        view.getGlobalVisibleRect(actualPosition)
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels
        val screen = Rect(0, 0, width, height)
        return actualPosition.intersect(screen)
    }
}