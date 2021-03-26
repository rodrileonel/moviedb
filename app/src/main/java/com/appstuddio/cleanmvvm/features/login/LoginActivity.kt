package com.appstuddio.cleanmvvm.features.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appstuddio.cleanmvvm.R
import com.appstuddio.cleanmvvm.core.extensions.goToActivity
import com.appstuddio.cleanmvvm.core.extensions.toast
import com.appstuddio.cleanmvvm.features.movies.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            if (validateUser("${etUser.text}") && validatePassword("${etPassword.text}"))
                goToActivity<MainActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            else
                toast("Credenciales Invalidas")
        }
    }

    fun validateUser(user:String):Boolean{
        return user == "Admin"
    }

    fun validatePassword(pw:String):Boolean{
        return pw == "Password*123"
    }


}