package com.januar.submission2jetpack.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.ui.main.movie.MoviesFragment
import com.januar.submission2jetpack.ui.main.tvseries.TVSeriesFragment

class SectionPageAdapter (private val context: Context, fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val TAB_TITLES = intArrayOf(R.string.tab_movie, R.string.tab_tv_series)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MoviesFragment()
            1 -> fragment = TVSeriesFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}