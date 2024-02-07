package com.devid_academy.projetfinal.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.annotation.StringRes
import com.devid_academy.projetfinal.R

fun Context.toast(userMessage : Int, duration : Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, userMessage, duration).show()

fun Context.alertDialog(message : Int, onConfirm : ()-> Unit) {

    AlertDialog.Builder(this).apply {
        setTitle(R.string.alertdialog_title)
        setMessage(message)

        setPositiveButton(R.string.alertdialog_title_positive_answer) { _ , _ ->
            onConfirm.invoke()
        }

        setNegativeButton(R.string.alertdialog_title_negative_answer) { dialogInterface , _ ->
            dialogInterface.dismiss()
        }
        create()
    }.show()

}


object Role {
    const val ADMIN = 10
    const val TEACH = 20
    const val LEARN = 30
}

object Place {
    const val MY_HOME = "Mon domicile"
    const val YOUR_HOME = "Votre domicile"
    const val PUBLIC_PLACE = "Lieu public"
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

// domain
interface AppRes {
    fun getString(resourceId: Int): String
    fun getString(resourceId: Int, vararg args: String): String
}

// model
class AppResImpl(
    private val context: Context
) : AppRes {

    override fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    override fun getString(@StringRes resourceId: Int, vararg args: String): String {
        return context.getString(resourceId, *args)
    }
}


