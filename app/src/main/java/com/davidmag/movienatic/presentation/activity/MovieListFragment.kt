package com.davidmag.movienatic.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.davidmag.movienatic.R
import com.davidmag.movienatic.presentation.adapter.MovieClickListener
import com.davidmag.movienatic.presentation.adapter.MovieRecyclerViewAdapter
import com.davidmag.movienatic.presentation.common.BaseFragment
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.common.initViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

open class MovieListFragment : BaseFragment(), MovieClickListener {

    @Inject
    lateinit var viewModel : MovieListViewModel

    private val mAdapter by lazy {
        MovieRecyclerViewAdapter(this)
    }

    override fun onAttach(context: Context) {
        presentationComponent.inject(this)
        viewModel = initViewModel { viewModel }

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.activity_movie_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.adapter = mAdapter

        viewModel.imageConfigs.observe(viewLifecycleOwner, Observer {
            when(it.viewType){
                PresentationObject.VIEWTYPE_ERROR ->
                    it.exception?.printStackTrace()
                PresentationObject.VIEWTYPE_CONTENT ->
                    mAdapter.imageConfigs = it.value
            }
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            mAdapter.movieList = it
        })
    }

    override fun onMovieClick(v: View, pos: Int) {
        val intent = Intent(
            context,
            MovieDetailsActivity::class.java
        )

        intent.putExtras(viewModel.fillMovieDetailsArgs(mAdapter.getItem(pos)))

        startActivity(intent)
    }
}