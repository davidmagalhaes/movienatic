package com.davidmag.movienatic.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidmag.movienatic.R
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.adapter.MovieClickListener
import com.davidmag.movienatic.presentation.adapter.MovieRecyclerViewAdapter
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

open class MovieListTabFragment : BaseFragment(), MovieClickListener {

    @Inject
    lateinit var viewModel : MovieListTabViewModel

    val mAdapter by lazy {
        MovieRecyclerViewAdapter(context!!, this)
    }

    override fun onAttach(context: Context) {
        DaggerPresentationComponent.builder().
            application(App.instance).build().inject(this)

        viewModel = initViewModel { viewModel }

        viewModel.getMovies(arguments!!.getInt("genre_id"))
        viewModel.getImageConfigs()

        viewModel.updateMovieList(arguments!!.getInt("genre_id")).
            observe(viewLifecycleOwner, Observer {})

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = GridLayoutManager(context!!, 2) as RecyclerView.LayoutManager?
        recycler_view.adapter = mAdapter

        viewModel.imageConfigs.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                mAdapter.imageConfigs = it[0]
                mAdapter.notifyDataSetChanged()
            }
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            mAdapter.movieList = it
        })
    }

    override fun onMovieClick(v: View, pos: Int) {
        val intent = Intent(context, MovieDetailsActivity::class.java)

        intent.putExtra("id", mAdapter.getItemId(pos))

        startActivity(intent)
    }
}