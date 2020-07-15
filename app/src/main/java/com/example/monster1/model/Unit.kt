package com.example.monster1.model


class Unit(unit_data: String, rule_map: HashMap<String, String>) {
    var name: String = ""
    var race: String = ""
    var camp: String = ""
    var spd: Int = 0
    var def: Int = 0
    var cost: Int = 0
    var type: String = ""
    var fly: Boolean = false
    var special_rules: LinkedHashMap<String, String> = LinkedHashMap<String, String>()
    lateinit var brawl: AttackMode
    lateinit var blast: AttackMode
    var have_brawl = false
    var have_blast = false
}