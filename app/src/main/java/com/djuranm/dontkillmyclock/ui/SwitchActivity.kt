package com.djuranm.dontkillmyclock.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.djuranm.dontkillmyclock.databinding.ActivitySwitchBinding
import com.djuranm.dontkillmyclock.notification.NotificationCenter
import com.djuranm.dontkillmyclock.persistance.PersistenceCenter

class SwitchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySwitchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val state = PersistenceCenter.getState(applicationContext)
        NotificationCenter.updateNotification(applicationContext, state)
        binding.onOffSwitch.isChecked = state
        binding.onOffSwitch.setOnCheckedChangeListener { _, isChecked ->
            PersistenceCenter.saveState(applicationContext, isChecked)
            NotificationCenter.updateNotification(applicationContext, isChecked)
        }
    }

}