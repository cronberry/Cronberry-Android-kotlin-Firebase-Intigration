package com.blue.firebaseappdemo.DTO

class UserRegister {

    var paramList: List<ParamList>? = null
    var audienceId: Int? = null
    var apiKey: String? = null

    class ParamList {
        var paramKey: String? = null
        var paramValue: String? = null
    }
}