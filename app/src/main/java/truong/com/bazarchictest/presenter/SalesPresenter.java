package truong.com.bazarchictest.presenter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import truong.com.bazarchictest.adapter.SaleAdapter;
import truong.com.bazarchictest.model.Sales;
import truong.com.bazarchictest.service.APIServiceImpl;
import truong.com.bazarchictest.service.FetchDataCallBack;

/**
 * Created by ctruong on 16/03/2017.
 */

public class SalesPresenter extends MainPresenter {
    String TAG = SalesPresenter.class.getSimpleName();
    private RecyclerView.LayoutManager layoutManager;
    private SaleAdapter saleAdapter;
    private ArrayList<Sales> salesList;
    APIServiceImpl apiServiceImpl;

    public SalesPresenter(Context context, RecyclerView view) {
        super(context);
        apiServiceImpl = new APIServiceImpl();
        layoutManager = new GridLayoutManager(context, 1);
        view.setLayoutManager(layoutManager);
        salesList = new ArrayList<>();
        saleAdapter = new SaleAdapter(salesList, context);
        view.setAdapter(saleAdapter);
    }

    public void fetchAll() {
        Log.i(TAG, "fetchAll: " + salesList);
        apiServiceImpl.getAllItems(new FetchDataCallBack() {
            @Override
            public void onFetchSuccess(List<Sales> sales) {
                Log.i(TAG, sales.toString());
                Gson gson = new Gson();
                String json = gson.toJson(sales);
                Log.i(TAG, "onFetchSuccess: " + json);
                Type listType = new TypeToken<List<Sales>>(){}.getType();
                try {
                    JSONArray obj = new JSONArray(json);
                    List<Sales> myList = gson.fromJson(obj.toString(), listType);
                    salesList.addAll(myList);
                    saleAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFetchFailed(Exception e) {

            }
        });
    }
}
