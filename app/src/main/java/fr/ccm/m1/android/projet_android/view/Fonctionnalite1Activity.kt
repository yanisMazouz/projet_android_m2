package fr.ccm.m1.android.projet_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.ccm.m1.android.projet_android.databinding.ActivityFonctionnalite1Binding

class Fonctionnalite1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityFonctionnalite1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFonctionnalite1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}