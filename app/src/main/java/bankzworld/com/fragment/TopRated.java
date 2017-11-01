package bankzworld.com.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import bankzworld.com.R;
import bankzworld.com.adapter.MovieAdapter;
import bankzworld.com.model.movie;
import bankzworld.com.model.movieResponse;
import bankzworld.com.utilities.APiService;
import bankzworld.com.utilities.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by King Jaycee on 31/10/2017.
 */

public class TopRated extends Fragment {

    private static final String TAG = TopRated.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private APiService aPiService;
    private String API_KEY; // YOUR API_KEY HERE

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        testForNetwork();

        return view;
    }

    private void testForNetwork() {
        if (RetrofitUtil.isConnected(getContext())) {
            if (API_KEY != null) {
                getTopRated();
            } else {
                Toast.makeText(getContext(), "please obtain your API_KEY", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Unable to Connect", Toast.LENGTH_SHORT).show();
        }
    }

    private void getTopRated() {
        RetrofitUtil retrofitUtil = new RetrofitUtil(getContext());
        aPiService = retrofitUtil.provideRetrofit().create(APiService.class);
        aPiService.getTopRated(API_KEY).enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.raw());
                    ArrayList<movie> movieList = response.body().getResults();
                    mRecyclerView.setAdapter(new MovieAdapter(movieList));
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
