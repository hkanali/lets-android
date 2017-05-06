package lets.android.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import lets.android.R;
import lets.android.adapter.MainFragmentPagerAdapter;
import lets.android.databinding.ActivityMainBinding;
import lets.android.databinding.ContentNavigationViewBinding;
import lets.android.service.UserService;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private ContentNavigationViewBinding contentNavigationViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewPager viewPager = this.activityMainBinding.activityMainViewPager;
        viewPager.setAdapter(new MainFragmentPagerAdapter(this));

        TabLayout tabLayout = this.activityMainBinding.activityMainTabLayout;
        tabLayout.setupWithViewPager(viewPager);


        Toolbar toolbar = this.activityMainBinding.activityMainToolbar;

        final DrawerLayout drawerLayout = this.activityMainBinding.activityMainDrawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = this.activityMainBinding.mainDrawerNavigation;

        this.contentNavigationViewBinding = DataBindingUtil.bind(navigationView.getHeaderView(0));

        new UserService().setUser(this.contentNavigationViewBinding, "22");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                // TODO
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
