package com.albkali.movielist;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.albkali.movielist.API.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();

     List<Movie> mMovieList;

    Context mContext;
    private int previousPosition = 0;

    public MoviesAdapter(Context context, List<Movie> movieList){

        mMovieList=movieList;
        mContext=context;
    }


    class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle;
        ImageView movieBanner;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle=(TextView)itemView.findViewById(R.id.tv_movie_title);
            movieBanner=(ImageView)itemView.findViewById(R.id.iv_movie_banner);
        }

        void bind(int listIndex){
            movieTitle.setText(mMovieList.get(listIndex).getTitle());
            Glide.with(mContext).load(mMovieList.get(listIndex).getImage())
                    .apply(RequestOptions.centerCropTransform()).into(movieBanner);


        }


    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder=new MovieViewHolder(view);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        Log.d(TAG, "#" + i);
        holder.bind(i);
        if(i > previousPosition){ // We are scrolling DOWN

            AnimationUtil.animate(holder, true);
            AnimationUtil.scaleAnimation(holder,true,0, Animation.RESTART);


        }else{ // We are scrolling UP

            AnimationUtil.animate(holder, true);
            AnimationUtil.scaleAnimation(holder,true,0, Animation.RESTART);


        }

        previousPosition = i;


    }

    @Override
    public int getItemCount() {
        if(mMovieList != null){
            return mMovieList.size(); }
        return 0;    }


    public void setMovieList(List<Movie> movieList){
        mMovieList=movieList;
        notifyDataSetChanged(); }


}
