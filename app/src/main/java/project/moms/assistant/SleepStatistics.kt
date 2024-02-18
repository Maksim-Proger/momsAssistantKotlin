package project.moms.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.moms.assistant.databinding.ActivitySleepStatisticsBinding

class SleepStatistics : AppCompatActivity() {
    private lateinit var binding : ActivitySleepStatisticsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}