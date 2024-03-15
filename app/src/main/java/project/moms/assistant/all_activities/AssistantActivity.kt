package project.moms.assistant.all_activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.moms.assistant.databinding.ActivityAssistantBinding

class AssistantActivity : AppCompatActivity() {
    private var _binding:  ActivityAssistantBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAssistantBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}