package lets.android.type;

import android.support.v4.app.Fragment;

import lets.android.R;
import lets.android.fragment.FavoriteFragment;
import lets.android.fragment.FeedFragment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MainFragmentPagerType {

    FEED(0, FeedFragment.newInstance(), R.string.app_name),
    FAVORITE(1, FavoriteFragment.newInstance(), R.string.app_name);

    @Getter
    private int position;

    @Getter
    private Fragment fragment;

    @Getter
    private int nameResourceId;

    public static MainFragmentPagerType of(int position) {

        for (MainFragmentPagerType type :
                values()) {

            if (type.position == position) {

                return type;
            }
        }

        return FEED;
    }
}
