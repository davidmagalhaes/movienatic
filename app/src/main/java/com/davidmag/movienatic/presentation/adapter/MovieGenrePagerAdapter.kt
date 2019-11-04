package com.davidmag.movienatic.presentation.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class MovieGenrePagerAdapter(
        fm: FragmentManager,
        private val tabs : List<TabInfo>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return tabs[position].fragment
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return tabs[position].fragment.apply {
            arguments = tabs[position].args
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].title
    }

    override fun getCount(): Int {
        return tabs.size
    }
}