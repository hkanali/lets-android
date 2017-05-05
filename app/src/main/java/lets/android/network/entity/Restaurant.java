package lets.android.network.entity;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Restaurant extends BaseNetworkEntity {

    public Long id;

    public Long categoryId;

    public Meta meta;

    public static class Meta {

        public String name;

        public String description;

        public List<String> imageUrls;

//        @BindingAdapter({"imageUrl"})
        public static void loadImage(ImageView view, String imageUrl) {

            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .into(view);
        }
    }
}
