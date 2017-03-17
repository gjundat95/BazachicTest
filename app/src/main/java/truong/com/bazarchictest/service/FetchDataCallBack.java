package truong.com.bazarchictest.service;

import java.util.List;

import truong.com.bazarchictest.model.Data;
import truong.com.bazarchictest.model.Sales;

/**
 * Created by ctruong on 16/03/2017.
 */

public interface FetchDataCallBack {
    void onFetchSuccess(List<Sales> sales);

    void onFetchFailed(Exception e);
}
