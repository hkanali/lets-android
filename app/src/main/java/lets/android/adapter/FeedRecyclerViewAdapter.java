package lets.android.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lets.android.R;
import lets.android.databinding.ContentCategoryBinding;
import lets.android.network.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedRecyclerViewAdapter.BindingHolder> {

    @Getter
    private List<Category> content;

    public boolean addAll(List<Category> content) {

        return this.content.addAll(content);
    }

    @Override
    public FeedRecyclerViewAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_category, parent, false);
        return new FeedRecyclerViewAdapter.BindingHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedRecyclerViewAdapter.BindingHolder holder, int position) {

        holder.getContentCategoryBinding().setCategory(this.content.get(position));
    }

    @Override
    public int getItemCount() {

        return this.content.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        @Getter
        private ContentCategoryBinding contentCategoryBinding;

        public BindingHolder(View itemView) {

            super(itemView);
            contentCategoryBinding = DataBindingUtil.bind(itemView);
        }
    }
}
