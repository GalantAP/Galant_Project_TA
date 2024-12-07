package com.example.gamelistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnGameList = findViewById<Button>(R.id.btnGameList)
        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)

        // Aksi ketika tombol "Go to Game List" ditekan
        btnGameList.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Contoh: jika Anda ingin menampilkan gambar setelah tombol ditekan
        // imageView1.visibility = View.VISIBLE
        // imageView2.visibility = View.VISIBLE
    }
}
