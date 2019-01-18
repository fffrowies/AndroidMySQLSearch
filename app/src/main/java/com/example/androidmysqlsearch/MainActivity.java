package com.example.androidmysqlsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.androidmysqlsearch.API.ISearchAPI;
import com.example.androidmysqlsearch.Adapter.PersonAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    ISearchAPI myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    RecyclerView recycler_search;
    LinearLayoutManager layoutManager;
    PersonAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add suggest list
        addSuggestList();

        //View
        recycler_search = (RecyclerView)findViewById(R.id.recycler_search);
        recycler_search.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_search.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);
        materialSearchBar.setCardViewElevation(10);

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for (String search_term:suggestList) {
                    if (search_term.toLowerCase().contains(materialSearchBar.getText().toLowerCase())) {
                        suggest.add(search_term);
                    }
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void addSuggestList() {
        suggestList.add("Fernando Rowies");
        suggestList.add("Ricardo Gonzalez");
        suggestList.add("Pablo Martinez");
        suggestList.add("Mariana Pernia");
        suggestList.add("Fabio Sinagra");
        suggestList.add("Miriam Perez");

    }
}
