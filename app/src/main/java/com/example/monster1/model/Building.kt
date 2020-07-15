package com.example.monster1.model

class Building(building_data: String, rule_map: HashMap<String, String>) {
    var name: String = ""
    var def: String = ""
    var special_rules: LinkedHashMap<String, String> = LinkedHashMap<String, String>()
}