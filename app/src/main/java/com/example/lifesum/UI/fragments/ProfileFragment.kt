package com.example.lifesum.UI.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.example.lifesum.UI.activites.SettingsActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile_.*


class ProfileFragment : Fragment(R.layout.fragment_profile_) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gAuth = FirebaseAuth.getInstance().currentUser?.displayName.toString()

        tvName_u.text = gAuth
        btn_settings.setOnClickListener {
            startActivity(Intent(this.requireActivity(), SettingsActivity::class.java))
        }

    }
}