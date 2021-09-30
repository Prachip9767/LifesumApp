package com.example.lifesum.UI

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_d_iary.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Water.setOnClickListener {
            if (Water.isAnimating) {
                Water.reverseAnimationSpeed()
            } else {
                Water.playAnimation()
            }
        }
            Water2.setOnClickListener {
                if (Water.isAnimating) {
                    Water.reverseAnimationSpeed()
                } else if(Water2.isAnimating){
                    Water2.reverseAnimationSpeed()
                }
            }
        }
    }
