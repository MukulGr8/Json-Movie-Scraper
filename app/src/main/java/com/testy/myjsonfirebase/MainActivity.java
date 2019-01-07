package com.testy.myjsonfirebase;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static TextView textView;
    public static Button button;
    public static ListView listView;
    public static MovieAdapter movieAdapter;
    public static String finalJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        new BackgroundTask().execute();

// Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
           .cacheInMemory(true)
                .cacheOnDisk(true)
           .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
           .defaultDisplayImageOptions(defaultOptions)
           .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
    }

    public void parseJson(View view){
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this,DisplayListView.class);
        intent.putExtra("json_data",finalJson);
        startActivity(intent);
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader br = null;

            try {
                //Creating a url to download the data from the url
                URL url = new URL("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt");
                //Connecting to the url with the help of the httpurlconnection object
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                //Creating the inputstream to read the data from the url
                InputStream stream = httpURLConnection.getInputStream();
                //Creating a buffered reader object to fastly read the data from the url
                br = new BufferedReader(new InputStreamReader(stream));
                //Creating a string buffer or builder to store the data of the url into it
                StringBuffer buffer = new StringBuffer();
                String line="";
                //Using while loop retreving the data from the buffer reader object
                while((line = br.readLine()) !=null){
                    //Appending the string buffer everytime we found any data from the url
                    buffer.append(line);
                }
                //closing all the connections
                br.close();
                stream.close();
                httpURLConnection.disconnect();

                finalJson = buffer.toString();
                return finalJson;
                //This if for retriving the first array only using the index 0
//                JSONObject finalObject = parentArray.getJSONObject(0);
//                String movieName = finalObject.getString("movie");
//                int year = finalObject.getInt("year");

                //return finalBufferedData.toString();

                //Returning the string data to the postExecute method
               // return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String Result) {
              textView.setText(Result);
//            movieAdapter = new MovieAdapter(getApplicationContext(),R.layout.row_layout,Result);
//            listView.setAdapter(movieAdapter);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
