package com.davidmag.movienatic.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidmag.movienatic.BaseActivity
import com.davidmag.movienatic.R
import com.davidmag.movienatic.adapter.MovieClickListener
import com.davidmag.movienatic.adapter.MovieRecyclerViewAdapter
import com.davidmag.movienatic.repository.ResourceStatus
import com.davidmag.movienatic.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_movie_list.*

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
        viewModel.lookupUpcomingMovies()
    }

    fun initRecyclerView(){
        recycler_view.adapter = mAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(1) && !viewModel.pagesExhausted){
                    bottom_progress_bar.visibility = View.VISIBLE
                    viewModel.searchNextPage()
                }
                else{
                    bottom_progress_bar.visibility = View.GONE
                }
            }
        })
    }

    fun subscribeObservers(){
        viewModel.movies.observe(this, Observer { movies ->
            movies?.let {

                if(it.status == ResourceStatus.SUCCESS || it.status == ResourceStatus.ERROR){
                    bottom_progress_bar.visibility = View.GONE
                    top_progress_bar.visibility = View.GONE
                }

                if(it.status == ResourceStatus.ERROR){
                    Toast.makeText(this, R.string.error_connection_generic, Toast.LENGTH_LONG).show()
                }

                it.data?.let {
                    mAdapter.movieList = it
                }
            }
        })
    }

    override fun onMovieClick(v: View, pos: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", mAdapter.movieList[pos].id)

        startActivity(intent)
    }
}
