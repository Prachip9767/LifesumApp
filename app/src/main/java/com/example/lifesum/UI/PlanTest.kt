package com.example.lifesum.UI

import MySliderImageAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.R
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_plan.*


class PlanTest : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_plan)

        imageSliderView()
        }
    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }

    private fun imageSliderView() {
        val imageList: ArrayList<String> = ArrayList()

        imageList.add("https://www.linkpicture.com/q/3_308.jpg")
        imageList.add("https://www.linkpicture.com/q/4_278.jpg")
        imageList.add("https://www.linkpicture.com/q/5_287.jpg")

        setImageInSlider(imageList, imageSlider = imageSlider)
    }
    }