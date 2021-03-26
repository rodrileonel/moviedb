package com.appstuddio.cleanmvvm.features.movies

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.appstuddio.cleanmvvm.R
import com.appstuddio.cleanmvvm.core.extensions.loadFromUrl
import com.appstuddio.cleanmvvm.core.platform.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_movie.*


class MovieDialogFragment(val movie:MovieView) : BaseDialogFragment() {

    override fun layoutId() = R.layout.dialog_fragment_movie

    override fun getTagNameDialog() = MovieDialogFragment::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivPoster.loadFromUrl("https://image.tmdb.org/t/p/w500${movie.posterPath}")
        tvTitle.text = movie.title
        tvVotes.text = "${movie.voteAverage}"
        tvOverview.text = movie.overview
        tvLaunchDate.text = movie.releaseDate
    }


    companion object{
        fun newInstance(message:MovieView) = MovieDialogFragment(message)
    }

}

