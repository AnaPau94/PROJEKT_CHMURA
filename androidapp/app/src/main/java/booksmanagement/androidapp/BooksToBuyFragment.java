package booksmanagement.androidapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BooksToBuyFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public BooksToBuyFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BooksToBuyFragment newInstance(int sectionNumber) {
        BooksToBuyFragment fragment = new BooksToBuyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        //TODO poniżej ksiązki są dodawane do listy
        String[] titles = {"First", "Second", "Third", "Forth", "Fifth", "Sixth", "Seventh", "Eights", "Ninth", "Tenth"};

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        List<BookToBuy> booksToBuy = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < titles.length; i++) {
                booksToBuy.add(new BookToBuy(titles[i]));
            }
        }
        ToBuyAdapter listViewAdapter = new ToBuyAdapter(getActivity(), R.layout.list_item_book_to_buy, booksToBuy);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < titles.length; i++) {
                listViewAdapter.add(new BookToBuy(titles[i]));
            }
        }
        listView.setAdapter(listViewAdapter);
        /////////////////////////////////////////
        // koniec dodawania

        return rootView;
    }
}
