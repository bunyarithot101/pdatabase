package com.bbbdev.pokebase

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

//        try {
            //this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//
//        }



        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.topAppBar))

    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.menu_1 -> {
//                //newGame()
//
//                Timber.i("option clicked")
//                true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

}