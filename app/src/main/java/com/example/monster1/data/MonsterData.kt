package com.example.monster1.data

import android.content.Context
import android.util.Log
import com.example.monster1.MainActivity
import com.example.monster1.R
import com.example.monster1.model.Monster
import org.json.JSONObject
import java.util.ArrayList

class MonsterData (context: Context){
    val monster_data = context.getJsonDataFromAsset("monster_data.json")
    val rules = context.getJsonDataFromAsset("rules.json")

    private val mons_map: HashMap<String, HashMap<String, Monster>> = HashMap<String, HashMap<String, Monster>>()
    private val pro_mons_map: HashMap<String, HashMap<String, Monster>> = HashMap<String, HashMap<String, Monster>>()
    private val des_mons_map: HashMap<String, HashMap<String, Monster>> = HashMap<String, HashMap<String, Monster>>()
    private val rules_map: HashMap<String, String> = HashMap<String, String>()
    private var pro_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var hyper_pro_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var des_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var hyper_des_mons: HashMap<String, Monster> = HashMap<String, Monster>()

    private var alpha_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var hyper_mons: HashMap<String, Monster> = HashMap<String, Monster>()

    private var pro_mons_list: ArrayList<Monster> = ArrayList<Monster>()
    private var des_mons_list: ArrayList<Monster> = ArrayList<Monster>()

    init {
        val rule_json_obj = JSONObject(rules)
        val rule_array = rule_json_obj.getJSONArray("Rules")
        for (i in 0 .. rule_array.length()-1) {
            var obj = rule_array.getJSONObject(i)
            var title = obj.getString("title")
            var context = obj.getString("context")
            rules_map.put(title, context)
        }

        val json_obj = JSONObject(monster_data)
        val mons_array = json_obj.getJSONArray("Monster")
        Log.e("test mons", "${mons_array.length()}")
        for(i in 0 .. mons_array.length()-1) {
            var mon_data = Monster(mons_array[i].toString(), rules_map)
            if (mon_data.camp.equals("protectors")) {
                if (mon_data.type.equals("alpha")) {
                    pro_mons.put(mon_data.name, mon_data)
                    alpha_mons.put(mon_data.name, mon_data)
                } else if (mon_data.type.equals("hyper")) {
                    hyper_pro_mons.put(mon_data.name, mon_data)
                    hyper_mons.put(mon_data.name, mon_data)
                }
            } else if (mon_data.camp.equals("destoryers")) {
                if (mon_data.type.equals("alpha")) {
                    des_mons.put(mon_data.name, mon_data)
                    alpha_mons.put(mon_data.name, mon_data)
                } else if (mon_data.type.equals("hyper")) {
                    hyper_des_mons.put(mon_data.name, mon_data)
                    hyper_mons.put(mon_data.name, mon_data)
                }
            }
//            Log.e("test mons", "${mon_data.name} , ${mon_data.race}")
        }
        mons_map.put("pro_alpha", pro_mons)
        mons_map.put("pro_hyper", hyper_pro_mons)
        mons_map.put("des_alpha", des_mons)
        mons_map.put("des_hyper", hyper_des_mons)

        pro_mons_map.set("alpha", pro_mons)
        pro_mons_map.set("hyper", hyper_pro_mons)

        des_mons_map.set("alpha", des_mons)
        des_mons_map.set("hyper", hyper_des_mons)

        for (i in pro_mons_map.get("alpha")!!.values.sortedWith(compareBy({ it.race }, { it.name }))) {
            pro_mons_list.add(i)
        }

        for (i in des_mons_map.get("alpha")!!.values.sortedWith(compareBy({ it.race }, { it.name }))) {
            des_mons_list.add(i)
        }
    }

    fun getMonsData(): HashMap<String, HashMap<String, Monster>>{
        return mons_map
    }

    fun getProMonsData(): HashMap<String, HashMap<String, Monster>>{
        return pro_mons_map
    }

    fun getDesMonsData(): HashMap<String, HashMap<String, Monster>>{
        return des_mons_map
    }

    fun getProMonsterList(): ArrayList<Monster> {
        return pro_mons_list
    }

    fun getDesMonsterList(): ArrayList<Monster> {
        return des_mons_list
    }

    fun getProMonsterByIndex(index: Int): Monster {
        return pro_mons_list.get(index)
    }

    fun getDesMonsterByIndex(index: Int): Monster {
        return des_mons_list.get(index)
    }

    fun getAlphaMonsterByName(name: String): Monster {
        return alpha_mons!!.get(name)!!
    }

    fun getHyperMonsterByName(name: String): Monster {
        return hyper_mons!!.get(name)!!
    }
}