/*
    This file is part of Handbook for Melee.

    Handbook for Melee is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Handbook for Melee is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Handbook for Melee.  If not, see <http://www.gnu.org/licenses/>
 */

package com.thatkawaiiguy.meleehandbook.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.thatkawaiiguy.meleehandbook.R;
import com.thatkawaiiguy.meleehandbook.adapter.fragment.TabFragmentAdapter;
import com.thatkawaiiguy.meleehandbook.utils.ArrayHelper;
import com.thatkawaiiguy.meleehandbook.utils.Preferences;
import com.thatkawaiiguy.meleehandbook.utils.XMLParser;

import pl.droidsonroids.gif.GifImageView;

public class TechTabActivity extends AppCompatActivity {

    private String techPicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().hasExtra("bundle") && savedInstanceState == null)
            savedInstanceState = getIntent().getExtras().getBundle("bundle");
        Preferences.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_tab_gif_layout);

        if (getIntent().getExtras() == null)
            return;
        techPicked = getIntent().getExtras().getString("option");
        int id = getIntent().getExtras().getInt("xml");
        assert techPicked != null;
        String videoID = getIntent().getExtras().getString("videoID");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(0x00000000);
        final AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(techPicked);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final GifImageView tabImage = (GifImageView) findViewById(R.id.infoGif);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        assert viewPager != null;

        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(),
                XMLParser.getTabs(getResources(), techPicked, id, id == R.xml.standardtech ? 2 : 3)));
        tabLayout.setupWithViewPager(viewPager);

        switch(videoID) {
            case "Wall jumping":
                tabImage.setImageResource(R.drawable.walljump);
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                        appBar.setExpanded(true);
                        switch(tab.getPosition()) {
                            case 0:
                                tabImage.setImageResource(R.drawable.walljump);
                                break;
                            case 1:
                                tabImage.setImageResource(R.drawable.defaultpic);
                                break;
                            case 2:
                                tabImage.setImageResource(R.drawable.reversewalljump);
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
                break;
            case "Directional Influence":
                tabImage.setImageResource(R.drawable.di);
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                        appBar.setExpanded(true);
                        tabImage.setImageResource(getResources().getIdentifier(ArrayHelper
                                .getFileName(tab.getText().toString()), "drawable", getPackageName()));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                });
                break;
            case "Super wavedash & SDWD":
                tabImage.setImageResource(R.drawable.swd);
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                        appBar.setExpanded(true);

                        tabImage.setImageResource(getResources().getIdentifier(ArrayHelper
                                .getFileName(tab.getText().toString()), "drawable", getPackageName()));
                        switch(tab.getPosition()) {
                            case 0:
                                tabImage.setImageResource(R.drawable.swd);
                                break;
                            case 1:
                                tabImage.setImageResource(R.drawable.defaultpic);
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                });
                break;
            case "Extended & homing grapple":
                tabImage.setImageResource(R.drawable.extendedgrapple);
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                        appBar.setExpanded(true);
                        tabImage.setImageResource(getResources().getIdentifier(ArrayHelper
                                .getFileName(tab.getText().toString()), "drawable", getPackageName()));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                });
                break;
            case "Shield dropping":
                tabImage.setImageResource(R.drawable.shielddrop);
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                        appBar.setExpanded(true);
                        tabImage.setImageResource(getResources().getIdentifier(ArrayHelper
                                .getFileName(tab.getText().toString()), "drawable", getPackageName()));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                });
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("tech", techPicked);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        techPicked = savedInstanceState.getString("tech");
    }
}
