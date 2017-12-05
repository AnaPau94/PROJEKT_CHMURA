package booksmanagement.androidapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import booksmanagement.androidapp.model.BookModel;

/**
 * Created by Anna Bielinska-Slot on 12/4/2017.
 */

public class ISBNFinderUtil {
    private Long isbnNumber;
    private String urlPart1 = "http://www.bookfinder4u.com/IsbnSearch.aspx?isbn=";
    private String urlPart2 = "&mode=direct";
    private String pattern1Author = "<br>By:";
    private String pattern2Author = "<br>";
    private String pattern3Author = ">";
    private String pattern4Author = "</";
    private String pattern1Title = "class=\"t9\">";
    private String pattern2Title = "</b>";
    private String pattern1PublisherAndDate = "Publisher:";
    private String pattern2PublisherAndDate = "<br>";


    private BookModel book;

    public ISBNFinderUtil(Long isbnNumber) {
        this.isbnNumber = isbnNumber;
        book = new BookModel();
    }

    public BookModel getBook() {
        return book;
    }


    public boolean find() {
        boolean out = false;
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL(urlPart1 + isbnNumber.toString() + urlPart2);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                if (line.contains("<b class=\"t9\">")) {
                    getDataFromHtml(br, line);
                    out = true;
                    break;
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {

            }
        }

        return out;
    }

    private void getDataFromHtml(BufferedReader br, String line) throws IOException {
        book.setIsbn(isbnNumber.toString());    //pobierany ISBN

        String regexString = Pattern.quote(pattern1Title) + "(.*?)" + Pattern.quote(pattern2Title);
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            book.setBookTitle(matcher.group(1));    //pobierany tytuł
        }

        regexString = Pattern.quote(pattern1Author) + "(.*?)" + Pattern.quote(pattern2Author);
        pattern = Pattern.compile(regexString);
        matcher = pattern.matcher(line);
        if (matcher.find()) {
            String uncuttedAuthorInfo = matcher.group(1);

            regexString = Pattern.quote(pattern3Author) + "(.*?)" + Pattern.quote(pattern4Author);
            pattern = Pattern.compile(regexString);
            matcher = pattern.matcher(uncuttedAuthorInfo);
            if (matcher.find()) {
                book.setBookAuthor(matcher.group(1));    //pobierany Author
            }
        }

        line = br.readLine();
        line = br.readLine();
        regexString = Pattern.quote(pattern1PublisherAndDate) + "(.*?)" + Pattern.quote(pattern2PublisherAndDate);
        pattern = Pattern.compile(regexString);
        matcher = pattern.matcher(line);
        if (matcher.find()) {
            String[] tab = matcher.group(1).split(" - ");
            book.setPublication(tab[0]);    //pobierany Publication
            book.setPrintDate(tab[1]);      //pobierana Data druku
        }
    }
}
