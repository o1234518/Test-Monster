package com.example.monster1.ui.building

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.monster1.R

class BuildingFragment : Fragment() {

    private lateinit var buildViewModel: BuildingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        buildViewModel =
            ViewModelProviders.of(this).get(BuildingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_building, container, false)
        val textView: TextView = root.findViewById(R.id.text_building)
        buildViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}