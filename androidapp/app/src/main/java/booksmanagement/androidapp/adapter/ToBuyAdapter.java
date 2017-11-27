package booksmanagement.androidapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.BookToBuy;

public class ToBuyAdapter extends ArrayAdapter {

    private int resource;
    private Context context;
    private List<BookToBuy> items;

    public ToBuyAdapter(@NonNull Context context, int resource, List<BookToBuy> items) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }


    //this method will be called for every item of your listview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        BookToBuyHolder holder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(resource, parent, false);

        holder = new BookToBuyHolder();
        holder.bookToBuy = items.get(position);
        holder.buyBookButton = (Button) row.findViewById(R.id.buttonItemToBuy);
        holder.buyBookButton.setTag(holder.bookToBuy);

        holder.title = (TextView)row.findViewById(R.id.textViewBookToBuyTitle);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(BookToBuyHolder holder) {
        holder.title.setText(holder.bookToBuy.getTitle());
    }

    public static class BookToBuyHolder {
        BookToBuy bookToBuy;
        TextView title;
        Button buyBookButton;
    }
}
