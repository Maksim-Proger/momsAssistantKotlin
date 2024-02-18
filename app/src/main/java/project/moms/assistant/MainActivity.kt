package project.moms.assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import project.moms.assistant.databinding.ActivityMainscreenBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animateBottomPanel()
    }


    /**
     * Метод отвечает за анимацию нижнего контейнера с кнопками
     */
    private fun animateBottomPanel() {

        // Устанавливаем отступы (padding) для linearLayoutButtons
        val padding = resources.getDimensionPixelSize(R.dimen.padding)
        binding.linearLayoutButtons.setPadding(padding, padding,padding,padding)

        binding.scrollViewContent.viewTreeObserver.addOnScrollChangedListener {
            val maxScroll = binding.scrollViewContent.getChildAt(0).height -
                    binding.scrollViewContent.height
            val currentScroll = binding.scrollViewContent.scrollY

            val percentageScrolled = currentScroll.toFloat() / maxScroll.toFloat()
            val initialPanelWidth = resources.getDimensionPixelSize(R.dimen.initial_panel_width)
            val newPanelWidth = initialPanelWidth + (percentageScrolled * (resources.getDimensionPixelSize(R.dimen.max_panel_width) - initialPanelWidth)).toInt()

            val layoutParams = binding.linearLayoutButtons.layoutParams
            layoutParams.width = newPanelWidth
            binding.linearLayoutButtons.layoutParams = layoutParams

//            if (percentageScrolled >= 1.0) {
//                val color = ContextCompat.getColor(this, R.color.background_app_color)
//                binding.linearLayoutButtons.setBackgroundColor(color)
//
//            } else {
//                val color = ContextCompat.getColor(this, R.color.dark_app_color)
//                binding.linearLayoutButtons.setBackgroundColor(color)
//            }
        }
    }



}