package com.example.feint.old_android_test_del.pojo

/**
 * Created by feint on 17/8/31.
 */
class Response<T>(val data:T?=null,val req:Req=Response.SUCCESS){

    class Req(val code:Int=0,val msg:String="")

    companion object {
        val USER_NO_USER=Req(601,"用户不存在")
        val USER_PSD_WRONG=Req(602,"密码错误")
        val USER_NICKNAME_HAVING=Req(603,"用户名已被使用")
        val USER_EMAIL_HAVING=Req(604,"该邮箱已被使用")
        val USER_EMAIL_WRONG_FORMAT=Req(605,"邮箱格式不合法")
        val USER_NICKNAME_TOO_LONG=Req(606,"用户名过长")
        val USER_NICKNAME_SPECIAL_CHAR=Req(607,"用户名含有特殊字符")
        val SUCCESS=Req(200,"请求成功")
        val NOT_FOUND=Req(404,"找不到页面")
    }
}