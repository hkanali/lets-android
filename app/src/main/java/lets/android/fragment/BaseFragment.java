package lets.android.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import lets.android.adapter.BaseRecyclerViewAdapter;
import lets.android.network.entity.BaseNetworkEntity;
import lets.android.network.entity.PageImpl;
import lets.android.view.EndlessRecyclerViewScrollListener;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public abstract class BaseFragment<VDB extends ViewDataBinding, RVA extends BaseRecyclerViewAdapter, E extends BaseNetworkEntity> extends Fragment {

    protected boolean existNext = true;

    protected VDB viewDataBinding;

    protected abstract int getLayoutId();

    protected abstract RecyclerView getRecyclerView();

    protected abstract RVA getAdapter();

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract Observable<Response<PageImpl<E>>> getContent(int page);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.viewDataBinding = DataBindingUtil.inflate(inflater, this.getLayoutId(), container, false);

        final RecyclerView recyclerView = this.getRecyclerView();

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
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
                    .subscribe(new Observer<Response<PageImpl<E>>>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                            Timber.e(e);
                        }

                        @Override
                        public void onNext(Response<PageImpl<E>> response) {

                            if (!response.isSuccessful()) {

                                try {

                                    Timber.e(response.errorBody().string());
                                } catch (IOException e) {
                                }

                                return;
                            }

                            PageImpl<E> body = response.body();

                            if (totalItemsCount >= body.totalElements) {

                                BaseFragment.this.existNext = false;
                            }

                            if (recyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {

                                BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
                                adapter.addAll(body.content);
                                //recyclerView.getAdapter().notifyItemInserted(recyclerViewAdapter.getContent().size() - 1);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }
}
