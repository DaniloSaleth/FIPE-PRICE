package com.example.showmetheprice.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.showmetheprice.databinding.ActivityMainBinding
import com.example.showmetheprice.ui.marca.MarcasActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startListeners()
    }

    private fun startListeners(){

        val intent = Intent(this,MarcasActivity::class.java)

        binding.cvCar.setOnClickListener {
            intent.putExtra("type","carros")
            startActivity(intent)
        }

        binding.cvMotorcycle.setOnClickListener {
            intent.putExtra("type","motos")
            startActivity(intent)
        }

        binding.cvTruck.setOnClickListener {
            intent.putExtra("type", "caminhoes")
            startActivity(intent)
        }
    }
}