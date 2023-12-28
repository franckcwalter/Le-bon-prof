package com.devid_academy.projetfinal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devid_academy.projetfinal.R

class MainActivity : AppCompatActivity() {

   //  private val activityViewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}