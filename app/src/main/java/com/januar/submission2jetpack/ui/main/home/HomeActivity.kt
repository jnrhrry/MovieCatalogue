package com.januar.submission2jetpack.ui.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.ui.adapter.SectionPageAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePagerAdapter()
    }

    private fun homePagerAdapter(){
        val mFragmentManager = supportFragmentManager
        val sectionPagerAdapter = SectionPageAdapter(this, mFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}