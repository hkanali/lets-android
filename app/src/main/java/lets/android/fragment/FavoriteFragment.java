package lets.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import lets.android.R;
import lets.android.adapter.FavoriteRecyclerViewAdapter;
import lets.android.databinding.FragmentFeedBinding;
import lets.android.network.ApiFactory;
import lets.android.network.entity.PageImpl;
import lets.android.network.entity.Restaurant;
import retrofit2.Response;
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
    protected RecyclerView getRecyclerView() {

        return this.viewDataBinding.fragmentFeedRecyclerView;
    }

    @Override
    protected FavoriteRecyclerViewAdapter getAdapter() {

        return new FavoriteRecyclerViewAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {

        return new LinearLayoutManager(super.getContext());
    }

    @Override
    protected Observable<Response<PageImpl<Restaurant>>> getContent(int page) {

        return ApiFactory.getRetrofitClient().getRestaurant(page);
    }
}
