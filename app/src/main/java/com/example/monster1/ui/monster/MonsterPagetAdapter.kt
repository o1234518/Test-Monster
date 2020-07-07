package com.example.monster1.ui.monster

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.monster1.R
import com.example.monster1.data.MonsterData
import kotlinx.android.synthetic.main.pager_item.view.*

class MonsterPagerAdapter(val context: Context, val mons_data: MonsterData) : PagerAdapter() {
    override fun isViewFromObject(view: View, o: Any): Boolean {
        return o === view
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return super.getPageTitle(position)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.i("test1", "triger instantiateItem $position")
        val view = LayoutInflater.from(container.context).inflate(R.layout.pager_item, container, false)
        container.addView(view)
        var pro_list_adapter: MonsterListAdapter
        var des_list_adapter: MonsterListAdapter
        when (position) {
            0 -> {
                pro_list_adapter = MonsterListAdapter(context, position, mons_data.getProMonsData())
                view.pager_list.setAdapter(pro_list_adapter)
            }

            1 -> {
                des_list_adapter = MonsterListAdapter(context, position, mons_data.getDesMonsData())
                view.pager_list.setAdapter(des_list_adapter)
            }
        }
        return view
    }

}