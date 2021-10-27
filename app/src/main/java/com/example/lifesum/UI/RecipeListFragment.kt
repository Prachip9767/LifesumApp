package com.example.lifesum.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.example.lifesum.UI.activites.RecipeReccylerView
import kotlinx.android.synthetic.main.recipe_layout.*

class RecipeListFragment : Fragment(R.layout.recipe_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_seeAllRecipe.setOnClickListener {
            startActivity(Intent(this.requireActivity(), RecipeReccylerView::class.java))
        }
        clickToSearch.setOnClickListener {
            startActivity(Intent(this.requireActivity(), RecipeReccylerView::class.java))
        }
    }

}