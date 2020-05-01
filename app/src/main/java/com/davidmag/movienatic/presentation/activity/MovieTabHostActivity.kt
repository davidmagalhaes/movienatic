package com.davidmag.movienatic.presentation.activity

import android.content.Intent
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
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.SearchView
import com.facebook.flipper.android.diagnostics.FlipperDiagnosticActivity
import kotlinx.android.synthetic.main.activity_movie_tabhost.*


class MovieTabHostActivity : BaseActivity() {

    @Inject lateinit var viewModel : MovieTabHostViewModel

    private var searchItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPresentationComponent.builder().
            application(App.instance).
            build().inject(this)

        setContentView(R.layout.activity_movie_tabhost)

        setSupportActionBar(toolbar)

        val movieGenrePagerAdapter = MovieGenrePagerAdapter(supportFragmentManager, arrayListOf())
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = movieGenrePagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        viewModel = initViewModel { viewModel }

        viewModel.updateImageConfigs().observe(this, Observer {})
        viewModel.getGenres().observe(this, Observer {
            if(it.hasReturnedData()){
                movieGenrePagerAdapter.mTabs = arrayListOf(
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_firsttab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it.data!![0].id!!)  }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_secondtab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it.data!![1].id!!)  }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_thirdtab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it.data!![2].id!!)  }
                    ),
                    TabInfo(
                        title = getString(R.string.presentation_activity_movietabhost_fourthtab),
                        fragment = MovieListTabFragment(),
                        args  = Bundle().apply { putInt("genre_id", it.data!![3].id!!)  }
                    )
                )
            }
            else {
                it.error?.printStackTrace()
            }
        })

        startActivity(Intent(applicationContext, FlipperDiagnosticActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mActionModeSearchCallback = object : ActionMode.Callback {

            private var mSearchView: SearchView? = null

            override fun onCreateActionMode(actionMode: ActionMode, menu: Menu): Boolean {
                menuInflater.inflate(R.menu.activity_movietabhost, menu)

                val menuItem =  menu.findItem(R.id.action_search)!!

                mSearchView = menuItem.actionView as SearchView
                mSearchView!!.queryHint = getString(R.string.search_hint)

                return true
            }

            override fun onPrepareActionMode(actionMode: ActionMode, menu: Menu): Boolean {
                mSearchView!!.requestFocus()
                return true
            }

            override fun onActionItemClicked(actionMode: ActionMode, menuItem: MenuItem): Boolean {
                return false
            }

            override fun onDestroyActionMode(actionMode: ActionMode) {

            }
        } as ActionMode.Callback

        startSupportActionMode(mActionModeSearchCallback)

        return true;
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

//        searchItem = menu.add(R.string.presentation_activity_movietabhost_menu_search).apply {
//            icon = getDrawable(R.drawable.ic_search_black_24dp)?.apply {
//                setTint(resources.getColor(android.R.color.white))
//            }
//            setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
//        }

        return true
    }
}