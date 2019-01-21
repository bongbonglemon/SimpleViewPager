package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    ViewPager viewPager;
    MoviePageAdapter adapter;
    String mSearchTerm;
    Movie mMovie;
    int numberOfPages = 1;

    private static final String TAG = "MainActivity";
    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.container);
        adapter = new MoviePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        SearchFragment.newInstance();
    }

    //respond to search button clicking
    /* public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txt_search);
        String searchTerm = text.getText().toString();

        MovieListFragment fragment = MovieListFragment.newInstance(searchTerm);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, MOVIE_LIST_FRAGMENT_TAG);
        ft.addToBackStack(null);
        ft.commit();
    } */

    @Override
    public void onMovieSelected(Movie movie) {

        mMovie = movie;
        numberOfPages = 3;
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(2);
        /* DetailFragment fragment = DetailFragment.newInstance(movie);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, MOVIE_DETAIL_FRAGMENT_TAG);
        ft.addToBackStack(null); //remember for the back button
        ft.commit();
        */
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        mSearchTerm = searchTerm;
        numberOfPages = 2;
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(1);
    }

    class MoviePageAdapter extends FragmentStatePagerAdapter {

        public MoviePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SearchFragment.newInstance();
                case 1:
                    return MovieListFragment.newInstance(mSearchTerm);
                case 2:
                    return DetailFragment.newInstance(mMovie);
                default:
                    return null;

            }

        }

        // If I screwed up anywhere, its likely here!
        // Crashes if I go back to make another search!
        @Override
        public int getCount() {
            return numberOfPages;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


}
