package com.test.testapplication.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.test.myapplication.databinding.ActivityLoginBinding
import com.test.testapplication.PreferenceHelper
import com.test.testapplication.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
        configureLiveDataObservers()

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(username.text.toString(), password.text.toString())
        }
    }

    private fun configureLiveDataObservers() {
        setIsAuthenticatedLiveDataObserver()
    }

    private fun setIsAuthenticatedLiveDataObserver() {
        loginViewModel.getIsAuthenticatedLiveData().observe(this) { isAuthenticated ->
            isAuthenticated?.let {
                if (isAuthenticated) {
                    PreferenceHelper.setUserLogged(true)
                    binding.loading.visibility = View.GONE
                    goToMain()
                } else {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "There was an issue with the username and password",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initializeLiveData() {
        initializeIsAuthenticatedLiveData()
    }

    private fun initializeIsAuthenticatedLiveData() {
        loginViewModel.setIsAuthenticatedLiveData(null)
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        initializeLiveData()
    }
}
