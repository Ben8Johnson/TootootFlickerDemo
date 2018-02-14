package com.example.benjohnson.tootootflickerdemo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.benjohnson.tootootflickerdemo.adapter.MainVpAdapter
import com.example.benjohnson.tootootflickerdemo.databinding.ActivityMainBinding
import com.example.benjohnson.tootootflickerdemo.fragment.homepage.HomePageFragment
import com.example.benjohnson.tootootflickerdemo.fragment.profile.ProfileFragment
import com.example.benjohnson.tootootflickerdemo.fragment.search.SearchFragment

/**
 * @Class MainActivity Used to house the apps main fragments
 */
class MainActivity : AppCompatActivity() {

    private lateinit var backStackManger: BackStackManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        backStackManger = BackStackManager(R.id.fl_main, supportFragmentManager)
        setupViewPager(binding.viewpager)
        binding.tabs.setupWithViewPager(binding.viewpager)

    }

    /**
     * Populates the viewpager with an adapter containing the HomePageFragment
     */
    private fun setupViewPager(viewPager: ViewPager) {
        val pages: MutableList<Fragment> = mutableListOf(HomePageFragment(), ProfileFragment(), SearchFragment())
        val pageTitles: MutableList<String> = resources.getStringArray(R.array.fragment_titles).toMutableList()
        val adapter = MainVpAdapter(supportFragmentManager, pages, pageTitles)
        viewPager.adapter = adapter
    }
}