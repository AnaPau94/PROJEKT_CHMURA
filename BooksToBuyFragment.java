package booksmanagement.androidapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.adapter.ToBuyAdapter;
import booksmanagement.androidapp.model.BookModel;
import booksmanagement.androidapp.model.BookToBuy;


public class BooksToBuyFragment extends Fragment {
    private final String booksToBuyUrl = "https://chmuraksiazki.herokuapp.com/api/book/all/buy";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SharedPreferences preferences;
    private RestTemplate restTemplate;
    private List<BookToBuy> booksToBuy;
    private ListView listView;

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
        preferences = getActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        View rootView = inflater.inflate(R.layout.fragment_books, container, false);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + preferences.getString("token",""));
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

        BookModel[] allBooks;
        ResponseEntity<BookModel[]> response = restTemplate.exchange(booksToBuyUrl, HttpMethod.GET,request, BookModel[].class);
        allBooks = response.getBody();
        //ResponseEntity<Object[]> response = restTemplate.exchange(booksToBuyUrl, HttpMethod.GET,request,Object[].class );
        //Object[] objects = response.getBody();
        //booksToBuy = new ArrayList<>();
        //for (int i = 0; i<objects.length; i++){
         //   booksToBuy.add((BookToBuy)objects[i]);
        //}


        //TODO poniżej ksiązki są dodawane do listy

        listView = (ListView) rootView.findViewById(R.id.list_view);
        List<BookToBuy> booksToBuy = new ArrayList<>();
        if (allBooks.length>0);
        {
            ToBuyAdapter listViewAdapter = new ToBuyAdapter(getActivity(), R.layout.list_item_to_buy, booksToBuy);
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < allBooks.length; i++) {
                    listViewAdapter.add(new BookToBuy(allBooks[i].getBookTitle()));
                }
            }
            listView.setAdapter(listViewAdapter);
        }
        /////////////////////////////////////////
        // koniec dodawania

        return rootView;
    }
}
//for (int iter = 0; iter < allBooks.length; iter++){
//  booksToBuy.add();
//}