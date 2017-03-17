package truong.com.bazarchictest.service;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import truong.com.bazarchictest.model.Data;
import truong.com.bazarchictest.model.Sales;

/**
 * Created by ctruong on 16/03/2017.
 */

public class APIServiceImpl {
    String TAG = APIServiceImpl.class.getSimpleName();
    APIService apiService;

    public void getAllItems(final FetchDataCallBack callBack) {
        apiService = APIService.retrofit.create(APIService.class);
        Call<Data> call = apiService.getAllData();
        Log.i(TAG, "getAllItems: " + call);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.i(TAG, "onResponse: " + response.code());/*
                JSONObject jsonObject = new JSONObject(response.body().getSales());*/
                List<Sales> sales = response.body().getSales();
                Gson gson = new Gson();
                String json = gson.toJson(sales);
                Log.i(TAG, "onResponse: " + json);
                try {
                    JSONArray obj = new JSONArray(json);
                    Log.i(TAG, "onResponse JSON: " + obj);
                    callBack.onFetchSuccess(Sales.getAllSales(obj));
                    Log.i(TAG, "onResponse Callback: ok");
                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onFetchFailed(e);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e(TAG, t.toString());
                callBack.onFetchFailed(new Exception(t));
            }
        });
    }


}
