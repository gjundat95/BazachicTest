package truong.com.bazarchictest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ctruong on 16/03/2017.
 */

public class Data {

    @SerializedName("sales")
    @Expose
    private List<Sales> sales = null;

    public Data(List<Sales> sales) {
        this.sales = sales;
    }

    public Data() {
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }
}
