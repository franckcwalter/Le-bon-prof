package com.devid_academy.domain.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.devid_academy.domain.R

fun Context.toast(userMessage : Int, duration : Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, userMessage, duration).show()

fun Context.alertDialog(message : Int, onConfirm : ()-> Unit) {

    AlertDialog.Builder(this).apply {
        setTitle(R.string.alertdialog_title)
        setMessage(message)

        setPositiveButton(R.string.alertdialog_title_positive_answer) { _, _ ->
            onConfirm.invoke()
        }

        setNegativeButton(R.string.alertdialog_title_negative_answer) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        create()
    }.show()

}
