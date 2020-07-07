package com.example.monster1.model

import android.util.Log
import org.json.JSONObject

class Monster(mons_data: String) {
    var name: String = ""
    var race: String = ""
    var camp: String = ""
    var type: String = ""
    var spd: String = ""
    var def: String = ""
    var life: String = ""
    var life_upper: Int = 0
    var life_lower:Int = 0
    var fly: Boolean = false
    var special_rules: LinkedHashMap<String, String> = LinkedHashMap<String, String>()
    lateinit var brawl: AttackMode
    lateinit var blast: AttackMode
    lateinit var power: AttackMode
    var have_brawl = false
    var have_blast = false
    var have_power = false

    init {
        val mons_json = JSONObject(mons_data)
        this.name = mons_json.getString("name")
        this.race = mons_json.getString("race")
        this.camp = mons_json.getString("camp")
        this.type = mons_json.getString("type")
        this.spd = mons_json.getInt("spd").toString()
        this.def = mons_json.getInt("def").toString()
        this.life = mons_json.getString("life")
        this.life_upper = life.split("_")[0].toInt()
        this.life_lower = life.split("_")[1].toInt()
        this.fly = mons_json.getBoolean("fly")
        val rules = mons_json.getJSONArray("special_rules")
        for(i in 0 .. rules.length()-1) {
            var rule = JSONObject(rules[i].toString())
            special_rules.put(rule.getString("title"), rule.getString("context"))
        }
        this.have_brawl = JSONObject(mons_json.getString("brawl")).getBoolean("have")
        this.have_blast = JSONObject(mons_json.getString("blast")).getBoolean("have")
        this.have_power = JSONObject(mons_json.getString("power")).getBoolean("have")

        if (have_brawl) {
            brawl = AttackMode(JSONObject(mons_json.getString("brawl")))
        }

        if (have_blast) {
            blast = AttackMode(JSONObject(mons_json.getString("blast")))
        }

        if (have_power) {
            power = AttackMode(JSONObject(mons_json.getString("power")))
        }

    }
}