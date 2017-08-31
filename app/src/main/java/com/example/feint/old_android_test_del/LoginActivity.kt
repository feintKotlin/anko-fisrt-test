package com.example.feint.old_android_test_del

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.feint.old_android_test_del.pojo.Response
import com.example.feint.old_android_test_del.pojo.User
import com.example.feint.old_android_test_del.utility.MD5Util
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.CommonPool

import kotlinx.coroutines.experimental.Deferred

import kotlinx.coroutines.experimental.async

import okhttp3.*

import org.jetbrains.anko.*
import org.jetbrains.anko.coroutines.experimental.bg


import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.logging.Logger

class LoginActivity : AppCompatActivity() {
    val logger = Logger.getLogger(LoginActivity::class.java.name)
    /**
     * 添加了Anko的协程依赖后，就不要再添加Kotlin的coroutines库了。估计是有冲突。
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login.onClick {
            val data: Deferred<Response<User>?> =bg{
                login(User(email = et_email.text.toString(),password = MD5Util.md5(et_pwd.text.toString())))
            }

            toast(data.await()!!.req.msg)
//            val data: Deferred<Response<User>?> = async(CommonPool){
//                login(User(email = et_email.text.toString(),password = MD5Util.md5(et_pwd.text.toString())))
//            }
//
//            toast(data.await()!!.req.msg)
        }

    }

    fun login(user:User): Response<User>?{
        return try {
            val client = OkHttpClient()
            val request = Request.Builder().url("http://www.feintkotlin.com:8686/user/sign-in").
                    post(RequestBody.create(MediaType.parse("application/json"), ObjectMapper().writeValueAsBytes(user))).build()
            val response = client.newCall(request).execute()
            ObjectMapper().readValue(response.body()!!.string(), Response<User>(data = User())::class.java)
        }catch (e:Exception){
            logger.info(e.toString())
            Response<User>(User(),Response.NOT_FOUND)
        }

    }
}


