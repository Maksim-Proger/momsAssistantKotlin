package project.moms.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.moms.assistant.databinding.ActivityAssistantBinding

class AssistantActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAssistantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssistantBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}