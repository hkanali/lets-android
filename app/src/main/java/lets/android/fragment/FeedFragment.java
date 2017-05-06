package lets.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import lets.android.R;
import lets.android.adapter.FeedRecyclerViewAdapter;
import lets.android.databinding.FragmentFeedBinding;
import lets.android.network.ApiFactory;
import lets.android.network.entity.Category;
import lets.android.network.entity.PageImpl;
import retrofit2.Response;
import rx.Observable;

public class FeedFragment extends BaseFragment<FragmentFeedBinding, FeedRecyclerViewAdapter, Category> {

    public static Fragment newInstance() {

        FeedFragment fragment = new FeedFragment();
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
    protected FeedRecyclerViewAdapter getAdapter() {

        return new FeedRecyclerViewAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {

        return new GridLayoutManager(super.getContext(), 2);
    }

    @Override
    protected Observable<Response<PageImpl<Category>>> getContent(int page) {

        return ApiFactory.getRetrofitClient().getCategory(page);
    }
}
