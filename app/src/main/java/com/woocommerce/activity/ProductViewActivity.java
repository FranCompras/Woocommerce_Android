package com.woocommerce.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.woocommerce.R;
import com.woocommerce.fragment.ScreenSlidePageFragment;

import java.util.ArrayList;

public class ProductViewActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter mPagerAdapter;
    String imageUrl;
    ImageButton leftNav;
    ImageButton rightNav;
    ArrayList<Integer> list = new ArrayList<Integer>();

    int NUM_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list.add(R.drawable.item1);
        list.add(R.drawable.item2);
        list.add(R.drawable.item3);
        list.add(R.drawable.item4);

        //viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager_productview);
        if (viewPager != null) {
            setupViewPager(viewPager);
            viewPager.setOffscreenPageLimit(NUM_PAGES);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.dealtabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);

        //tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(R.drawable.defaultimage)
                    .setText("Front "+i);
        }

        TextView tv = (TextView) findViewById(R.id.oldprice);
        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ViewCartActivity.class);
                context.startActivity(intent);
            }
        });


        indicator();
    }


    public void indicator(){

        /* indicator */
        leftNav = (ImageButton) findViewById(R.id.left_nav);
        rightNav = (ImageButton) findViewById(R.id.right_nav);

        if(viewPager.getCurrentItem() == 0){
            leftNav.setVisibility(View.GONE);
        }

        // Images left navigation
        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                    leftNav.setVisibility(View.VISIBLE);
                    rightNav.setVisibility(View.VISIBLE);

                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                    leftNav.setVisibility(View.GONE);
                    rightNav.setVisibility(View.VISIBLE);
                }
            }
        });

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
                if(tab == NUM_PAGES - 1){
                    rightNav.setVisibility(View.GONE);
                    leftNav.setVisibility(View.VISIBLE);
                }
                else if(tab > 0){
                    leftNav.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(position, list.get(position));
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }







}
