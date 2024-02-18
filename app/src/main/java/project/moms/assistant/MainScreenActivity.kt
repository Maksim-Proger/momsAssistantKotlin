package project.moms.assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import project.moms.assistant.databinding.ActivityMainScreenBinding

class MainScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animateBottomPanel()
    }


    /**
     * Метод отвечает за анимацию нижнего контейнера с кнопками
     */
    private fun animateBottomPanel() {

        binding.scrollViewContent.viewTreeObserver.addOnScrollChangedListener {
            val maxScroll = binding.scrollViewContent.getChildAt(0).height - binding.scrollViewContent.height
            val currentScroll = binding.scrollViewContent.scrollY

            val percentageScrolled = currentScroll.toFloat() / maxScroll.toFloat()
            val initialPanelWidth = resources.getDimensionPixelSize(R.dimen.initial_panel_width)
            val newPanelWidth = initialPanelWidth + (percentageScrolled *
                    (resources.getDimensionPixelSize(R.dimen.max_panel_width) - initialPanelWidth)).toInt()

            val layoutParams = binding.linearLayoutButtons.layoutParams
            layoutParams.width = newPanelWidth
            binding.linearLayoutButtons.layoutParams = layoutParams

            // Данный кусок отвечает за изменение цвета контейнера и его элементов

            if (percentageScrolled >= 1.0) {
                val color = ContextCompat.getColor(this, R.color.background_app_color)
                binding.linearLayoutButtons.setBackgroundColor(color)

                val childInLinearLayout = binding.linearLayoutButtons.childCount
                for (i in 0 until childInLinearLayout) {
                    val childView = binding.linearLayoutButtons.getChildAt(i)
                    childView.setBackgroundColor(color)
                }
            } else {
                binding.linearLayoutButtons.setBackgroundResource(R.drawable.rounded_corners)
                val childInLinearLayout = binding.linearLayoutButtons.childCount
                for (i in 0 until childInLinearLayout) {
                    val childView = binding.linearLayoutButtons.getChildAt(i)
                    childView.setBackgroundResource(R.drawable.rounded_corners)
                }
            }
        }
    }



}