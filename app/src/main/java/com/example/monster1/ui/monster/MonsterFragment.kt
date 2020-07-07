package com.example.monster1.ui.monster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.monster1.R
import com.example.monster1.data.MonsterData
import com.google.android.material.tabs.TabLayout

class MonsterFragment : Fragment() {

    private lateinit var monsterViewModel: MonsterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monsterViewModel =
            ViewModelProviders.of(this).get(MonsterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_monster, container, false)
        val view_pager: ViewPager = root.findViewById(R.id.monster_view_pager)
        val tab_layout: TabLayout = root.findViewById(R.id.monster_tab)
        val monster_list: ListView = root.findViewById(R.id.test_monster_list)
        view_pager.adapter = MonsterPagerAdapter(inflater.context, MonsterData(inflater.context))
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
//        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                view_pager.currentItem = tab.position
//                Log.e("test1", ""+tab.position)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//
//            }
//        })
//        val textView: TextView = root.findViewById(R.id.text_monster)
//        monsterViewModel.text.observe(this, Observer {
//                textView.text = it
//        })
        return root
    }
}