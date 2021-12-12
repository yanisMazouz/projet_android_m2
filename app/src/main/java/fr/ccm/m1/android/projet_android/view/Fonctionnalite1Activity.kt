package fr.ccm.m1.android.projet_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import fr.ccm.m1.android.projet_android.databinding.ActivityFonctionnalite1Binding
import fr.ccm.m1.android.projet_android.viewModel.Fonctionnalite1ViewModel

class Fonctionnalite1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityFonctionnalite1Binding
    private lateinit var viewModel: Fonctionnalite1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[Fonctionnalite1ViewModel::class.java]
        binding = ActivityFonctionnalite1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.versionNumero!!.text = viewModel.mApplicationVersion.toString()
        binding.buttonFonctionnalite2.setOnClickListener{goToActivity2()}
        binding.buttonFonctionnalite3.setOnClickListener{goToActivity3()}
    }

    fun goToActivity2(){
        val intent= Intent(this, Fonctionnalite2Activity::class.java)
        startActivity(intent)
    }

    fun goToActivity3(){
        val intent= Intent(this, Fonctionnalite3Activity::class.java)
        startActivity(intent)
    }


}