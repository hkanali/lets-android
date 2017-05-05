package lets.android.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lets.android.R;
import lets.android.adapter.FeedRecyclerViewAdapter;
import lets.android.databinding.FragmentFeedBinding;
import lets.android.network.ApiFactory;
import lets.android.network.entity.Category;
import lets.android.network.entity.PageImpl;
import lets.android.view.EndlessRecyclerViewScrollListener;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FeedFragment extends Fragment {

    private FragmentFeedBinding fragmentFeedBinding;

    private boolean existNext = true;

    public static Fragment newInstance() {

        FeedFragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.fragmentFeedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false);

        final RecyclerView recyclerView = this.fragmentFeedBinding.fragmentFeedRecyclerView;

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(super.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new FeedRecyclerViewAdapter(new ArrayList<Category>()));
        this.loadContents(0, 0, recyclerView);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int page, final int totalItemsCount, RecyclerView view) {

                FeedFragment.this.loadContents(page, totalItemsCount, view);
            }
        });

        return this.fragmentFeedBinding.getRoot();
    }

    private void loadContents(int page, final int totalItemsCount, final RecyclerView recyclerView) {

        if (!this.existNext) {

            return;
        }

        if (recyclerView.getAdapter() instanceof FeedRecyclerViewAdapter) {

            ApiFactory.getRetrofitClient().getCategory(page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PageImpl<Category>>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(PageImpl<Category> categoryPage) {

                            if (totalItemsCount >= categoryPage.totalElements) {

                                FeedFragment.this.existNext = false;
                            }

                            FeedRecyclerViewAdapter recyclerViewAdapter = (FeedRecyclerViewAdapter) recyclerView.getAdapter();
                            recyclerViewAdapter.addAll(categoryPage.content);
                            //recyclerView.getAdapter().notifyItemInserted(recyclerViewAdapter.getContent().size() - 1);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    })
        }
    }
}
