package fr.ccm.m1.android.projet_android.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import fr.ccm.m1.android.projet_android.databinding.ActivityFonctionnalite1Binding
import fr.ccm.m1.android.projet_android.databinding.ActivityFonctionnalite3Binding
import fr.ccm.m1.android.projet_android.viewModel.Fonctionnalite3ViewModel

class Fonctionnalite3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityFonctionnalite3Binding
    private lateinit var mViewModel: Fonctionnalite3ViewModel

    private var mObserverUser = Observer<FirebaseUser> {
        if(it != null ){
            binding.info.text = "Email : "+it.email + " \nId : "+ it.uid
        }else{
            binding.info.text = "Non connecté"
        }
    }

    private var mObserverError = Observer<Int> {
        afficheError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFonctionnalite3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mViewModel = ViewModelProvider(this)[Fonctionnalite3ViewModel::class.java]
        binding.login.setOnClickListener { login() }
        binding.register.setOnClickListener { register() }
        binding.logout.setOnClickListener { mViewModel.disconnect() }
    }


    private fun isEmptyFields(): Boolean {
        return TextUtils.isEmpty(binding.email.text.toString()) || TextUtils.isEmpty(binding.password.text.toString())
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mCurrentUser.observe(this, mObserverUser)
        mViewModel.mErrorProcess.observe(this, mObserverError)
    }


    override fun onStop() {
        mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)
        super.onStop()
    }

    private fun login() {
        if (!isEmptyFields()) {
            mViewModel.loginUser(
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        }else{
            Toast.makeText(this, "Email ou mdp vide !", Toast.LENGTH_SHORT).show()
        }
    }


    private fun register() {
        if (!isEmptyFields()) {
            mViewModel.registerNewUser(
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        }else{
            Toast.makeText(this, "Email ou mdp vide !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun afficheError(code: Int) {
        when (code) {
            5 -> Toast.makeText(this, "Déconnecté", Toast.LENGTH_SHORT).show()
            9 -> Toast.makeText(this, "Utilisateur null!", Toast.LENGTH_SHORT).show()
            10 -> Toast.makeText(this, "Erreur lors de la création du profil", Toast.LENGTH_SHORT)
                .show()
            11 -> Toast.makeText(this, "Erreur lors de la connexion", Toast.LENGTH_SHORT).show()
        }
    }
}