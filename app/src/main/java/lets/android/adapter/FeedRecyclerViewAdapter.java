package lets.android.adapter;

import android.view.View;

import java.util.List;

import lets.android.databinding.ContentCategoryBinding;
import lets.android.network.entity.Category;

public class FeedRecyclerViewAdapter extends BaseRecyclerViewAdapter<Category, FeedRecyclerViewAdapter.ContentCategoryBindingViewHolder> {

    public FeedRecyclerViewAdapter(List<Category> content) {

        super(content);
    }

    @Override
    protected ContentCategoryBindingViewHolder createViewHolder(View view) {

        return new ContentCategoryBindingViewHolder(view);
    }

    @Override
    protected void setContent(ContentCategoryBindingViewHolder holder, int position) {

        holder.getViewDataBinding().setCategory(this.getContent().get(position));
    }

    public static class ContentCategoryBindingViewHolder extends BaseBindingViewHolder<ContentCategoryBinding> {

        public ContentCategoryBindingViewHolder(View itemView) {

            super(itemView);
        }
    }
}
