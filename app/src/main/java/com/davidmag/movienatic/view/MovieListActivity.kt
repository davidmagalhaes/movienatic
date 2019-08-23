package com.davidmag.movienatic.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmag.movienatic.BaseActivity
import com.davidmag.movienatic.R
import com.davidmag.movienatic.adapter.MovieClickListener
import com.davidmag.movienatic.adapter.MovieRecyclerViewAdapter
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*
import java.net.SocketTimeoutException

class MovieListActivity : BaseActivity(), MovieClickListener {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(MovieListViewModel::class.java)
    }
    val mAdapter by lazy {
        MovieRecyclerViewAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initRecyclerView()
        subscribeObservers()
//        viewModel.getUpcomingMovies().invokeOnCompletion {
//            it?.let {
//                val errorMsg = when(it){
//                    is SocketTimeoutException -> getString(R.string.error_connection_timeout)
//                    else -> getString(R.string.error_generic_explained, it.message)
//                }
//
//                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
//            }
//        }
    }

    fun initRecyclerView(){
        movie_list.adapter = mAdapter
        movie_list.layoutManager = GridLayoutManager(this, 2)
    }

    fun subscribeObservers(){
        viewModel.getMovies().observe(this, Observer { movies ->
            movies?.let {
                mAdapter.movieList = it.data!!
            }
        })
    }

    override fun onMovieClick(v: View, pos: Int) {

    }
}
