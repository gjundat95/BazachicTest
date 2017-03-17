package truong.com.bazarchictest.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctruong on 16/03/2017.
 */

public class Sales {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sale")
    @Expose
    private String sale;
    @SerializedName("sale_key")
    @Expose
    private String saleKey;
    @SerializedName("store")
    @Expose
    private String store;
    @SerializedName("sale_url")
    @Expose
    private String saleUrl;
    @SerializedName("image_urls")
    @Expose
    private ImagesUrls imageUrls;
    @SerializedName("begins")
    @Expose
    private String begins;
    @SerializedName("ends")
    @Expose
    private String ends;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("products")
    @Expose
    private List<String> products = null;
    @SerializedName("size")
    @Expose
    private Integer size;

    public Sales() {
    }
    private static Sales getSales(JSONObject object) {
        return new Gson().fromJson(object.toString(), Sales.class);
    }

    public static ArrayList<Sales> getAllSales(JSONArray jsonArray) throws JSONException {
        ArrayList<Sales> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add(getSales(jsonArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public Sales(String name, String sale, String saleKey, String store, String saleUrl, ImagesUrls imageUrls, String begins, String ends, String description, List<String> products, Integer size) {
        this.name = name;
        this.sale = sale;
        this.saleKey = saleKey;
        this.store = store;
        this.saleUrl = saleUrl;
        this.imageUrls = imageUrls;
        this.begins = begins;
        this.ends = ends;
        this.description = description;
        this.products = products;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getSaleKey() {
        return saleKey;
    }

    public void setSaleKey(String saleKey) {
        this.saleKey = saleKey;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getSaleUrl() {
        return saleUrl;
    }

    public void setSaleUrl(String saleUrl) {
        this.saleUrl = saleUrl;
    }

    public ImagesUrls getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ImagesUrls imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getBegins() {
        return begins;
    }

    public void setBegins(String begins) {
        this.begins = begins;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
