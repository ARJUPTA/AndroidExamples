package com.arjupta.customauth.network;

import com.arjupta.customauth.model.Profile;
import com.arjupta.customauth.network.ApiConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.arjupta.customauth.network.response.ProfileListResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by michaellimantara on 20/3/16.
 */
public class ApiManager {

    private static ApiService apiService;
    private static Retrofit retrofit;

    private ApiManager() { }

    public static ApiService getApiClient() {
        if (apiService == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }

    public interface ApiService {

        @GET(ApiConfig.EndPoint.PROFILE)
        Call<String> getProfiles();

        @FormUrlEncoded
        @POST(ApiConfig.EndPoint.PROFILE)
        Call<String> postProfile(@Field("uid") String uid,
                                  @Field("pass") String pass);

    }
}

