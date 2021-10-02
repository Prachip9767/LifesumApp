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
        imageList.add("https://www.linkpicture.com/q/WhatsApp-Image-2021-10-02-at-5.36.24-PM.jpeg")
        imageList.add("https://www.linkpicture.com/q/WhatsApp-Image-2021-10-02-at-3.17.38-PM-1.jpeg")
        imageList.add("https://www.linkpicture.com/q/WhatsApp-Image-2021-10-02-at-3.17.38-PM-2.jpeg")
        imageList.add("https://www.linkpicture.com/q/WhatsApp-Image-2021-10-02-at-3.17.38-PM-3.jpeg")
        imageList.add("https://www.linkpicture.com/q/WhatsApp-Image-2021-10-02-at-3.17.38-PM-4.jpeg")
        imageList.add("https://www.linkpicture.com/q/WhatsApp-Image-2021-10-02-at-3.17.38-PM-5.jpeg")
        setImageInSlider(imageList, imageSlider = imageSlider)
    }
    }