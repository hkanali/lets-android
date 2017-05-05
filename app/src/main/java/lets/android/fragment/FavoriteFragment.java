package lets.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lets.android.R;
import lets.android.adapter.FavoriteRecyclerViewAdapter;
import lets.android.databinding.FragmentFeedBinding;
import lets.android.network.ApiFactory;
import lets.android.network.entity.PageImpl;
import lets.android.network.entity.Restaurant;
import rx.Observable;

public class FavoriteFragment extends BaseFragment<FragmentFeedBinding, FavoriteRecyclerViewAdapter, Restaurant> {

    public static Fragment newInstance() {

        FavoriteFragment fragment = new FavoriteFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_feed;
    }

    @Override
    protected RecyclerView getRecylerView() {

        return this.viewDataBinding.fragmentFeedRecyclerView;
    }

    @Override
    protected FavoriteRecyclerViewAdapter getAdapter() {

        return new FavoriteRecyclerViewAdapter(new ArrayList<Restaurant>());
    }

    @Override
    protected Observable<PageImpl<Restaurant>> getContent(int page) {

        return ApiFactory.getRetrofitClient().getRestaurant(page);
    }
}
