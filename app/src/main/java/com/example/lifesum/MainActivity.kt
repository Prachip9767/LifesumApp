package com.example.lifesum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_d_iary)
        Water.setOnClickListener {
            Water.playAnimation()
        }
        Water2.setOnClickListener {
            Water2.playAnimation()
        }
        Water3.setOnClickListener {
            Water3.playAnimation()
        }
        Water4.setOnClickListener {
            Water4.playAnimation()
        }
    }

}
//Prachi Pardeshi