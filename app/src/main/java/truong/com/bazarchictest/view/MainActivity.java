package truong.com.bazarchictest.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import truong.com.bazarchictest.R;
import truong.com.bazarchictest.presenter.SalesPresenter;

/**
 * Created by ctruong on 16/03/2017.
 */

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.card_recycler_view)
    RecyclerView view;

    private SalesPresenter salesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        salesPresenter = new SalesPresenter(this, view);
        salesPresenter.fetchAll();
    }
}
