package lets.android.adapter;

import android.view.View;

import lets.android.R;
import lets.android.databinding.ContentCategoryBinding;
import lets.android.network.entity.Category;

public class FeedRecyclerViewAdapter extends BaseRecyclerViewAdapter<Category, FeedRecyclerViewAdapter.ContentCategoryBindingViewHolder> {

    @Override
    protected int getLayoutId() {

        return R.layout.content_category;
    }

    @Override
    protected ContentCategoryBindingViewHolder createViewHolder(View view) {

        return new ContentCategoryBindingViewHolder(view);
    }

    @Override
    protected void setContent(ContentCategoryBindingViewHolder holder, Category category) {

        holder.getViewDataBinding().setCategory(category);
    }

    public static class ContentCategoryBindingViewHolder extends BaseBindingViewHolder<ContentCategoryBinding> {

        public ContentCategoryBindingViewHolder(View itemView) {

            super(itemView);
        }
    }
}
