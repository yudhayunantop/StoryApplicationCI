package com.submission.storyapplication.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.submission.storyapplication.databinding.ActivityLoginBinding
import com.submission.storyapplication.core.utils.Resources
import com.submission.storyapplication.core.utils.Preferences
import com.submission.storyapplication.core.utils.Preferences.preferences
import com.submission.storyapplication.core.utils.Preferences.saveName
import com.submission.storyapplication.core.utils.Preferences.saveToken
import com.submission.storyapplication.core.utils.Preferences.saveUserId
import com.submission.storyapplication.main.MainActivity
import com.submission.storyapplication.register.RegisterActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val LoginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding
    private var isLoading= MutableLiveData<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility= View.GONE

        Preferences.init(this)

        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (preferences.contains("KEY_userId") &&
            preferences.contains("KEY_name") &&
            preferences.contains("key_token")
        ) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setupView()
        loadingObserver()
    }

    fun setupView() {
        binding.btnLogin.setOnClickListener {
            if (binding.edLoginEmail.text.toString().isNotEmpty() &&
                binding.edLoginPassword.text.toString().isNotEmpty()
            ) {
                checkLogin(binding.edLoginEmail.text.toString(), binding.edLoginPassword.text.toString())
            } else {
                Toast.makeText(applicationContext, "Username / password empty!!!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun checkLogin(email: String, password: String) {

            Log.e("LoginActivity", email)
            Log.e("LoginActivity", password)

            val coroutineScope = LoginViewModel.viewModelScope
            coroutineScope.launch {
                LoginViewModel.login(email, password).flowOn(
                    Dispatchers.IO
                ).collect { result ->
                    when (result) {
                        is Resources.Success -> {
                            isLoading.value=false
                            saveUserId(result.data!!.userId!!)
                            saveName(result.data!!.name!!)
                            saveToken(result.data!!.token!!)
                            startActivity(
                                Intent(this@LoginActivity, MainActivity::class.java)
                            )
                            finish()
                        }
                        is Resources.Error -> {
                            isLoading.value=false
                            Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT)
                                .show()
                            Log.e("LoginActivity", result.message.toString())
                        }
                        is Resources.Loading -> {
                            isLoading.value=true
                        }
                    }
                }
            }
    }

    private fun loadingObserver(){
        isLoading.observe(this@LoginActivity){ loadingState->
            if(loadingState){
                binding.progressBar.visibility= View.VISIBLE
                binding.btnLogin.isEnabled = false
            }else{
                binding.progressBar.visibility= View.GONE
                binding.btnLogin.isEnabled = true
            }
        }
    }
}