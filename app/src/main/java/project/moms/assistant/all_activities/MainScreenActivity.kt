package project.moms.assistant.all_activities

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import project.moms.assistant.R
import project.moms.assistant.databinding.ActivityMainScreenBinding


class MainScreenActivity : AppCompatActivity(), OnScrollChangeListener {
    private lateinit var binding : ActivityMainScreenBinding
    private lateinit var fragmentMainScreen: FragmentMainScreen
    private lateinit var fragmentSleepActivity: SleepActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // region Fragments
        fragmentMainScreen = FragmentMainScreen()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_place, fragmentMainScreen)
            .commit()

        fragmentSleepActivity = SleepActivity()
        supportFragmentManager.beginTransaction()
            .commit()

        fragmentMainScreen.setOnScrollChangeListener(this)
        fragmentSleepActivity.setOnScrollChangeListener(this)
        // endregion

        listenerButtons()
}

    /**
     * Метод отвечает за прослушивание кнопок
     */
    private fun listenerButtons() {
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
            // Получаем текущую иконку из вашего ImageView
            val homeButton = binding.homeButton.drawable
            val sleepButton = binding.sleepButton.drawable
            val diaryButton = binding.diaryButton.drawable
            val assistantButton = binding.assistantButton.drawable
            // Применяем новый цвет
            DrawableCompat.setTint(homeButton, ContextCompat.getColor(this, R.color.text_color))
            DrawableCompat.setTint(sleepButton, ContextCompat.getColor(this, R.color.text_color))
            DrawableCompat.setTint(diaryButton, ContextCompat.getColor(this, R.color.text_color))
            DrawableCompat.setTint(assistantButton, ContextCompat.getColor(this, R.color.text_color))
            // Обновляем иконку в вашем ImageView
            binding.homeButton.setImageDrawable(homeButton)
            binding.sleepButton.setImageDrawable(sleepButton)
            binding.diaryButton.setImageDrawable(diaryButton)
            binding.assistantButton.setImageDrawable(assistantButton)

        } else {
            binding.linearLayoutButtons.setBackgroundResource(R.drawable.rounded_corners)
            val childInLinearLayout = binding.linearLayoutButtons.childCount
            for (i in 0 until childInLinearLayout) {
                val childView = binding.linearLayoutButtons.getChildAt(i)
                childView.setBackgroundResource(R.drawable.rounded_corners)
            }

            // Менять цвет иконки
            // Получаем текущую иконку из вашего ImageView
            val homeButton = binding.homeButton.drawable
            val sleepButton = binding.sleepButton.drawable
            val diaryButton = binding.diaryButton.drawable
            val assistantButton = binding.assistantButton.drawable
            // Применяем новый цвет
            DrawableCompat.setTint(homeButton, ContextCompat.getColor(this, R.color.text_color2))
            DrawableCompat.setTint(sleepButton, ContextCompat.getColor(this, R.color.text_color2))
            DrawableCompat.setTint(diaryButton, ContextCompat.getColor(this, R.color.text_color2))
            DrawableCompat.setTint(assistantButton, ContextCompat.getColor(this, R.color.text_color2))
            // Обновляем иконку в вашем ImageView
            binding.homeButton.setImageDrawable(homeButton)
            binding.sleepButton.setImageDrawable(sleepButton)
            binding.diaryButton.setImageDrawable(diaryButton)
            binding.assistantButton.setImageDrawable(assistantButton)
        }
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
}