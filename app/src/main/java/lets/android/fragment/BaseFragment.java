package lets.android.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lets.android.adapter.BaseRecyclerViewAdapter;
import lets.android.network.entity.BaseNetworkEntity;
import lets.android.network.entity.PageImpl;
import lets.android.view.EndlessRecyclerViewScrollListener;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BaseFragment<VDB extends ViewDataBinding, RVA extends BaseRecyclerViewAdapter, E extends BaseNetworkEntity> extends Fragment {

    protected boolean existNext = true;

    protected VDB viewDataBinding;

    protected abstract int getLayoutId();

    protected abstract RecyclerView getRecylerView();

    protected abstract RVA getAdapter();

    protected abstract Observable<PageImpl<E>> getContent(int page);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.viewDataBinding = DataBindingUtil.inflate(inflater, this.getLayoutId(), container, false);

        final RecyclerView recyclerView = this.getRecylerView();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(super.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(this.getAdapter());
        this.loadContents(0, 0, recyclerView);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int page, final int totalItemsCount, RecyclerView view) {

                BaseFragment.this.loadContents(page, totalItemsCount, view);
            }
        });

        return this.viewDataBinding.getRoot();
    }

    private void loadContents(int page, final int totalItemsCount, final RecyclerView recyclerView) {

        if (!this.existNext) {

            return;
        }

        if (recyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {

            this.getContent(page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PageImpl<E>>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(PageImpl<E> entityPage) {

                            if (totalItemsCount >= entityPage.totalElements) {

                                BaseFragment.this.existNext = false;
                            }

                            RVA recyclerViewAdapter = (RVA) recyclerView.getAdapter();
                            recyclerViewAdapter.addAll(entityPage.content);
                            //recyclerView.getAdapter().notifyItemInserted(recyclerViewAdapter.getContent().size() - 1);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }
}
