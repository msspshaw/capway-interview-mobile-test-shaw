package com.example.capway

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : FragmentActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Set on click listeners for bottom bar buttons
        account_menu_item.setOnClickListener(this)
        send_menu_item.setOnClickListener(this)
        more_menu_item.setOnClickListener(this)
        learn_menu_item.setOnClickListener(this)
        phunds_menu_item.setOnClickListener(this)

        if (savedInstanceState == null) {
            // If not recreating the activity, default to account page
            selectAccountMenuItem()
        }
    }



    // Utility functions for selecting bottom bar menu items
    private fun selectAccountMenuItem() {
        loadAccountFragment()
        top_title.text = getString(R.string.account_menu_title)
        setAccountMenuItemVisibilitySelected()
        setSendMenuItemVisibilityUnselected()
        setLearnMenuItemVisibilityUnselected()
        setPhundsMenuItemVisibilityUnselected()
    }

    private fun selectSendMenuItem() {
        loadBlankFragment()
        top_title.text = getString(R.string.send_menu_title)
        // Navigate to send tab (white view)
        setAccountMenuItemVisibilityUnselected()
        setSendMenuItemVisibilitySelected()
        setLearnMenuItemVisibilityUnselected()
        setPhundsMenuItemVisibilityUnselected()
    }

    private fun selectMoreMenuItem() {
        loadBlankFragment()
        top_title.text = getString(R.string.more_menu_title)
        // Navigate to more tab (white view)
        setAccountMenuItemVisibilityUnselected()
        setSendMenuItemVisibilityUnselected()
        setLearnMenuItemVisibilityUnselected()
        setPhundsMenuItemVisibilityUnselected()
    }

    private fun selectLearnMenuItem() {
        loadBlankFragment()
        top_title.text = getString(R.string.learn_menu_title)
        // Navigate to learn tab (white view)
        setAccountMenuItemVisibilityUnselected()
        setSendMenuItemVisibilityUnselected()
        setLearnMenuItemVisibilitySelected()
        setPhundsMenuItemVisibilityUnselected()
    }

    private fun selectPhundsMenuItem() {
        loadBlankFragment()
        top_title.text = getString(R.string.phunds_menu_title)
        // Navigate to phunds tab (white view)
        setAccountMenuItemVisibilityUnselected()
        setSendMenuItemVisibilityUnselected()
        setLearnMenuItemVisibilityUnselected()
        setPhundsMenuItemVisibilitySelected()
    }

    // Utility methods for making UI changes upon button selection
    private fun setAccountMenuItemVisibilitySelected() {
        account_menu_image.setImageResource(R.drawable.account_selected)
        account_menu_image.alpha = 1.toFloat()
        account_menu_title.setTextColor(getColor(R.color.capway_selected_menu))
    }
    private fun setAccountMenuItemVisibilityUnselected() {
        account_menu_image.setImageResource(R.drawable.account_unselected)
        account_menu_image.alpha = (0.4).toFloat()
        account_menu_title.setTextColor(getColor(R.color.capway_light_grey))
    }

    private fun setSendMenuItemVisibilitySelected() {
        send_menu_image.setImageResource(R.drawable.send_selected)
        send_menu_image.alpha = 1.toFloat()
        send_menu_title.setTextColor(getColor(R.color.capway_selected_menu))
    }
    private fun setSendMenuItemVisibilityUnselected() {
        send_menu_image.setImageResource(R.drawable.send_unselected)
        send_menu_image.alpha = (0.4).toFloat()
        send_menu_title.setTextColor(getColor(R.color.capway_light_grey))
    }

    private fun setLearnMenuItemVisibilitySelected() {
        learn_menu_image.setImageResource(R.drawable.learn_selected)
        learn_menu_image.alpha = 1.toFloat()
        learn_menu_title.setTextColor(getColor(R.color.capway_selected_menu))
    }
    private fun setLearnMenuItemVisibilityUnselected() {
        learn_menu_image.setImageResource(R.drawable.learn_unselected)
        learn_menu_image.alpha = (0.4).toFloat()
        learn_menu_title.setTextColor(getColor(R.color.capway_light_grey))
    }

    private fun setPhundsMenuItemVisibilitySelected() {
        phunds_menu_image.setImageResource(R.drawable.phunds_selected)
        phunds_menu_image.alpha = 1.toFloat()
        phunds_menu_title.setTextColor(getColor(R.color.capway_selected_menu))
    }
    private fun setPhundsMenuItemVisibilityUnselected() {
        phunds_menu_image.setImageResource(R.drawable.phunds_unselected)
        phunds_menu_image.alpha = (0.4).toFloat()
        phunds_menu_title.setTextColor(getColor(R.color.capway_light_grey))
    }

    private fun loadAccountFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AccountFragment(), "account")
            .commit()
    }

    private fun loadBlankFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, BlankFragment(), "blank")
            .commit()
    }

    // One downside to this older way of managing state is a difficulty using the back button
    // to track movement between pages. This bears more consideration.
    override fun onClick(v: View?) {
        when (v) {
            account_menu_item -> selectAccountMenuItem()
            send_menu_item -> selectSendMenuItem()
            more_menu_item -> selectMoreMenuItem()
            learn_menu_item -> selectLearnMenuItem()
            phunds_menu_item -> selectPhundsMenuItem()
        }
    }
}