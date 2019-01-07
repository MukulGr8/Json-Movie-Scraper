package com.testy.myjsonfirebase;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModel {

    private String movie;
    private int year;
    private float rating;
    private String duration;
    private String director;
    private String image;
    private String story;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }

    //For Cast we will use list coz its a array so we will use one more class for that
    //This seralized name is used becoz of gson coz it dont know what is the castList and will not find anymatch hence it will give null
    //So to overcome that problem we will use the seralized name and will make gson fool
    @SerializedName("cast")
    private List<Cast> castList;
    static class Cast{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
