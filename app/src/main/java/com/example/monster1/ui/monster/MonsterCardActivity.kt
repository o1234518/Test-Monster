package com.example.monster1.ui.monster

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.monster1.R
import com.example.monster1.data.MonsterData
import com.example.monster1.model.Monster
import kotlinx.android.synthetic.main.fragment_monster_card.*
import kotlinx.android.synthetic.main.fragment_monster_card.view.*
import kotlinx.android.synthetic.main.rule_dialog.view.*

class MonsterCardActivity : AppCompatActivity() {

    lateinit var mons_data: MonsterData
    lateinit var context: Context
    lateinit var infalInflater: LayoutInflater
    var type = "alpha"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_monster_card)
        setSupportActionBar(findViewById(R.id.toolbar))

        context = this@MonsterCardActivity
        mons_data = MonsterData(context)
        infalInflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val mons_name = intent.getStringExtra("name")
        Log.e("intent", "$mons_name")

        var convertView = infalInflater.inflate(R.layout.fragment_monster_card, null)

        val alpha_monster = mons_data.getAlphaMonsterByName(mons_name)
        val hyper_monster = mons_data.getHyperMonsterByName(mons_name)

        var monster = alpha_monster

        var check = type
        when (check) {
            "alpha" -> {
                monster = alpha_monster
            }
            "hyper" -> {
                monster = hyper_monster
            }
        }

        refreshView(monster!!)

        val mon_change_type_img = mon_go_hyper_img

        mon_change_type_img.setOnClickListener {
            when (type) {
                "alpha" -> {
                    type = "hyper"
                    monster = hyper_monster
                    refreshView(monster!!)
                }
                "hyper" -> {
                    type = "alpha"
                    monster = alpha_monster
                    refreshView(monster!!)
                }
            }

        }

    }

    fun refreshView(monster: Monster) {
        val monster_name = card_monster_name
        val monster_race = card_monster_race
        val life_lay = mon_life
        val tv_mon_spd = mon_spd
        val tv_mon_def = mon_def
        val img_mon_fly = mon_fly
        val card_lay = card_lay
//        val hyper_mon_lay = convertView!!.mon_hyper_layout
//        val mon_lay = convertView!!.mon_alpha_layout
//        val def_spd_lay = convertView!!.mon_spd_def_lay
        val mon_spe_context_lay = mon_spe_rule_context
        val brawl_atk_lay = mon_brawl_attack_lay
        val brawl_white_dice = tv_mon_brawl_white
        val brawl_blue_dice = tv_mon_brawl_blue
        val brawl_atk_rule_lay = brawl_atk_rule_lay
        val blast_atk_lay = mon_blast_attack_lay
        val blast_white_dice = tv_mon_blast_white
        val blast_blue_dice = tv_mon_blast_blue
        val blast_atk_rng = tv_mon_rng
        val blast_atk_rule_lay = blast_atk_rule_lay
        val power_atk_lay = mon_power_attack_lay
        val power_white_dice = tv_mon_power_white
        val power_blue_dice = tv_mon_power_blue
        val power_atk_rule_lay = power_atk_rule_lay

//        mon_lay.visibility = View.VISIBLE
//        hyper_mon_lay.visibility = View.GONE

//        val mon_life = (monster!!.life_upper - monster!!.life_lower) + 1
        val tv_width = getLifeTextViewWidth(type, monster)

        Log.i("life width", "${tv_width}")

        life_lay.removeAllViews()

        monster_name.text = monster.name
        monster_race.text = getString(R.string.card_race_title, monster.race)

        when (type) {
            "alpha" -> {
                for (i in monster!!.life_upper downTo monster!!.life_lower) {
                    var life_tv = TextView(context)
                    life_tv.gravity = Gravity.CENTER
                    life_tv.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        context.resources.getInteger(R.integer.monster_card_life_text_size)
                            .toFloat()
                    )
                    life_tv.width = tv_width
                    life_tv.setText("$i")
                    life_tv.setTypeface(life_tv.getTypeface(), Typeface.BOLD)
                    life_tv.setTextColor(Color.BLACK)
                    if (i != monster!!.life_lower) {
                        var params = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 0, 2, 0)
                        life_tv.layoutParams = params
                    }
                    life_lay.addView(life_tv)
                }
            }
            "hyper" -> {
                for (i in monster!!.life_upper downTo 1) {
                    var life_tv = TextView(context)
                    life_tv.gravity = Gravity.CENTER
                    life_tv.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        context.resources.getInteger(R.integer.monster_card_life_text_size)
                            .toFloat()
                    )
                    life_tv.width = tv_width
                    life_tv.setText("$i")
                    life_tv.setTypeface(life_tv.getTypeface(), Typeface.BOLD)
                    life_tv.setTextColor(Color.BLACK)
                    if (i != 1) {
                        var params = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 0, 2, 0)
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
        Log.e("test", "${monster.spd}, ${monster.def}")
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
        when (type) {
            "alpha" -> {
                card_lay.setBackgroundColor(Color.rgb(128, 255, 255))
            }
            "hyper" -> {
                card_lay.setBackgroundColor(Color.rgb(255, 255, 173))
//                card_lay.setBackgroundColor(Color.rgb(255, 255, 112))
            }
        }
    }

    fun getLifeTextViewWidth(type: String, monster: Monster): Int {
        var width = 0
        Log.i("test width", "${context.resources.configuration.screenWidthDp}")
        when (type) {
            "alpha" -> {
                width =
                    (context.resources.configuration.screenWidthDp / (monster!!.life_upper - monster!!.life_lower + 1))
                        .minus(context.resources.getInteger(R.integer.monster_card_life_width))
//                            - context.resources.getInteger(R.integer.monster_card_life_width)
            }
            "hyper" -> {
                width =
                    (context.resources.configuration.screenWidthDp / monster!!.life_upper)
                        .minus(context.resources.getInteger(R.integer.monster_card_life_width))
//                            - context.resources.getInteger(R.integer.monster_card_life_width)
            }
        }
        Log.i(
            "life width ori",
            "${width}, ${context.resources.getInteger(R.integer.monster_card_life_width)}"
        )
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
        title_tv.setTextSize(
            TypedValue.COMPLEX_UNIT_SP,
            context.resources.getInteger(R.integer.monster_card_rule_text_size).toFloat()
        )
//        title_tv.width = tv_width
        title_tv.setText(title)
        title_tv.setTypeface(title_tv.getTypeface(), Typeface.BOLD)

        if (haveText) {
            title_tv.setTypeface(title_tv.getTypeface(), Typeface.BOLD_ITALIC)
            title_tv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            title_tv.setTextColor(Color.GRAY)
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
        val item = getLayoutInflater().inflate(R.layout.rule_dialog, null)
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