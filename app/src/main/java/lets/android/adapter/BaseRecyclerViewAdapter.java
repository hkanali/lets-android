package lets.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lets.android.network.entity.BaseNetworkEntity;
import lombok.Getter;

public abstract class BaseRecyclerViewAdapter<E extends BaseNetworkEntity, H extends BaseBindingViewHolder>
        extends RecyclerView.Adapter<H> {

    protected abstract int getLayoutId();

    protected abstract H createViewHolder(View view);

    protected abstract void setContent(H holder, E entity);

    @Getter
    private List<E> content = new ArrayList<>();

    public boolean addAll(List<E> content) {

        return this.content.addAll(content);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.getLayoutId(), parent, false);
        return this.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {

        this.setContent(holder, this.content.get(position));
    }

    @Override
    public int getItemCount() {

        return this.content.size();
    }
}
