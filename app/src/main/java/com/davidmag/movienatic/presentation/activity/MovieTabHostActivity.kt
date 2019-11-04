package com.davidmag.movienatic.presentation.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.adapter.MovieGenrePagerAdapter
import com.davidmag.movienatic.presentation.adapter.TabInfo
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieTabHostActivity @Inject constructor() : BaseActivity() {

    @Inject lateinit var viewModel : MovieTabHostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPresentationComponent.builder().
            application(App.instance).
            build().inject(this)

        setContentView(R.layout.activity_movie_tabhost)

        viewModel = initViewModel { viewModel }

        viewModel.getGenres().observe(this, Observer {
            val movieGenrePagerAdapter = MovieGenrePagerAdapter(
                supportFragmentManager,  arrayListOf(
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_firsttab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it[0].id!!)  }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_secondtab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it[1].id!!)  }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_thirdtab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it[2].id!!)  }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_fourthtab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it[3].id!!)  }
                    )
                ))

            val viewPager: ViewPager = findViewById(R.id.view_pager)
            viewPager.adapter = movieGenrePagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            tabs.setupWithViewPager(viewPager)
        })
    }
}