package com.example.kbase

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.remember
import androidx.core.os.LocaleListCompat
import com.example.kbase.data.StateStore
import com.example.kbase.ui.KotlinGuideApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val store = remember { StateStore() }
            KotlinGuideApp(store) {
                val locales = LocaleListCompat.forLanguageTags(it.tag)
                AppCompatDelegate.setApplicationLocales(locales)
                this.recreate()
            }
        }
    }
}
