package com.djuranm.dontkillmyclock.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.djuranm.dontkillmyclock.databinding.ActivitySwitchBinding
import com.djuranm.dontkillmyclock.persistance.PersistenceCenter
import com.djuranm.dontkillmyclock.service.ClockForegroundService

class SwitchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySwitchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val state = PersistenceCenter.getState(applicationContext)

        binding.onOffSwitch.isChecked = state
        binding.onOffSwitch.setOnCheckedChangeListener { _, isChecked ->
            PersistenceCenter.saveState(applicationContext, isChecked)
            if (isChecked) {
                ContextCompat.startForegroundService(this, ClockForegroundService.getIntent(this))
            } else {
                stopService(ClockForegroundService.getIntent(this))
            }
        }

        if (state) {
            ContextCompat.startForegroundService(this, ClockForegroundService.getIntent(this))
        }
    }

}