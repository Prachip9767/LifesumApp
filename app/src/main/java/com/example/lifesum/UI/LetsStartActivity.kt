package com.example.lifesum.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.R
import kotlinx.android.synthetic.main.activity_lets_start.*

class LetsStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_start)

        val path1 = "android.resource://com.example.lifesum/" + R.raw.m_start
        val path2 = "android.resource://com.example.lifesum/" + R.raw.m_continue

        video_view_m.setVideoPath(path1)
        video_view_m.setOnPreparedListener {
            video_view_m.start()
        }


        video_view_m.setOnCompletionListener {
            video_view_m.setVideoPath(path2)
        }

        lets_start.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
//            val chooseGoalFragment = ChooseGoalFragment()
//            this.supportFragmentManager.beginTransaction()
//                .replace(R.id.createAccountFragmentContainer, chooseGoalFragment)
//                .addToBackStack("")
//                .commit()
        }
    }
}