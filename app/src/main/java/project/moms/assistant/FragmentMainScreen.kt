package project.moms.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.moms.assistant.databinding.FragmentMainScreenBinding

class FragmentMainScreen : Fragment() {

    private lateinit var binding : FragmentMainScreenBinding
    private var scrollChangeListener: OnScrollChangeListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scrollViewContent.viewTreeObserver.addOnScrollChangedListener {
            val maxScroll = binding.scrollViewContent.getChildAt(0).height - binding.scrollViewContent.height
            val currentScroll = binding.scrollViewContent.scrollY
            val percentageScrolled = currentScroll.toFloat() / maxScroll.toFloat()

            // Вызов интерфейса для передачи значения прокрутки в активность
            scrollChangeListener?.onScrollChanged(percentageScrolled)
        }
    }

    fun setOnScrollChangeListener(listener: OnScrollChangeListener) {
        this.scrollChangeListener = listener
    }
}