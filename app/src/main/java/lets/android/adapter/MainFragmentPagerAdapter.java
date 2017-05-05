package lets.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import lets.android.activity.MainActivity;
import lets.android.type.MainFragmentPagerType;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private MainActivity mainActivity;

    public MainFragmentPagerAdapter(MainActivity mainActivity) {

        super(mainActivity.getSupportFragmentManager());
        this.mainActivity = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {

        return MainFragmentPagerType.of(position).getFragment();
    }

    @Override
    public int getCount() {

        return MainFragmentPagerType.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return this.mainActivity.getResources().getString(MainFragmentPagerType.of(position).getNameResourceId());
    }
}
