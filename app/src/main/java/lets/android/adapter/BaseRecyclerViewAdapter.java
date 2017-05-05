package lets.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lets.android.R;
import lets.android.network.entity.BaseNetworkEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class BaseRecyclerViewAdapter<E extends BaseNetworkEntity, H extends BaseBindingViewHolder>
        extends RecyclerView.Adapter<H> {

    protected abstract H createViewHolder(View view);

    protected abstract void setContent(H holder, int position);

    @Getter
    private List<E> content;

    public boolean addAll(List<E> content) {

        return this.content.addAll(content);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_category, parent, false);
        return this.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {

        this.setContent(holder, position);
    }

    @Override
    public int getItemCount() {

        return this.content.size();
    }
}
