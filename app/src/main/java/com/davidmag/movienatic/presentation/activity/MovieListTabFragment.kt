package com.davidmag.movienatic.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.davidmag.movienatic.R
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.adapter.MovieClickListener
import com.davidmag.movienatic.presentation.adapter.MovieRecyclerViewAdapter
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListTabFragment : BaseFragment(), MovieClickListener {

    @Inject
    lateinit var viewModel : MovieListTabViewModel

    val mAdapter by lazy {
        MovieRecyclerViewAdapter(context!!, this)
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

        DaggerPresentationComponent.builder().
            application(App.instance).build().inject(this)

        viewModel = initViewModel { viewModel }

        recycler_view.layoutManager = GridLayoutManager(context!!, 2)
        recycler_view.adapter = mAdapter

        viewModel.getImageConfigs().observe(viewLifecycleOwner, Observer {
              mAdapter.imageConfigs = it[0]
              mAdapter.notifyDataSetChanged()
        })

        viewModel.getMovies(arguments!!.getInt("genre_id")).
                observe(viewLifecycleOwner, Observer {

            mAdapter.movieList = it
        })
    }

    override fun onMovieClick(v: View, pos: Int) {
        val intent = Intent(context, MovieDetailsActivity::class.java)

        intent.putExtra("id", mAdapter.getItemId(pos))

        startActivity(intent)
    }
}