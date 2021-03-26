package com.appstuddio.cleanmvvm.features.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.appstuddio.cleanmvvm.R
import com.appstuddio.cleanmvvm.core.extensions.failure
import com.appstuddio.cleanmvvm.core.extensions.observe
import com.appstuddio.cleanmvvm.core.extensions.viewModel
import com.appstuddio.cleanmvvm.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, {
                it?.let {
                    moviesAdapter.collection = it
                }
            })
            observe(movieDetail,{
                it?.let {

                }
            })
            observe(sData,{
                it?.let {
                    moviesViewModel.loadMovies()
                }
            })
            failure(failure, {
                it?.let {

                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = {
            MovieDialogFragment.newInstance(it).showDialog(childFragmentManager)
        }
        moviesViewModel.loadData()
    }
}