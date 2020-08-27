package com.davidmag.movienatic.presentation.fragmentactivity.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.viewmodel.HomeViewModel
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.SearchView
import com.davidmag.movienatic.presentation.common.DelegatedAdapter
import com.davidmag.movienatic.presentation.common.initViewModel
import com.davidmag.movienatic.presentation.di.presentationComponent
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : HomeViewModel

    val adapter by lazy {
        DelegatedAdapter(this).apply {
            presentationComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presentationComponent.inject(this)

        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        swiper.setOnRefreshListener {
            viewModel.updateGenres()
        }

        swiper.isRefreshing = true

        home_recycler.adapter = adapter

        viewModel = initViewModel { viewModel }

        viewModel.updateImageConfigs()
        viewModel.updateGenres()

        viewModel.genres.observe(this, Observer {
            swiper.isRefreshing = false

            adapter.items = it
            adapter.notifyDataSetChanged()
        })
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