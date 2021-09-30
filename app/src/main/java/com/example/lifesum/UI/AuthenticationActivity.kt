package com.example.lifesum.UI

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.R
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestWindowFeature(1)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        setContentView(R.layout.activity_authentication)



        window.statusBarColor = Color.BLACK
        val path = "android.resource://com.example.lifesum/" + R.raw.spl_ls
        val uri = Uri.parse(path)
        video_view.setVideoURI(uri)

        video_view.setOnPreparedListener { mp ->
            mp.start()
        }

        video_view.setOnCompletionListener { mp ->
            mp.start()
        }

        tv_btn_login.setOnClickListener {
            viewSignInBottomSheet()
        }

        btn_sign_up.setOnClickListener {
            startActivity(Intent(this, LetsStartActivity::class.java))
        }
    }

    private fun viewSignInBottomSheet() {
        val signInBottomSheet = BottomSheetSignIn()
        signInBottomSheet.show(supportFragmentManager, signInBottomSheet.tag)
    }


}