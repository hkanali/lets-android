package lets.android.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lets.android.R;
import lets.android.adapter.MainFragmentPagerAdapter;
import lets.android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewPager viewPager = this.activityMainBinding.activityMainViewPager;
        viewPager.setAdapter(new MainFragmentPagerAdapter(super.getSupportFragmentManager()));
        TabLayout tabLayout = this.activityMainBinding.activityMainTabLayout;
        tabLayout.setupWithViewPager(viewPager);
    }
}
