package truong.com.bazarchictest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import truong.com.bazarchictest.R;

/**
 * Created by ctruong on 17/03/2017.
 */

public class DetailItemActivity extends AppCompatActivity {

    String TAG = DetailItemActivity.class.getSimpleName();

    String name, description, store, begins, ends;

    @BindView(R.id.detail_layout)
    LinearLayout detailLayout;
    @BindView(R.id.tv_sale_name_value)
    TextView tvSaleNameValue;
    @BindView(R.id.tv_store_value)
    TextView tvStoreValue;
    @BindView(R.id.tv_description_value)
    TextView tvDescriptionValue;
    @BindView(R.id.tv_time_value)
    TextView tvTimeValue;
    @BindView(R.id.vf_item)
    ViewFlipper vfItem;
    Picasso picasso;

    String urls, images, url, timeLast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String sale = intent.getStringExtra("sale");
        try {
            JSONObject jsonObject = new JSONObject(sale);
            name = getData(jsonObject, "name");
            description = getData(jsonObject, "description");
            store = getData(jsonObject, "store");
            begins = getData(jsonObject, "begins");
            ends = getData(jsonObject, "ends");
            urls = getData(jsonObject, "image_urls");
            Log.i(TAG, "onCreate: " + urls);
            JSONObject urlObject = new JSONObject(urls);

            images = getData(urlObject, "1024x320");
            JSONArray jsonArray = new JSONArray(images);
            url = jsonArray.getJSONObject(0).getString("url");
            Log.i(TAG, "onCreate: " + url);
            setImageInFlipper(url);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date dateBegin = sdf.parse(begins);
            Date dateEnd = sdf.parse(ends);
            timeLast = calculDifferenceBetween2Dates(dateBegin, dateEnd);
            Log.i(TAG, "onCreate: " + timeLast);
            tvSaleNameValue.setText(name);
            tvStoreValue.setText(store);
            tvDescriptionValue.setText(description);
            tvTimeValue.setText(timeLast);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * GetData from JSON Object
     *
     * @param object
     * @param key
     * @return String
     * @throws JSONException
     */
    public String getData(JSONObject object, String key) throws JSONException {
        String value = object.getString(key);
        return value;
    }

    private void setImageInFlipper(String urls) {
        ImageView view = new ImageView(getApplicationContext());
        picasso.with(this).load(urls).memoryPolicy(MemoryPolicy.NO_CACHE).into(view);
        vfItem.addView(view);
    }

    public String calculDifferenceBetween2Dates(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;

        String time = elapsedDays + " days, " + elapsedHours + " hours last";
        return time;
    }
}
