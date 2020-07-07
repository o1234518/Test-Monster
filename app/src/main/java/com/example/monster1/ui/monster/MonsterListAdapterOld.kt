package com.example.monster1.ui.monster

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.monster1.R
import com.example.monster1.model.Monster
import org.json.JSONObject
import java.util.ArrayList

class MonsterListAdapterOld (private val context: Context, val camp: Int) : BaseAdapter(){

    private val mLayInf: LayoutInflater = LayoutInflater.from(context)
    var mItemList: List<Map<String, Any>>? = null
    val test_data2 = "{\n" +
            "  \"Header\": {\n" +
            "    \"version\": \"1.0\",\n" +
            "    \"updateDate\": \"2019-11-26\"\n" +
            "  },\n" +
            "  \"Monster\": [\n" +
            "    {\n" +
            "      \"name\":\"DEFENDER\\u0020X\",\n" +
            "      \"race\":\"G.U.A.R.D\",\n" +
            "      \"camp\":\"protectors\",\n" +
            "      \"type\":\"alpha\",\n" +
            "      \"spd\":6,\n" +
            "      \"def\":8,\n" +
            "      \"life\":\"11_6\",\n" +
            "      \"fly\":true,\n" +
            "      \"special_rules\":[\n" +
            "          {\n" +
            "            \"title\":\"DISRUPTION\",\n" +
            "            \"context\":\"Enemy modelsattacking while within two spaces of this model lose one Boost Die on their attack rolls.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"MECHANICAL\",\n" +
            "            \"context\":\"This monster is mechanical.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"SAFEGUARD\",\n" +
            "            \"context\":\"If you are securing one or more buildings when you Power Up, you gain additional Power Die.\"\n" +
            "          }\n" +
            "        ],\n" +
            "      \"brawl\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":null\n" +
            "      },\n" +
            "      \"blast\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":4,\n" +
            "        \"range\":3,\n" +
            "        \"rule\":null\n" +
            "      },\n" +
            "      \"power\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":5,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":null\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"DEFENDER X\",\n" +
            "      \"race\":\"G.U.A.R.D\",\n" +
            "      \"camp\":\"protectors\",\n" +
            "      \"type\":\"hyper\",\n" +
            "      \"spd\":6,\n" +
            "      \"def\":8,\n" +
            "      \"life\":\"5_1\",\n" +
            "      \"fly\":true,\n" +
            "      \"special_rules\":[\n" +
            "          {\n" +
            "            \"title\":\"DISRUPTION\",\n" +
            "            \"context\":\"Enemy modelsattacking while within two spaces of this model lose one Boost Die on their attack rolls.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"ENERGY CYCLE\",\n" +
            "            \"context\":\"If you roll two or more Action Dice on an attack made by this model and the attack hits an enemy model, after the attack is resolved you can move one Action Die from your Unit Pool to your Monster Pool.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"MECHANICAL\",\n" +
            "            \"context\":\"This monster is mechanical.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"SAFEGUARD\",\n" +
            "            \"context\":\"If you are securing one or more buildings when you Power Up, you gain additional Power Die.\"\n" +
            "          }\n" +
            "        ],\n" +
            "      \"brawl\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":5,\n" +
            "        \"rule\":null\n" +
            "      },\n" +
            "      \"blast\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":5,\n" +
            "        \"range\":3,\n" +
            "        \"rule\":[\n" +
            "            {\n" +
            "              \"title\":\"BEAT BACK\",\n" +
            "              \"context\":\"If this model hits an enemy monster with this attack, after the attack is resolved you can move the monster hit up to two spaces in a straight line in any direction. During this movement, the target monster moves through and collides with buildings, unit, and hazards in its path. The target monster stops moving if it collides with another monster.\"\n" +
            "            }\n" +
            "          ]\n" +
            "      },\n" +
            "      \"power\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":6,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":null\n" +
            "      }\n" +
            "    },\n" +
            "\t    {\n" +
            "      \"name\":\"White Dajan\",\n" +
            "      \"race\":\"Empire of the Apes Monster\",\n" +
            "      \"camp\":\"protectors\",\n" +
            "      \"type\":\"alpha\",\n" +
            "      \"spd\":6,\n" +
            "      \"def\":8,\n" +
            "      \"life\":\"11_6\",\n" +
            "      \"fly\":false,\n" +
            "      \"special_rules\":[\n" +
            "          {\n" +
            "            \"title\":\"Demolisher\",\n" +
            "            \"context\":\"When this model makes an attack, gain +1 Power Die for every building destroyed as a result of it's attack.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"Steady\",\n" +
            "            \"context\":\"Enemy monsters do not roll Boost Dice when making body slam power attacks against this model.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"Wrecking Crew\",\n" +
            "            \"context\":\"While this model is in play, allied Faction units gain Demolisher.\"\n" +
            "          }\n" +
            "        ],\n" +
            "      \"brawl\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":[\n" +
            "            {\n" +
            "              \"title\":\"Fling\",\n" +
            "              \"context\":\"When this attack hits a unit, choose a building or enemy model within five spaces of the unit hit. Roll the dice in play. If the chosen model's DEF is equal to or less than the number of strikes rolled, it takes 1 damage point.\"\n" +
            "            }\n" +
            "          ]\n" +
            "      },\n" +
            "      \"blast\":{\n" +
            "        \"have\":false,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":4,\n" +
            "        \"range\":3,\n" +
            "        \"rule\":null\n" +
            "      },\n" +
            "      \"power\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":null\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"White Dajan\",\n" +
            "      \"race\":\"Empire of the Apes Monster\",\n" +
            "      \"camp\":\"protectors\",\n" +
            "      \"type\":\"hyper\",\n" +
            "      \"spd\":7,\n" +
            "      \"def\":8,\n" +
            "      \"life\":\"5_1\",\n" +
            "      \"fly\":true,\n" +
            "      \"special_rules\":[\n" +
            "          {\n" +
            "            \"title\":\"Demolisher\",\n" +
            "            \"context\":\"When this model makes an attack, gain +1 Power Die for every building destroyed as a result of it's attack.\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"title\":\"Treacherous Territory\",\n" +
            "            \"context\":\"Enemy units treat spaces within two spaces of this model as rough terrain while advancing.\"\n" +
            "          }\n" +
            "        ],\n" +
            "      \"brawl\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":8,\n" +
            "        \"blue\":6,\n" +
            "        \"rule\":[\n" +
            "            {\n" +
            "              \"title\":\"BEAT BACK\",\n" +
            "              \"context\":\"If this model hits an enemy monster with this attack, after the attack is resolved you can move the monster hit up to two spaces in a straight line in any direction. During this movement, the target monster moves through and collides with buildings, unit, and hazards in its path. The target monster stops moving if it collides with another monster.\"\n" +
            "            },\n" +
            "\t\t\t{\n" +
            "              \"title\":\"Fling\",\n" +
            "              \"context\":\"When this attack hits a unit, choose a building or enemy model within five spaces of the unit hit. Roll the dice in play. If the chosen model's DEF is equal to or less than the number of strikes rolled, it takes 1 damage point.\"\n" +
            "            }\n" +
            "          ]\n" +
            "      },\n" +
            "      \"blast\":{\n" +
            "        \"have\":false,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":5,\n" +
            "        \"range\":3,\n" +
            "        \"rule\":null\n" +
            "      },\n" +
            "      \"power\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":8,\n" +
            "        \"blue\":5,\n" +
            "        \"rule\":null\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"GORGHADRA\",\n" +
            "      \"race\":\"PLANET EATERS\",\n" +
            "      \"camp\":\"destoryers\",\n" +
            "      \"type\":\"alpha\",\n" +
            "      \"spd\":6,\n" +
            "      \"def\":9,\n" +
            "      \"life\":\"5_1\",\n" +
            "      \"fly\":false,\n" +
            "      \"special_rules\":[\n" +
            "        {\n" +
            "          \"title\":\"DEMOLISHER\",\n" +
            "          \"context\":\"When this model makes an attack, gain +1 Power Die for every building destoryed as a result of its attack.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"title\":\"RILED\",\n" +
            "          \"context\":\"When this model is damaged or destoryed by an enemy attack, you gain one Power Die.\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"brawl\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":8,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":[\n" +
            "          {\n" +
            "            \"title\":\"CHAIN REACTION\",\n" +
            "            \"context\":\"If this attack hits a unit, all unit adjacent to the target unit with DEF equal to or less than the number of strikes rolled also suffer 1 damage point as a result of this attack.\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"blast\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":4,\n" +
            "        \"blue\":4,\n" +
            "        \"range\":3,\n" +
            "        \"rule\":[\n" +
            "          {\n" +
            "            \"title\":\"DISINTEGRATE\",\n" +
            "            \"context\":\"Buildings destoryedby this attack are not replaced by rubble or hazards.\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"power\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":7,\n" +
            "        \"blue\":4,\n" +
            "        \"rule\":null\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"GORGHADRA\",\n" +
            "      \"race\":\"PLANET EATERS\",\n" +
            "      \"camp\":\"destoryers\",\n" +
            "      \"type\":\"hyper\",\n" +
            "      \"spd\":6,\n" +
            "      \"def\":8,\n" +
            "      \"life\":\"11,6\",\n" +
            "      \"fly\":false,\n" +
            "      \"special_rules\":[\n" +
            "        {\n" +
            "          \"title\":\"ANNIHILATE\",\n" +
            "          \"context\":\"This monster's attacks do super damage.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"title\":\"DEMOLISHER\",\n" +
            "          \"context\":\"When this model makes an attack, gain +1 Power Die for every building destoryed as a result of its attack.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"title\":\"RILED\",\n" +
            "          \"context\":\"When this model is damaged or destoryed by an enemy attack, you gain one Power Die.\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"title\":\"UNEARTHLY RAGE\",\n" +
            "          \"context\":\"While this model is in play, other allied models gain Riled.\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"brawl\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":9,\n" +
            "        \"blue\":5,\n" +
            "        \"rule\":[\n" +
            "          {\n" +
            "            \"title\":\"CHAIN REACTION\",\n" +
            "            \"context\":\"If this attack hits a unit, all unit adjacent to the target unit with DEF equal to or less than the number of strikes rolled also suffer 1 damage point as a result of this attack.\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"blast\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":5,\n" +
            "        \"blue\":5,\n" +
            "        \"range\":3,\n" +
            "        \"rule\":[\n" +
            "          {\n" +
            "            \"title\":\"DISINTEGRATE\",\n" +
            "            \"context\":\"Buildings destoryedby this attack are not replaced by rubble or hazards.\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"power\":{\n" +
            "        \"have\":true,\n" +
            "        \"white\":8,\n" +
            "        \"blue\":5,\n" +
            "        \"rule\":null\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"
    val test_data = context.resources.getString(R.string.test_data)
    val name_list = context.resources.getStringArray(R.array.monster_name_array)
    val race_list = context.resources.getStringArray(R.array.monster_race_array)
    var mons_list = ArrayList<Monster>()
    val hyper_mons_list = ArrayList<Monster>()
    var pro_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    var hyper_pro_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    var des_mons: HashMap<String, Monster> = HashMap<String, Monster>()
    var hyper_des_mons: HashMap<String, Monster> = HashMap<String, Monster>()

    init {
        val json_obj = JSONObject(test_data2)
        val mons_array = json_obj.getJSONArray("Monster")
        Log.e("test mons", "${mons_array.length()}")
        for(i in 0 .. mons_array.length()-1) {
            var mon_data = Monster(mons_array[i].toString())
            if (mon_data.camp.equals("protectors")) {
                if (mon_data.type.equals("alpha")) {
                    pro_mons.put(mon_data.name, mon_data)
                    mons_list.add(mon_data)
                } else if (mon_data.type.equals("hyper")) {
                    hyper_pro_mons.put(mon_data.name, mon_data)
                }
            } else if (mon_data.camp.equals("destoryers")) {
                if (mon_data.type.equals("alpha")) {
                    des_mons.put(mon_data.name, mon_data)
                    mons_list.add(mon_data)
                } else if (mon_data.type.equals("hyper")) {
                    hyper_des_mons.put(mon_data.name, mon_data)
                }
            }
        }

        Log.e("test mons2", "${mons_array.length()} , ${mons_list.size}")
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = mLayInf.inflate(R.layout.monster_group, parent, false)

        if (camp == 0) {
            mons_list = getProMonsterList()
        } else if (camp == 1) {
            mons_list = getDesMonsterList()
        }

        val monster = mons_list.get(position)

        val mon_name = v.findViewById(R.id.monster_name) as TextView
        val mon_race = v.findViewById(R.id.monster_race) as TextView
        val mon_def = v.findViewById(R.id.mon_def) as TextView
        val mon_spd = v.findViewById(R.id.mon_spd) as TextView
        val mon_fly = v.findViewById(R.id.mon_fly) as ImageView

        mon_name.setText(monster.name)
        mon_race.setText(monster.race)
        mon_def.setText(monster.def.toString())
        mon_spd.setText(monster.spd.toString())
        if(!monster.fly) {
            mon_fly.visibility = View.INVISIBLE
        }

//        if(camp == 0 && !monster.camp.equals("protectors")) {
//            v.isVisible = false
//        } else if (camp == 1 && !monster.camp.equals("destoryers")) {
//            v.isVisible = false
//        }

        Log.e("test mons3", " ${mons_list.size}")

        return v
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        var count = 0
        if (camp == 0) {
            count = pro_mons.size
        } else if (camp == 1) {
            count = des_mons.size
        }
        return count
    }

    fun getProMonsterList(): ArrayList<Monster> {
        var list = ArrayList<Monster>()
        for((k,v) in pro_mons){
            list.add(v)
        }
        Log.e("test mons4 pro", " ${list.size}")
        return list
    }

    fun getDesMonsterList(): ArrayList<Monster> {
        var list = ArrayList<Monster>()
        for((k,v) in des_mons){
            list.add(v)
        }
        Log.e("test mons4 des", " ${list.size}")
        return list
    }

}