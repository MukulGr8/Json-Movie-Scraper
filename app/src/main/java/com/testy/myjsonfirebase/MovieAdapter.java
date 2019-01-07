package com.testy.myjsonfirebase;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieModel> {
    private ImageView ivMovieIcon;
    private TextView tvMovie;
    private TextView tvTagline;
    private TextView tvYear;
    private TextView tvDuration;
    private TextView tvDirector;
    private RatingBar rbMovieRating;
    private TextView tvCast;
    private TextView tvStory;
    private ProgressBar progressBar;

    public MovieAdapter(Context context, int resource,List<MovieModel> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context. LAYOUT_INFLATER_SERVICE );
        convertView = inflater.inflate(R.layout.row_layout, null );

        ivMovieIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
        tvMovie = (TextView)convertView.findViewById(R.id.tvMovie);
        tvTagline = (TextView)convertView.findViewById(R.id.tvTagline);
        tvYear = (TextView)convertView.findViewById(R.id.tvYear);
        tvDuration = (TextView)convertView.findViewById(R.id.tvDuration);
        tvDirector = (TextView)convertView.findViewById(R.id.tvDirector);
        rbMovieRating = (RatingBar)convertView.findViewById(R.id.rbMovie);
        tvCast = (TextView)convertView.findViewById(R.id.tvCast);
        tvStory = (TextView)convertView.findViewById(R.id.tvStory);
        progressBar = convertView.findViewById(R.id.progressBar);

        MovieModel movieModelList = getItem(position);

        tvMovie.setText(movieModelList.getMovie());
        tvTagline.setText(movieModelList.getStory());
        tvYear.setText("Year: " + movieModelList.getYear());
        tvDuration.setText("Duration:" + movieModelList.getDuration());
        tvDirector.setText("Director:" + movieModelList.getDirector());
        rbMovieRating.setRating(movieModelList.getRating()/2);

        // Then later, when you want to display image this is for image loading using universal image downloader lib from github
        ImageLoader.getInstance().displayImage(movieModelList.getImage(), ivMovieIcon, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);

            }
        }); // Default options will be used

        //This is usde for the cast array coz it is also a list and a part of the MovieModel
        StringBuffer stringBuffer = new StringBuffer();
        for(MovieModel.Cast cast : movieModelList.getCastList()){
            stringBuffer.append(cast.getName() + ", ");
        }

        tvCast.setText("Cast:" + stringBuffer);
        tvStory.setText(movieModelList.getStory());

        return convertView;
    }
}
