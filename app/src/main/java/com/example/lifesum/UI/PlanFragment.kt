package com.example.lifesum.UI

import MySliderImageAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_plan.*

class PlanFragment : Fragment(R.layout.fragment_plan) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // imageSliderView()
    }

    private fun setImageInSlider(images: ArrayList<Int>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }

//private fun imageSliderView() {
//    val imageList: ArrayList<Int> = ArrayList()
//    imageList.add(R.drawable.r1)
//    imageList.add(R.drawable.r2)
//    imageList.add(R.drawable.r3)
//
//    setImageInSlider(imageList, imageSlider)
//}
    }




