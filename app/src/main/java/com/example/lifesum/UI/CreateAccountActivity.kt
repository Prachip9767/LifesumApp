package com.example.lifesum.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.R
import com.example.lifesum.create_account_fragments.ChooseGoalFragment

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val chooseGoalFragment = ChooseGoalFragment()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
            )
            .replace(R.id.createAccountFragmentContainer, chooseGoalFragment)
            .commit()

    }
}
