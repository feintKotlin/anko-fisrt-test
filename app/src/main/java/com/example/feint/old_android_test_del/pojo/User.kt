package com.example.feint.old_android_test_del.pojo

/**
 * Created by feint on 17/8/31.
 */
data class User(
        val id:Long=0,
        var nickname:String="",
        var password:String="",
        var email:String="",
        var protrait:String="",
        var descrip:String="",
        var tag:String="",
        var type:Int=0,
        var chk:Byte=0)