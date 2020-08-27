package com.davidmag.movienatic.presentation.adapter.genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.davidmag.movienatic.presentation.fragmentactivity.MovieListFragment
import com.davidmag.movienatic.presentation.adapter.HomeViewHolder
import com.davidmag.movienatic.presentation.common.PresentationObject
import com.davidmag.movienatic.presentation.dto.GenrePresentation
import kotlinx.android.synthetic.main.activity_home_genre_item.view.*
import java.lang.IllegalArgumentException

class MovieByGenreViewHolder(itemView: View) : HomeViewHolder(itemView) {

    private val title = itemView.genre_title

    override fun bind(supportFragmentManager: FragmentManager, presentationObject: PresentationObject){
        if(presentationObject !is GenrePresentation){
            throw IllegalArgumentException("presentationObject is not a GenrePresentation object!")
        }

        title.text = presentationObject.description

        supportFragmentManager.beginTransaction()
            .replace(
                itemView.movie_list.id,
                findOrCreateFragment(supportFragmentManager, presentationObject),
                "movieList${presentationObject.id}"
            )
            .commit()
    }

    private fun findOrCreateFragment(supportFragmentManager: FragmentManager, genrePresentation: GenrePresentation) : Fragment {
        return supportFragmentManager.findFragmentByTag(
            "movieList${genrePresentation.id}"
        ) ?: MovieListFragment().apply {
            arguments = Bundle().apply {
                putLong("genre_id", genrePresentation.id)
            }
        }
    }

}