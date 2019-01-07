package com.testy.myjsonfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayListView extends AppCompatActivity {

    public static String finalJson;
    MovieAdapter movieAdapter;
    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view);

        //Now the next coming 4 lines will make u go crazy
        //We have to set the movieadapter a list which here has no use in the coming next 4 lines and will be a kick start to the
        //movieadapter then we will set the adapter on the listView finally
        ArrayList<MovieModel> Ilist = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(this,R.layout.row_layout,Ilist);

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(movieAdapter);

        //This is our Real Starting of the code
        finalJson = getIntent().getExtras().getString("json_data");
        try {
            JSONObject parentObject = new JSONObject(finalJson);
            //Name of our json array is passed in the parentarray
            JSONArray parentArray = parentObject.getJSONArray("movies");
            //This is for retriving the whole list using the parentArray for the JSONObject
            // StringBuffer finalBufferedData = new StringBuffer();

            //When we have many value and want to set them to our model or bean then we can use list concept to do so
            List<MovieModel> movieModelsList = new ArrayList<>();
            Gson gson = new Gson();
            for(int i=0;i<parentArray.length();i++){ //We use for loop to retrive a list of value like a array

                //Looping through all the array values from i to the lenght of the parent array or root array
                JSONObject finalObject = parentArray.getJSONObject(i);

                //Setting a movieModel object to pass all the values in the movieModel setters  .. to set all values.
                MovieModel movieModel = gson.fromJson(finalObject.toString(),MovieModel.class);
//
//                movieModel.setMovie(finalObject.getString("movie"));
//                movieModel.setYear(finalObject.getInt("year"));
//                movieModel.setRating((float) finalObject.getDouble("rating"));
//                movieModel.setDirector(finalObject.getString("director"));
//                movieModel.setStory(finalObject.getString("story"));
//                movieModel.setDuration(finalObject.getString("duration"));
//                movieModel.setImage(finalObject.getString("image"));
//
//                //Now we will work for the cast which is also a array  .. u can check your json through json viewer sites
//                List<MovieModel.Cast> castList = new ArrayList<>();
//                //Looping on all the cast fields of the respective i values
//                for(int j=0;j<finalObject.getJSONArray("cast").length();j++){
//                    JSONObject castObject = finalObject.getJSONArray("cast").getJSONObject(j);
//                    //Making a movieModel Cast object so to add the data into the castlist
//                    MovieModel.Cast cast = new MovieModel.Cast();
//                    cast.setName(castObject.getString("name"));
//                    //This line is like adding the all cast object to the castlist
//                    castList.add(cast);
//                }
//                //This is nothing but a simple setter to set the cast into the movieModel
//                movieModel.setCastList(castList);
//
//                //Finally Send all the json data list to our movieModel object of the MovieModel class
//                //This line is like adding all the movieModel objects to the moviemodelList
                movieModelsList.add(movieModel);
                movieAdapter.add(movieModel);

                // return movieModelsList;

                //String movieName = finalObject.getString("movie");
                //int year = finalObject.getInt("year");
                //finalBufferedData.append(movieName+" ===>    "+year+" \n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
