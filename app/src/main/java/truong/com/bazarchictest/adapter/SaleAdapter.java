package truong.com.bazarchictest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import truong.com.bazarchictest.R;
import truong.com.bazarchictest.model.Sales;
import truong.com.bazarchictest.view.DetailItemActivity;

/**
 * Created by ctruong on 16/03/2017.
 */

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleHolder> {
    String TAG = SaleAdapter.class.getSimpleName();
    private List<Sales> sales;
    private Context context;

    public SaleAdapter(List<Sales> sales, Context context) {
        this.sales = sales;
        this.context = context;
    }

    @Override
    public SaleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new SaleHolder(view);
    }

    @Override
    public void onBindViewHolder(final SaleHolder holder, final int position) {
        final Sales salesList = sales.get(position);
        holder.textView.setText(salesList.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + position);
                Log.i(TAG, "onClick: " + salesList.toString());
                Gson gson = new Gson();
                String sale = gson.toJson(salesList);
                Context c = holder.textView.getContext();
                Intent intent = new Intent(c, DetailItemActivity.class);
                intent.putExtra("sale", sale);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public class SaleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_Name)
        TextView textView;

        public SaleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
