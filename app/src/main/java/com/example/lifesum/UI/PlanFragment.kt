package com.example.lifesum.UI

import MySliderImageAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lifesum.R
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_plan.*

class PlanFragment : Fragment(R.layout.fragment_plan) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
    imageList.add("https://www.linkpicture.com/q/6_233.jpg")

    setImageInSlider(imageList, imageSlider = imageSlider)
}
    }




