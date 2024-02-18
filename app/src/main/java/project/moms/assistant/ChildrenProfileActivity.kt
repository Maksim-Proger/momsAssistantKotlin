package project.moms.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.moms.assistant.databinding.ActivityChildrenProfileBinding

class ChildrenProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChildrenProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildrenProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}