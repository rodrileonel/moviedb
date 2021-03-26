package com.appstuddio.cleanmvvm.features.movies

import androidx.lifecycle.MutableLiveData
import com.appstuddio.cleanmvvm.core.interactor.UseCase
import com.appstuddio.cleanmvvm.core.platform.BaseViewModel
import com.appstuddio.cleanmvvm.features.movies.models.Movie
import com.appstuddio.cleanmvvm.features.movies.models.MovieDetail
import com.appstuddio.cleanmvvm.features.movies.usecase.GetMovieDetail
import com.appstuddio.cleanmvvm.features.movies.usecase.GetMovies
import com.appstuddio.cleanmvvm.features.movies.usecase.SetData
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies, private val getMovieDetail: GetMovieDetail, private val setData: SetData) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()
    var movieDetail: MutableLiveData<MovieDetailView> = MutableLiveData()
    var sData: MutableLiveData<Boolean> = MutableLiveData()

    fun loadData() = setData(UseCase.None()) { it.either(::handleFailure, ::handleData) }

    private fun handleData(ok: Boolean) {
        this.sData.value = ok
    }

    fun loadMovies() = getMovies(UseCase.None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.posterPath, it.title,it.voteAverage, it.releaseDate, it.overview, it.originalLanguage) }
    }

    fun loadMovie(movieId:Int) = getMovieDetail(GetMovieDetail.Params(movieId)) { it.either(::handleFailure, ::handleMovieDetail) }

    private fun handleMovieDetail(movie: MovieDetail) {
        this.movieDetail.value = MovieDetailView(
            movie.id, movie.title, movie.poster, movie.summary, movie.cast, movie.director, movie.year, movie.trailer
        )
    }
}