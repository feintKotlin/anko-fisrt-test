package com.example.feint.old_android_test_del.utility

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by feint on 17/8/31.
 */
object MD5Util{
    fun md5(content:String)= BigInteger(1, MessageDigest.getInstance("MD5").digest(content.toByteArray())).toString(16);
}