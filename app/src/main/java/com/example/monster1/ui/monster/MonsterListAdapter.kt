package com.example.monster1.ui.monster

import android.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import com.example.monster1.R
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.monster1.model.Monster
import kotlinx.android.synthetic.main.monster_child.view.*
import kotlinx.android.synthetic.main.monster_group.view.*
import kotlinx.android.synthetic.main.rule_dialog.view.*


class MonsterListAdapter(
    private val context: Context,
    val camp: Int,
    mons_data: HashMap<String, HashMap<String, Monster>>
) : BaseExpandableListAdapter() {

    val infalInflater = this.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var mons_map: HashMap<String, Monster> = HashMap<String, Monster>()
    var hyper_mons_map: HashMap<String, Monster> = HashMap<String, Monster>()
    var mons_type: HashMap<String, String> = HashMap<String, String>()
    var mons_list = ArrayList<Monster>()
    var type: String = ""

    init {
        mons_map = mons_data.get("alpha")!!
        hyper_mons_map = mons_data.get("hyper")!!

        for ( i in mons_map.values.sortedWith(compareBy({it.race}, {it.name}))) {
            mons_list.add(i)
        }

        type = "alpha"
    }


    override fun getGroup(groupPosition: Int): Any {
        return mons_list[groupPosition].name
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val group_name = getGroup(groupPosition) as String
        if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.monster_group, null)
        }
        val monster = mons_map.get(group_name)
        var tv_mons_name = convertView!!.monster_name
        var tv_mons_race = convertView!!.monster_race
        val icon = convertView!!.race_icon
        tv_mons_name.setText(monster!!.name)
        tv_mons_race.setText(monster!!.race)
//        Log.i("test img", "${group_name}")
//        Log.i("test img", "${monster.name} , ${monster.race}")
        when(monster.race) {
            "Elemental Champions" -> {
                icon.setImageResource(R.drawable.elemental_champions)
                Log.i("test img", "Elemental Champions")
            }
            "Empire of the Apes" -> {
                icon.setImageResource(R.drawable.empireofthe_apes)
                Log.i("test img", "Empire of the Apes")
            }
            "G.U.A.R.D." -> {
                icon.setImageResource(R.drawable.guard)
                Log.i("test img", "G.U.A.R.D.")
            }
            "Green Fury" -> {
                icon.setImageResource(R.drawable.green_fury)
                Log.i("test img", "Green Fury")
            }
            "Shadow Sun Syndicate" -> {
                icon.setImageResource(R.drawable.shadow_sun_syndicate)
                Log.i("test img", "Shadow Sun Syndicate")
            }
            "Terrasaurs" -> {
                icon.setImageResource(R.drawable.terrasaurs)
                Log.i("test img", "Terrasaurs")
            }
            "Tritons" -> {
                icon.setImageResource(R.drawable.tritons)
                Log.i("test img", "Tritons")
            }
            "Lords of Cthul" -> {
                icon.setImageResource(R.drawable.lords_of_cthul)
                Log.i("test img", "Lords of Cthul")}
            "Martian Menace" -> {
                icon.setImageResource(R.drawable.martian_menace)
                Log.i("test img", "Martian Menace")
            }
            "Necroscourge" -> {
                icon.setImageResource(R.drawable.necroscourge)
                Log.i("test img", "Necroscourge")
            }
            "Planet Eaters" -> {
                icon.setImageResource(R.drawable.planet_eaters)
                Log.i("test img", "Planet Eaters")
            }
            "Savage Swarm" -> {
                icon.setImageResource(R.drawable.savage_swarm)
                Log.i("test img", "Savage Swarm")
            }
            "Subterran Uprising" -> {
                icon.setImageResource(R.drawable.subterran_uprising)
                Log.i("test img", "Subterran Uprising")
            }
            "UberCorp International" -> {
                icon.setImageResource(R.drawable.uber_corp_international)
                Log.i("test img", "UberCorp International")
            }
            "Waste" -> {
                icon.setImageResource(R.drawable.waste)
                Log.i("test img", "Waste")
            }
        }

        return convertView!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mons_list[groupPosition].name
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView

        val childText = getChild(groupPosition, childPosition) as String
        val group_name = getGroup(groupPosition) as String

        if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.monster_child, null)
        }

        val alpha_monster = mons_map.get(group_name)
        val hyper_monster = hyper_mons_map.get(group_name)

        var monster = alpha_monster

        var check = mons_type.get(group_name)
        if (check.isNullOrEmpty()) {
            mons_type.put(group_name, "alpha")
        } else {
            when(check) {
                "alpha" -> {
                    monster = alpha_monster
                }
                "hyper" -> {
                    monster = hyper_monster
                }
            }
        }

        refreshView(convertView!!, monster!!)

        val mon_change_type_img = convertView!!.mon_go_hyper_img

        mon_change_type_img.setOnClickListener {
            when (mons_type.get(group_name)) {
                "alpha" -> {
                    mons_type.set(group_name, "hyper")
                    monster = hyper_monster
                    refreshView(convertView!!, monster!!)
                }
                "hyper" -> {
                    mons_type.set(group_name, "alpha")
                    monster = alpha_monster
                    refreshView(convertView!!, monster!!)
                }
            }

        }
        return convertView!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return mons_map.size
    }

    fun refreshView(convertView: View, monster: Monster) {
        val life_lay = convertView!!.mon_life
        val tv_mon_spd = convertView!!.mon_spd
        val tv_mon_def = convertView!!.mon_def
        val img_mon_fly = convertView!!.mon_fly
//        val hyper_mon_lay = convertView!!.mon_hyper_layout
        val mon_lay = convertView!!.mon_alpha_layout
        val def_spd_lay = convertView!!.mon_spd_def_lay
        val mon_spe_context_lay = convertView!!.mon_spe_rule_context
        val brawl_atk_lay = convertView!!.mon_brawl_attack_lay
        val brawl_white_dice = convertView!!.tv_mon_brawl_white
        val brawl_blue_dice = convertView!!.tv_mon_brawl_blue
        val brawl_atk_rule_lay = convertView!!.brawl_atk_rule_lay
        val blast_atk_lay = convertView!!.mon_blast_attack_lay
        val blast_white_dice = convertView!!.tv_mon_blast_white
        val blast_blue_dice = convertView!!.tv_mon_blast_blue
        val blast_atk_rng = convertView!!.tv_mon_rng
        val blast_atk_rule_lay = convertView!!.blast_atk_rule_lay
        val power_atk_lay = convertView!!.mon_power_attack_lay
        val power_white_dice = convertView!!.tv_mon_power_white
        val power_blue_dice = convertView!!.tv_mon_power_blue
        val power_atk_rule_lay = convertView!!.power_atk_rule_lay

//        mon_lay.visibility = View.VISIBLE
//        hyper_mon_lay.visibility = View.GONE

        val mon_life = (monster!!.life_upper - monster!!.life_lower) + 1
        val tv_width = getLifeTextViewWidth(mons_type.get(monster.name)!!, monster, mon_life)

        life_lay.removeAllViews()

        when (mons_type.get(monster.name)!!) {
            "alpha" -> {
                for (i in monster!!.life_upper downTo mon_life) {
                    var life_tv = TextView(context)
                    life_tv.gravity = Gravity.CENTER
                    life_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32F)
                    life_tv.width = tv_width
                    life_tv.setText("$i")
                    life_tv.setTypeface(life_tv.getTypeface(), Typeface.BOLD)
                    if (i != mon_life) {
                        var params = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 0, 10, 0)
                        life_tv.layoutParams = params
                    }
                    life_lay.addView(life_tv)
                }
            }
            "hyper" -> {
                for (i in monster!!.life_upper downTo 1) {
                    var life_tv = TextView(context)
                    life_tv.gravity = Gravity.CENTER
                    life_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32F)
                    life_tv.width = tv_width
                    life_tv.setText("$i")
                    life_tv.setTypeface(life_tv.getTypeface(), Typeface.BOLD)
                    if (i != 1) {
                        var params = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 0, 10, 0)
                        life_tv.layoutParams = params
                    }
                    life_lay.addView(life_tv)
                }
            }
        }

        mon_spe_context_lay.removeAllViews()
        for ((k, v) in monster.special_rules) {
            val title = k
            val text = v

            Log.e("monster", "${monster.name}, ${title}, ${text}")

            var haveText = !text.equals("")

            Log.e("monster2", "${haveText}")

            var title_tv = getSpeTitleTextView(title, haveText, text)
            var context_tv = getSpeContextTextView(text)
            var spe_lay = LinearLayout(context)
            var params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 5, 0, 8)
            spe_lay.background = context.getDrawable(R.drawable.tv_shape1)
            spe_lay.orientation = LinearLayout.VERTICAL
            spe_lay.layoutParams = params
            spe_lay.addView(title_tv)
//            spe_lay.addView(context_tv)
//            spe_lay.setOnClickListener {
//                getRuleAlertDialog(title, text)
//            }
            mon_spe_context_lay.addView(spe_lay)
        }

        tv_mon_spd.setText(monster.spd)
        tv_mon_def.setText(monster.def)
        if (!monster.fly) {
            img_mon_fly.visibility = View.INVISIBLE
        } else {
            img_mon_fly.visibility = View.VISIBLE
        }

        brawl_atk_rule_lay.removeAllViews()
        blast_atk_rule_lay.removeAllViews()
        power_atk_rule_lay.removeAllViews()

        if (monster.have_brawl) {
            brawl_atk_lay.visibility = View.VISIBLE
            brawl_white_dice.setText(monster.brawl.white_dice)
            brawl_blue_dice.setText(monster.brawl.blue_dice)
            if (monster.brawl.atk_rule.size > 0) {
                for ((k, v) in monster.brawl.atk_rule) {
                    val title = k
                    val text = v

                    var haveText = !text.equals("")

                    var title_tv = getSpeTitleTextView(title, haveText, text)
                    var context_tv = getSpeContextTextView(text)
                    var spe_lay = LinearLayout(context)
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 4, 0, 0)
                    spe_lay.background = context.getDrawable(R.drawable.tv_shape1)
                    spe_lay.orientation = LinearLayout.VERTICAL
                    spe_lay.layoutParams = params
                    spe_lay.addView(title_tv)
//                    spe_lay.addView(context_tv)
                    brawl_atk_rule_lay.addView(spe_lay)
                }
            }
        } else {
            brawl_atk_lay.visibility = View.GONE
        }

        if (monster.have_blast) {
            blast_atk_lay.visibility = View.VISIBLE
            blast_white_dice.setText(monster.blast.white_dice)
            blast_blue_dice.setText(monster.blast.blue_dice)
            blast_atk_rng.setText(monster.blast.atk_range)
            if (monster.blast.atk_rule.size > 0) {
                for ((k, v) in monster.blast.atk_rule) {
                    val title = k
                    val text = v

                    var haveText = !text.equals("")

                    var title_tv = getSpeTitleTextView(title, haveText, text)
                    var context_tv = getSpeContextTextView(text)
                    var spe_lay = LinearLayout(context)
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 4, 0, 0)
                    spe_lay.background = context.getDrawable(R.drawable.tv_shape1)
                    spe_lay.orientation = LinearLayout.VERTICAL
                    spe_lay.layoutParams = params
                    spe_lay.addView(title_tv)
//                    spe_lay.addView(context_tv)
                    blast_atk_rule_lay.addView(spe_lay)
                }
            }
        } else {
            blast_atk_lay.visibility = View.GONE
        }

        if (monster.have_power) {
            power_atk_lay.visibility = View.VISIBLE
            power_white_dice.setText(monster.power.white_dice)
            power_blue_dice.setText(monster.power.blue_dice)
            if (monster.power.atk_rule.size > 0) {
                for ((k, v) in monster.power.atk_rule) {
                    val title = k
                    val text = v

                    var haveText = !text.equals("")

                    var title_tv = getSpeTitleTextView(title, haveText, text)
                    var context_tv = getSpeContextTextView(text)
                    var spe_lay = LinearLayout(context)
                    var params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 4, 0, 0)
                    spe_lay.background = context.getDrawable(R.drawable.tv_shape1)
                    spe_lay.orientation = LinearLayout.VERTICAL
                    spe_lay.layoutParams = params
                    spe_lay.addView(title_tv)
//                    spe_lay.addView(context_tv)
                    power_atk_rule_lay.addView(spe_lay)
                }
            }
        } else {
            power_atk_lay.visibility = View.GONE
        }
        when (mons_type.get(monster.name)!!) {
            "alpha" -> {
                mon_lay.setBackgroundColor(Color.rgb(128, 255, 255))
            }
            "hyper" -> {
                mon_lay.setBackgroundColor(Color.rgb(255, 255, 112))
            }
        }
    }

    fun getLifeTextViewWidth(type: String, monster: Monster, mon_life: Int): Int {
        var width = 0
        when (type) {
            "alpha" -> {
                width =
                    ((context.resources.configuration.screenWidthDp * 0.8) / (monster!!.life_upper - mon_life + 1)).toInt()
            }
            "hyper" -> {
                width =
                    ((context.resources.configuration.screenWidthDp * 0.8) / monster!!.life_upper).toInt()
            }
        }
        return width
    }

    fun getSpeTitleTextView(title: String, haveText: Boolean, text: String): TextView {
        var title_tv = TextView(context)
        var text_btn = Button(context)
        var tit_params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        tit_params.setMargins(5, 3, 5, 3)
        title_tv.layoutParams = tit_params
        title_tv.gravity = Gravity.LEFT
        title_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
//        title_tv.width = tv_width
        title_tv.setText(title)
        title_tv.setTypeface(title_tv.getTypeface(), Typeface.BOLD)

        if (haveText) {
            title_tv.setTypeface(title_tv.getTypeface(), Typeface.BOLD_ITALIC)
            title_tv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            title_tv.setOnClickListener {
                getRuleAlertDialog(title, text)
            }
        }
//        if (i != mon_life) {
//            val params = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            params.setMargins(0, 0, 10, 0)
//            life_tv.layoutParams = params
//        }
        return title_tv
    }

    fun getSpeContextTextView(text: String): TextView {
        var context_tv = TextView(context)
        var con_params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        con_params.setMargins(5, 0, 5, 3)
        context_tv.layoutParams = con_params
        context_tv.gravity = Gravity.LEFT
        context_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
//        title_tv.width = tv_width
        context_tv.setText(text)
//        context_tv.setTypeface(context_tv.getTypeface(), Typeface.BOLD)
//        if (i != mon_life) {
//            val params = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            params.setMargins(0, 0, 10, 0)
//            life_tv.layoutParams = params
//        }
        return context_tv
    }

    fun getRuleAlertDialog(title: String, text: String) {
        val item = LayoutInflater.from(context).inflate(R.layout.rule_dialog, null)
        val rul_title = item.rule_title_tv
        val rul_context = item.rule_context
        val rul_con_layout = item.rule_context_layout
        var params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 0, 10, 10)
        rul_con_layout.layoutParams = params
        rul_title.setText(title)
        rul_context.setText(text)

        AlertDialog.Builder(context)
            .setView(item)
            .show()
    }
}