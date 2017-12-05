package booksmanagement.androidapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import booksmanagement.androidapp.model.BookModel;

public class BooksReadFragment extends Fragment {
    private final String booksToReadUrl = "https://chmuraksiazki.herokuapp.com/api/book/all/alreadyRead";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SharedPreferences preferences;
    private RestTemplate restTemplate;

    public BooksReadFragment() {
        // Required empty public constructor
    }

    public static BooksReadFragment newInstance(int sectionNumber) {
        BooksReadFragment fragment = new BooksReadFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_simple_book, container, false);
        preferences = getActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + preferences.getString("token",""));
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

        ResponseEntity<Object[]> response = restTemplate.exchange(booksToReadUrl, HttpMethod.GET,request,Object[].class );
        Object[] objects = response.getBody();

        List<BookModel> booksRead = new ArrayList<>();
        for (int i = 0; i<objects.length; i++){
            booksRead.add((BookModel)objects[i]);
        }

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_simple_book);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < booksRead.size(); i++) {
                listViewAdapter.add((booksRead.get(i).getBookTitle()));
            }
        }


        listView.setAdapter(listViewAdapter);
        return rootView;
    }
}
