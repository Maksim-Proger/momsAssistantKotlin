package project.moms.assistant.all_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import project.moms.assistant.R
import project.moms.assistant.databinding.ActivityMainScreenBinding

class MainScreenActivity : AppCompatActivity(), OnScrollChangeListener {
    private lateinit var binding : ActivityMainScreenBinding
    private lateinit var fragmentMainScreen: FragmentMainScreen
    private lateinit var fragmentAssistantActivity: AssistantActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // region Fragments
        fragmentMainScreen = FragmentMainScreen()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_place, fragmentMainScreen)
            .commit()

        fragmentMainScreen.setOnScrollChangeListener(this)
        // endregion

}

    /**
     * Перегруженный метод из интерфейса, который выполняет функцию слушателя
     * ScrollView из фрагментов
     */
    override fun onScrollChanged(percentageScrolled: Float) {
        animateBottomPanel(percentageScrolled)
    }

    /**
     * Метод отвечает за анимацию нижнего контейнера с кнопками
     */
    private fun animateBottomPanel(percentageScrolled: Float) {

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

    /**
     * Метод отвечающий за смену фрагментов
     */
    fun changeFragment(view: View) {
        val fragment: Fragment = when (view.id) {
            R.id.home_button -> FragmentMainScreen()
            R.id.sleep_button -> SleepActivity()
            R.id.assistant_button -> AssistantActivity()
            else -> return
        }

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fr_place, fragment)
        ft.commit()

        if (fragment is FragmentMainScreen) {
            fragment.setOnScrollChangeListener(this)
        }
    }
}