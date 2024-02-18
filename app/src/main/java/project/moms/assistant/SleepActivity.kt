package project.moms.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.moms.assistant.databinding.ActivitySleepBinding

class SleepActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySleepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}