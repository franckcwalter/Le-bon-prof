package com.devid_academy.projetfinal.util

import android.content.Context
import android.widget.Toast

fun Context.toast(userMessage : Int, duration : Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, userMessage, duration).show()


object Role {
    const val ADMIN = 10
    const val TEACH = 20
    const val LEARN = 30
}

object Place {
    const val MY_HOME = "Mon domicile"
    const val YOUR_HOME = "Votre domicile"
    const val THIRD_PLACE = "Lieu tiers"
}



class SingleEvent<out T>(private val content : T) {

    private var hasBeenHandeled = false

    fun getContentIfNotHandeled() : T? {
        return if (hasBeenHandeled) {
            null
        }else{
            hasBeenHandeled = true
            content
        }
    }
}

