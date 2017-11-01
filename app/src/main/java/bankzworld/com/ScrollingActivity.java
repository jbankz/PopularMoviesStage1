package bankzworld.com;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import bankzworld.com.model.movie;

public class ScrollingActivity extends AppCompatActivity {

    ImageView mBackdrop;
    TextView mOverView;
    CollapsingToolbarLayout ctb;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Context context = getApplicationContext();

        mBackdrop = (ImageView) findViewById(R.id.image_backdrop);
        mOverView = (TextView) findViewById(R.id.overview);
        ctb = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        pb = (ProgressBar) findViewById(R.id.loading_backdrop);

        movie model = getIntent().getParcelableExtra("data");

        ctb.setTitle(model.getTitle());
        mOverView.setText(model.getOverview());
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w500/" + model.getBackdrop_path())
                .into(mBackdrop, new Callback() {
                    @Override
                    public void onSuccess() {
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        mBackdrop.setImageResource(R.drawable.placeholder);
                    }
                });

    }
}
