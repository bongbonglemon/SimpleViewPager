package edu.uw.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    Button mButton;
    EditText editText;

    // The SearchFragment will need to communicate with other Fragments,
    // and thus you will need to define an interface (e.g., OnSearchListener)
    // that the containing Activity can implement.

    interface OnSearchListener {
        // allow the Fragment to pass the entered search term to the Activity
        void onSearchSubmitted(String searchTerm);
    }

    OnSearchListener mListener;

    // Check if containing activity implements this surface

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchListener) {  //  = if MainActivity has implemented OnSearchListener
            // The code makes sure that the host Activity has
            // implemented the callback interface.
            mListener = (OnSearchListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + "Please implement OnSearchListener in MainActivity");
        }
    }

    // So at this point we can establish that the interface and hence the containing method has been
    // implemented in the containing activity
    // We can now add a click listener to the
    // button so that when it is clicked, it calls the onSeachSubmitted()


    public static Fragment newInstance(){
        return new SearchFragment();
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        mButton = (Button) rootView.findViewById(R.id.btn_search);
        editText = (EditText) rootView.findViewById(R.id.txt_search);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSearchSubmitted(editText.getText().toString());
                // Still abit confused at why I'm calling the method from an instance
                // of the instance in the fragment
            }
        });

        return rootView;




    }

}
