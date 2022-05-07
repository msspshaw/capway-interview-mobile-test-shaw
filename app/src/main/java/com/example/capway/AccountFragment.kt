package com.example.capway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.account_fragment.*

class AccountFragment: Fragment(), View.OnClickListener{
    // As the synthetic binding is not working properly for fragments, need
    // to use older binding methods
    private lateinit var transactionsButton: AppCompatButton
    private lateinit var moneyGoalsButton: AppCompatButton
    private lateinit var activityButton: AppCompatButton
    private lateinit var statementsButton: AppCompatButton
    private lateinit var accountSettingsButton: AppCompatButton
    private lateinit var alertButton: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.account_fragment, container, false)
        transactionsButton = view.findViewById(R.id.transaction_nav_tab)
        transactionsButton.setOnClickListener(this)

        moneyGoalsButton = view.findViewById(R.id.money_goals_nav_tab)
        moneyGoalsButton.setOnClickListener(this)

        activityButton = view.findViewById(R.id.activity_nav_tab)
        activityButton.setOnClickListener(this)

        statementsButton = view.findViewById(R.id.statements_nav_tab)
        statementsButton.setOnClickListener(this)

        accountSettingsButton = view.findViewById(R.id.account_settings_nav_tab)
        accountSettingsButton.setOnClickListener(this)

        alertButton = view.findViewById(R.id.alert_nav_tab)
        alertButton.setOnClickListener(this)

        // If we don't have a saved state, default to transactions button being selected and transactions displayed
        if (savedInstanceState == null) {
            selectButton(transactionsButton)
        }
        return view
    }

    override fun onClick(v: View?) {
        when (v) {
            transactionsButton, moneyGoalsButton, activityButton, statementsButton, accountSettingsButton, alertButton -> selectButton(v as AppCompatButton)
        }
    }

    private fun selectButton(selectedButton: AppCompatButton) {
        if (selectedButton == transactionsButton) {
            loadTransactionsFragment()
        } else {
            loadBlankFragment()
        }

        transactionsButton.isSelected = false
        moneyGoalsButton.isSelected = false
        activityButton.isSelected = false
        statementsButton.isSelected = false
        accountSettingsButton.isSelected = false
        alertButton.isSelected = false

        selectedButton.isSelected = true
    }

    private fun loadTransactionsFragment() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.account_internal_fragment_container, TransactionsFragment(), "accountInternalTransactionFragment")
            .commit()
    }

    private fun loadBlankFragment() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.account_internal_fragment_container, BlankFragment(), "accountInternalBlankFragment")
            .commit()
    }
}