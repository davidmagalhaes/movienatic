package com.davidmag.movienatic.presentation.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.adapter.MovieGenrePagerAdapter
import com.davidmag.movienatic.presentation.adapter.TabInfo
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import com.google.android.material.tabs.TabLayout

class MovieTabHostActivity : BaseActivity() {
    val viewModel by lazy {
        ViewModelProvider(this).get(MovieTabHostViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_tabhost)

        viewModel.getGenres().observe(this, Observer {
            val movieGenrePagerAdapter = MovieGenrePagerAdapter(
                supportFragmentManager, this, arrayListOf(
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_firsttab),
                        fragment = MovieListTabFragment().apply {
                            arguments = Bundle()
                            arguments!!.putInt("genre_id", it[0].id!!)
                        }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_secondtab),
                        fragment = MovieListTabFragment().apply {
                            arguments = Bundle()
                            arguments!!.putInt("genre_id", it[1].id!!)
                        }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_thirdtab),
                        fragment = MovieListTabFragment().apply {
                            arguments = Bundle()
                            arguments!!.putInt("genre_id", it[2].id!!)
                        }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_fourthtab),
                        fragment = MovieListTabFragment().apply {
                            arguments = Bundle()
                            arguments!!.putInt("genre_id", it[3].id!!)
                        }
                    )
                ))

            val viewPager: ViewPager = findViewById(R.id.view_pager)
            viewPager.adapter = movieGenrePagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            tabs.setupWithViewPager(viewPager)
        })
    }
}