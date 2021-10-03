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




