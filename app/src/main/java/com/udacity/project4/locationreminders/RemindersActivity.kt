package com.udacity.project4.locationreminders

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.udacity.project4.R

//import kotlinx.android.synthetic.main.activity_reminders.*


/**
 * The RemindersActivity that holds the reminders fragments
 */
class RemindersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
    }

//    override fun onBackPressed() {
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val nav_host_fragment = findNavController(R.id.nav_host_fragment)
                return true
            }

            R.id.logout -> {
                AuthUI.getInstance().signOut(this.baseContext)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
