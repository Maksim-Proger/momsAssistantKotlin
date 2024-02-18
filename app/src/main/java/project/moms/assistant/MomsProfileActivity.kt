package project.moms.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.moms.assistant.databinding.ActivityMomsProfileBinding

class MomsProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMomsProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMomsProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}