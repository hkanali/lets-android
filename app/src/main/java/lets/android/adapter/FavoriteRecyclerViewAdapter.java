package lets.android.adapter;

import android.view.View;

import lets.android.R;
import lets.android.databinding.ContentRestaurantBinding;
import lets.android.network.entity.Restaurant;

public class FavoriteRecyclerViewAdapter extends BaseRecyclerViewAdapter<Restaurant, FavoriteRecyclerViewAdapter.ContentRestaurantBindingViewHolder> {

    @Override
    protected ContentRestaurantBindingViewHolder createViewHolder(View view) {

        return new ContentRestaurantBindingViewHolder(view);
    }

    @Override
    protected void setContent(ContentRestaurantBindingViewHolder holder, Restaurant restaurant) {

        holder.getViewDataBinding().setRestaurant(restaurant);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.content_restaurant;
    }

    public static class ContentRestaurantBindingViewHolder extends BaseBindingViewHolder<ContentRestaurantBinding> {

        public ContentRestaurantBindingViewHolder(View itemView) {

            super(itemView);
        }
    }
}
