package truong.com.bazarchictest.service;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import truong.com.bazarchictest.config.Config;
import truong.com.bazarchictest.model.Data;

/**
 * Created by ctruong on 16/03/2017.
 */

public interface APIService {

    @GET("sales/active.json?apikey=" + Config.KEY_API)
    Call<Data> getAllData();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
