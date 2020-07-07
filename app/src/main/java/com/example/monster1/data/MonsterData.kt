package com.example.monster1.data

import android.content.Context
import android.util.Log
import com.example.monster1.MainActivity
import com.example.monster1.R
import com.example.monster1.model.Monster
import org.json.JSONObject
import java.util.ArrayList

class MonsterData (context: Context){
    val mon_data = context.getJsonDataFromAsset("monster_data.json")

    private val mons_map: HashMap<String, HashMap<String, Monster>> = HashMap<String, HashMap<String, Monster>>()
    private val pro_mons_map: HashMap<String, HashMap<String, Monster>> = HashMap<String, HashMap<String, Monster>>()
    private val des_mons_map: HashMap<String, HashMap<String, Monster>> = HashMap<String, HashMap<String, Monster>>()
    private var pro_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var hyper_pro_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var des_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    private var hyper_des_mons: HashMap<String, Monster> = HashMap<String, Monster>()

    init {
        val json_obj = JSONObject(mon_data)
        val mons_array = json_obj.getJSONArray("Monster")
        Log.e("test mons", "${mons_array.length()}")
        for(i in 0 .. mons_array.length()-1) {
            var mon_data = Monster(mons_array[i].toString())
            if (mon_data.camp.equals("protectors")) {
                if (mon_data.type.equals("alpha")) {
                    pro_mons.put(mon_data.name, mon_data)
                } else if (mon_data.type.equals("hyper")) {
                    hyper_pro_mons.put(mon_data.name, mon_data)
                }
            } else if (mon_data.camp.equals("destoryers")) {
                if (mon_data.type.equals("alpha")) {
                    des_mons.put(mon_data.name, mon_data)
                } else if (mon_data.type.equals("hyper")) {
                    hyper_des_mons.put(mon_data.name, mon_data)
                }
            }
//            Log.e("test mons", "${mon_data.name} , ${mon_data.race}")
        }
        mons_map.set("pro_alpha", pro_mons)
        mons_map.set("pro_hyper", hyper_pro_mons)
        mons_map.set("des_alpha", des_mons)
        mons_map.set("des_hyper", hyper_des_mons)

        pro_mons_map.set("alpha", pro_mons)
        pro_mons_map.set("hyper", hyper_pro_mons)

        des_mons_map.set("alpha", des_mons)
        des_mons_map.set("hyper", hyper_des_mons)
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
}