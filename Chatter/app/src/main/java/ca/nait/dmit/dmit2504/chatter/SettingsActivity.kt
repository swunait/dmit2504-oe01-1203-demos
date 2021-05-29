package ca.nait.dmit.dmit2504.chatter

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.preference.PreferenceManager

class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Add the SettingsFragment to this activity
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_settings_layout, SettingsFragment())
            .commit()

        val settings = PreferenceManager.getDefaultSharedPreferences(this)
        settings.registerOnSharedPreferenceChangeListener(this)

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "main_bg_color" -> {
                val settings = PreferenceManager.getDefaultSharedPreferences(this)
                val colorName : String? = settings.getString("main_bg_color","Yellow")
                val colorValue : Int = Color.parseColor(colorName)
                val layout = findViewById<FrameLayout>(R.id.activity_settings_layout)
                layout.setBackgroundColor(colorValue)

            }
            "main_bg_color_list" -> {
                val settings = PreferenceManager.getDefaultSharedPreferences(this)
                val colorName : String? = settings.getString("main_bg_color_list","Yellow")
                val colorValue : Int = Color.parseColor(colorName)
                val layout = findViewById<FrameLayout>(R.id.activity_settings_layout)
                layout.setBackgroundColor(colorValue)
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//
//        val settings = PreferenceManager.getDefaultSharedPreferences(this)
//        settings.registerOnSharedPreferenceChangeListener(this)
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        val settings = PreferenceManager.getDefaultSharedPreferences(this)
//        settings.unregisterOnSharedPreferenceChangeListener(this)
//    }
}