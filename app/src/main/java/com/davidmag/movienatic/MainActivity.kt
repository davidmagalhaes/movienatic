package com.davidmag.movienatic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.davidmag.movienatic.rest.movie.MovieApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            MovieApiClient.getUpcomingMovies().invokeOnCompletion { it?.let { runOnUiThread {
                handleError(it)
            } } }
        }
    }

    fun handleError(e : Throwable){
        AlertDialog.Builder(this).setMessage(e.message).create().show()
    }
}
