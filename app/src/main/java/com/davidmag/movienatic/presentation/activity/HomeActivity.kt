package com.davidmag.movienatic.presentation.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.viewmodel.HomeViewModel
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.SearchView
import com.davidmag.movienatic.presentation.common.BaseActivity
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.initViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presentationComponent.inject(this)

        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        swiper.setOnRefreshListener {
            viewModel.updateGenres()
        }

        viewModel = initViewModel { viewModel }

        viewModel.updateImageConfigs()
        viewModel.updateGenres()

        viewModel.genres.observe(this, Observer {
            swiper.isRefreshing = false

            it.forEach { element ->
                if(element.viewType == PresentationObject.DEFAULT_VIEWTYPE_CONTENT){
                    viewModel.updateMovieList(element.id).observe(this, Observer {

                    })
                }
                else if(element.viewType == PresentationObject.DEFAULT_VIEWTYPE_ERROR){
                    element.exception?.printStackTrace()
                }
            }
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