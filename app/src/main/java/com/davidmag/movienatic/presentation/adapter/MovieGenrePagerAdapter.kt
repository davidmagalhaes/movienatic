package com.davidmag.movienatic.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class MovieGenrePagerAdapter(
        val fm: FragmentManager,
        tabs : List<TabInfo>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var mTabs : List<TabInfo> = tabs
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment {
        fm.beginTransaction().remove(mTabs[position].fragment).commitNowAllowingStateLoss()

        return mTabs[position].fragment.apply {
            arguments = mTabs[position].args
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTabs[position].title
    }

    override fun getCount(): Int {
        return mTabs.size
    }
}