package project.moms.assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import project.moms.assistant.databinding.ActivityMainscreenBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}