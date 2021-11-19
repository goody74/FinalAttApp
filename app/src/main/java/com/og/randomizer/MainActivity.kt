package com.og.randomizer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

//константа для ключа сохранения состояния
private const val LAST_SELECTED_ITEM = "LAST_SELECTED_ITEM"
private val DICE_FRAGMENT = DiceFragment().javaClass.name
private val BANNER_FRAGMENT = BannerFragment().javaClass.name

class MainActivity : AppCompatActivity() {


    private var diceFragment = DiceFragment()
    private var bannerFragment= BannerFragment()


    //ранняя инициализация нижней навигации
    private lateinit var bottomNavigationMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationMenu = findViewById(R.id.bottom_navigation_menu)

        //натсроим клики по элементам нижней навигации
        bottomNavigationMenu.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
           when (item.itemId) {
                R.id.cats -> {
                    fragment =
                        savedInstanceState?.let {
                            supportFragmentManager.getFragment(it, BANNER_FRAGMENT)
                        } ?: bannerFragment
                }
                R.id.dice -> {
                    fragment =
                        savedInstanceState?.let {
                            supportFragmentManager.getFragment(it, DICE_FRAGMENT)
                        } ?: diceFragment
                }
                R.id.about -> {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:abc@xyz.com")
                    }
                    startActivity(Intent.createChooser(emailIntent, "Send feedback"))
                }

                else -> null
            }
            replaceFragment(fragment!!)
            true
        }

        //восстановление состояния нижней навиагции
        //если не сохранено то по дефолту выбрать R.id.cats
        bottomNavigationMenu.selectedItemId =
            savedInstanceState?.getInt(LAST_SELECTED_ITEM) ?: R.id.cats
    }

    //сохрнаим состояние последнего нажатого элемента нижней навигации
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(LAST_SELECTED_ITEM, bottomNavigationMenu.selectedItemId)

        //сохраняем интсанцию конкретного фрагмента
        val fragment = supportFragmentManager.fragments.last()
        supportFragmentManager.putFragment(outState, fragment.javaClass.name, fragment)
        super.onSaveInstanceState(outState)
    }

    //функция замены фрагментов с помощью supportFragmentManager
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}