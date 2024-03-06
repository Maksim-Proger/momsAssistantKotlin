package project.moms.assistant.all_activities

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import project.moms.assistant.R
import project.moms.assistant.databinding.FragmentSleepActivityBinding
import project.moms.assistant.model.DateTimeClass

class SleepActivity : Fragment() {
    private var _binding : FragmentSleepActivityBinding? = null
    private val binding get() = _binding!!
    private var scrollChangeListener: OnScrollChangeListener? = null
    private val dateTimeClass = DateTimeClass()

    // Тестируем анимацию
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.grid_transition)
    }
    companion object {
        private const val ID = "ID"

        fun newInstance(id: Int): SleepActivity {
            val args = Bundle()
            args.putInt(ID, id)
            val fragment = SleepActivity()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSleepActivityBinding.inflate(inflater, container, false)

        listenerButtons()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем анимацию к кнопкам
        val slideFromLeft = AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_from_left)
        val slideFromRight = AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_from_right)
        binding.statisticsButton.startAnimation(slideFromRight)
        binding.settingsButton.startAnimation(slideFromRight)
        binding.addDreamButton.startAnimation(slideFromRight)
        binding.fellAsleepButton.startAnimation(slideFromLeft)
        binding.wokeUpButton.startAnimation(slideFromLeft)

        // TODO Надо подписать что мы тут делаем
        binding.scrollViewContent.viewTreeObserver.addOnScrollChangedListener {
            val maxScroll = binding.scrollViewContent.getChildAt(0).height -
                    binding.scrollViewContent.height
            val currentScroll = binding.scrollViewContent.scrollY
            val percentageScrolled = currentScroll.toFloat() / maxScroll.toFloat()

            // Вызов интерфейса для передачи значения прокрутки в активность
            scrollChangeListener?.onScrollChanged(percentageScrolled)
        }
    }

    fun setOnScrollChangeListener(listener: OnScrollChangeListener) {
        this.scrollChangeListener = listener
    }

    private fun listenerButtons() {
        binding.fellAsleepButton.setOnClickListener {
            binding.fellAsleepMaterialButton.text = dateTimeClass.currentTime()
        }

        binding.wokeUpButton.setOnClickListener {
            binding.wokeUpMaterialButton.text = dateTimeClass.currentTime()
        }
    }

}