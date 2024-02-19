package project.moms.assistant.all_activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.moms.assistant.databinding.FragmentAssistantActivityBinding

class AssistantActivity : Fragment() {

    private lateinit var binding : FragmentAssistantActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssistantActivityBinding.inflate(inflater, container, false)
        return binding.root
    }
}