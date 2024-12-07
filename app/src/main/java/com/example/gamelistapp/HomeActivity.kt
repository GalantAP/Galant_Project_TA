package com.example.gamelistapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnPcGames = findViewById<Button>(R.id.btnPcGames)
        val btnConsoleGames = findViewById<Button>(R.id.btnConsoleGames)
        val btnMobileGames = findViewById<Button>(R.id.btnMobileGames)

        btnPcGames.setOnClickListener {
            replaceFragment(PcGamesFragment())
        }

        btnConsoleGames.setOnClickListener {
            replaceFragment(ConsoleGamesFragment())
        }

        btnMobileGames.setOnClickListener {
            replaceFragment(MobileGamesFragment())
        }

        replaceFragment(PcGamesFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
