package com.example.monster1.ui.build_army

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.monster1.R

class BuildFragment : Fragment() {

    private lateinit var buildViewModel: BuildViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        buildViewModel =
            ViewModelProviders.of(this).get(BuildViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_build_army, container, false)
        val textView: TextView = root.findViewById(R.id.text_build_army)
        buildViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}