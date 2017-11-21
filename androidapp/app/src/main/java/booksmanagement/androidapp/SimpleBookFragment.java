package booksmanagement.androidapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleBookFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public SimpleBookFragment() {
        // Required empty public constructor
    }

    public static SimpleBookFragment newInstance(int sectionNumber) {
        SimpleBookFragment fragment = new SimpleBookFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_simple_book, container, false);
        String[] titles = {"First", "Second", "Third", "Forth", "Fifth", "Sixth", "Seventh", "Eights", "Ninth", "Tenth", "First", "Second", "Third", "Forth", "Fifth", "Sixth", "Seventh", "Eights", "Ninth", "Tenth"};

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_simple_book);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(listViewAdapter);
        return rootView;
    }

}
