package com.example.monster1.ui.unit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.monster1.R

class UnitFragment : Fragment() {

    private lateinit var unitViewModel: UnitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        unitViewModel =
            ViewModelProviders.of(this).get(UnitViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_unit, container, false)
        val textView: TextView = root.findViewById(R.id.text_unit)
        unitViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}