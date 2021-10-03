package com.example.lifesum.UI

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_diary.*

class DiaryFragment : Fragment(R.layout.fragment_diary) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
glassMovement()
    }
    fun glassMovement(){
        glassAa.setOnClickListener {
            glassAa.isVisible=false
            glassA.isVisible=true
            glassA.playAnimation()
            glassA.speed=5f
        }

        glassA.setOnClickListener {
            glassAa.isVisible=true
            glassA.isVisible=false
            glassAa.playAnimation()
            glassAa.speed=5f
        }

        glassCc.setOnClickListener {
            glassCc.isVisible=false
            glassC.isVisible=true
            glassC.playAnimation()
            glassC.speed=5f
        }

        glassC.setOnClickListener {
            glassCc.isVisible=true
            glassC.isVisible=false
            glassCc.playAnimation()
            glassCc.speed=5f
        }
        glassBb.setOnClickListener {
            glassBb.isVisible=false
            glassB.isVisible=true
            glassB.playAnimation()
            glassB.speed=5f
        }

        glassB.setOnClickListener {
            glassBb.isVisible=true
            glassB.isVisible=false
            glassBb.playAnimation()
            glassBb.speed=5f
        }
        glassDd.setOnClickListener {
            glassDd.isVisible=false
            glassD.isVisible=true
            glassD.playAnimation()
            glassD.speed=5f
        }

        glassD.setOnClickListener {
            glassDd.isVisible=true
            glassD.isVisible=false
            glassDd.playAnimation()
            glassDd.speed=5f
        }
        glassEe.setOnClickListener {
            glassEe.isVisible=false
            glassE.isVisible=true
            glassE.playAnimation()
            glassE.speed=5f
        }

        glassE.setOnClickListener {
            glassEe.isVisible=true
            glassE.isVisible=false
            glassEe.playAnimation()
            glassEe.speed=5f
        }
        glassFf.setOnClickListener {
            glassFf.isVisible=false
            glassF.isVisible=true
            glassF.playAnimation()
            glassF.speed=5f
        }

        glassF.setOnClickListener {
            glassFf.isVisible=true
            glassF.isVisible=false
            glassFf.playAnimation()
            glassFf.speed=5f
        }
        glassGg.setOnClickListener {
            glassGg.isVisible=false
            glassG.isVisible=true
            glassG.playAnimation()
            glassG.speed=5f
        }

        glassG.setOnClickListener {
            glassGg.isVisible=true
            glassG.isVisible=false
            glassGg.playAnimation()
            glassGg.speed=5f
        }
    }
}
