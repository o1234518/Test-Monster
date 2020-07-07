package com.example.monster1.model

import org.json.JSONObject

class AttackMode(atk_data: JSONObject){
    var white_dice: String

    var blue_dice: String

    var atk_range: String

    var atk_rule = LinkedHashMap<String, String>()

    init {
        white_dice = atk_data.getInt("white").toString()
        blue_dice = atk_data.getInt("blue").toString()
        atk_range = atk_data.optInt("range",0).toString()
        val rules = atk_data.optJSONArray("rule")
        if (rules != null) {
            for(i in 0 .. rules.length()-1) {
                var rule = JSONObject(rules[i].toString())
//                Log.e("rule", "${rule.getString("title")}, ${rule.getString("context")}")
                atk_rule.put(rule.getString("title"), rule.getString("context"))
            }
        }
    }
}