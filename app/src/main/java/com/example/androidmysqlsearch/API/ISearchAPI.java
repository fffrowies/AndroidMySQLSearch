package com.example.androidmysqlsearch.API;

import android.database.Observable;

import com.example.androidmysqlsearch.Model.Person;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ISearchAPI {
    @GET("person")
    Observable<List<Person>> getPersonList();

    @POST("search")
    @FormUrlEncoded
    Observable<List<Person>> searchPerson(@Field("search") String searchQuery);
}
