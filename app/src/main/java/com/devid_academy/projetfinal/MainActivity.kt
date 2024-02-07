package com.devid_academy.projetfinal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devid_academy.domain.MyClass1
import com.devid_academy.model.MyClass2
import com.devid_academy.projetfinal.R
import com.devid_academy.ui.MyClass3
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        MyClass1.FROM_ANOTHER_MODULE
        MyClass2.FROM_ANOTHER_MODULE
        MyClass3.FROM_ANOTHER_MODULE

    }
}