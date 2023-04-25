package com.test.testapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.testapplication.PreferenceHelper.getUserLogged
import androidx.lifecycle.ViewModelProvider
import com.test.myapplication.databinding.ActivitySplashBinding
import com.test.testapplication.ui.login.LoginActivity
import com.test.testapplication.ui.login.LoginViewModel
import com.test.testapplication.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this).get(LoginViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        if (getUserLogged()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}