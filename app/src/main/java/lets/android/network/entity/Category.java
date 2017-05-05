package lets.android.network.entity;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Category {

    public Long id;

    public Meta meta;

    public static class Meta {

        public String name;

        public String description;

        public String imageUrl;

        @BindingAdapter({"imageUrl"})
        public static void loadImage(ImageView view, String imageUrl) {

            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .into(view);
        }
    }
}
