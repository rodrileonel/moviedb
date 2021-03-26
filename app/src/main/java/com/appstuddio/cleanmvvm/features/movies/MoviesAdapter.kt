package com.appstuddio.cleanmvvm.features.movies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appstuddio.cleanmvvm.R
import com.appstuddio.cleanmvvm.core.extensions.inflate
import com.appstuddio.cleanmvvm.core.extensions.loadFromUrl
import kotlinx.android.synthetic.main.row_movie.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (MovieView) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieView: MovieView, clickListener: (MovieView) -> Unit) {
            itemView.moviePoster.loadFromUrl("https://image.tmdb.org/t/p/w500${movieView.posterPath}")
            itemView.setOnClickListener { clickListener(movieView) }
        }
    }
}