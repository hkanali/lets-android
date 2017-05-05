package lets.android.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import lets.android.R;
import lets.android.adapter.FeedRecyclerViewAdapter;
import lets.android.databinding.FragmentFeedBinding;
import lets.android.network.ApiFactory;
import lets.android.network.entity.Category;
import lets.android.network.entity.PageImpl;
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
    protected RecyclerView getRecylerView() {

        return this.viewDataBinding.fragmentFeedRecyclerView;
    }

    @Override
    protected FeedRecyclerViewAdapter getAdapter() {

        return new FeedRecyclerViewAdapter(new ArrayList<Category>());
    }

    @Override
    protected Observable<PageImpl<Category>> getContent(int page) {

        return ApiFactory.getRetrofitClient().getCategory(page);
    }
}
