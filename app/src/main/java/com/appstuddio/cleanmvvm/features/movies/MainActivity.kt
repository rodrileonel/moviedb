package com.appstuddio.cleanmvvm.features.movies

import android.content.Context
import android.content.Intent
import com.appstuddio.cleanmvvm.core.platform.BaseActivity

class MainActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun showToolbar() = false

    override fun fragment() = MoviesFragment()
}