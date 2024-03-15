package project.moms.assistant.all_activities

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import project.moms.assistant.R
import project.moms.assistant.databinding.ActivityMainScreenBinding


class MainScreenActivity : AppCompatActivity(), OnScrollChangeListener {
    private var _binding : ActivityMainScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragmentMainScreen: FragmentMainScreen
    private lateinit var fragmentSleepActivity: SleepActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // region Fragments
        fragmentMainScreen = FragmentMainScreen()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fr_place, fragmentMainScreen)
            commit()
        }

        fragmentSleepActivity = SleepActivity()
        supportFragmentManager.beginTransaction().apply {
            commit()
        }

        fragmentMainScreen.setOnScrollChangeListener(this)
        fragmentSleepActivity.setOnScrollChangeListener(this)
        // endregion

        listenerButtons()
    }

    // region Тестируем анимацию
    fun showSleepFragment(image: ImageView, textView: TextView, id: Int) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(SLEEP_FRAGMENT)
            addSharedElement(image, getString(R.string.image_transition_name))
            addSharedElement(textView, getString(R.string.text_transition_name))
            replace(R.id.fr_place, SleepActivity.newInstance(id), SLEEP_FRAGMENT)
            commit()
        }
    }

    fun showMainScreenFragment(image: ImageView, textView: TextView, id: Int) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(MAIN_SCREEN)
            addSharedElement(image, getString(R.string.image_transition_name))
            addSharedElement(textView, getString(R.string.text_transition_name))
            replace(R.id.fr_place, FragmentMainScreen.newInstance(id), MAIN_SCREEN)
            commit()
        }
    }

    companion object {
        private const val SLEEP_FRAGMENT = "SLEEP_FRAGMENT"
        private const val MAIN_SCREEN = "MAIN_SCREEN"
    }
    // endregion

    /**
     * Метод отвечает за прослушивание кнопок
     */
    private fun listenerButtons() {
        /**
         * Переходим на страницу ассистента
         */
        binding.assistantButton.setOnClickListener {
            val intent: Intent = Intent(this, AssistantActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Перегруженный метод из интерфейса, который выполняет функцию слушателя
     * ScrollView из фрагментов
     */
    override fun onScrollChanged(percentageScrolled: Float) {
        animateBottomPanel(percentageScrolled)
    }

    /**
     * Метод отвечающий за смену фрагментов
     */
    fun changeFragment(view: View) {
        val fragment: Fragment = when (view.id) {
            R.id.home_button -> FragmentMainScreen()
            R.id.sleep_button -> SleepActivity()
            else -> return
        }

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fr_place, fragment)
        ft.commit()

        if (fragment is FragmentMainScreen) {
            fragment.setOnScrollChangeListener(this)
        } else if (fragment is SleepActivity) {
            fragment.setOnScrollChangeListener(this)
        }
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
            val color = ContextCompat.getColor(this, R.color.background_color)
            binding.linearLayoutButtons.setBackgroundColor(color)

            val childInLinearLayout = binding.linearLayoutButtons.childCount
            for (i in 0 until childInLinearLayout) {
                val childView = binding.linearLayoutButtons.getChildAt(i)
                childView.setBackgroundColor(color)
            }

            // Менять цвет иконки
            val buttons = listOf(binding.homeButton, binding.sleepButton, binding.diaryButton, binding.assistantButton)
            buttons.forEach { button ->
                val drawable = button.drawable
                DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.text_color))
                button.setImageDrawable(drawable)
            }

        } else {
            binding.linearLayoutButtons.setBackgroundResource(R.drawable.rounded_corners)
            val childInLinearLayout = binding.linearLayoutButtons.childCount
            for (i in 0 until childInLinearLayout) {
                val childView = binding.linearLayoutButtons.getChildAt(i)
                childView.setBackgroundResource(R.drawable.rounded_corners)
            }

            // Менять цвет иконки
            val buttons = listOf(binding.homeButton, binding.sleepButton, binding.diaryButton, binding.assistantButton)
            buttons.forEach { button ->
                val drawable = button.drawable
                DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.text_color2))
                button.setImageDrawable(drawable)
            }
        }
    }
}