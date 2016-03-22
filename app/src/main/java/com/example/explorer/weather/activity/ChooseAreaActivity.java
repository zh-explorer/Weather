package com.example.explorer.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.explorer.weather.R;
import com.example.explorer.weather.model.City;

import java.util.ArrayList;
import java.util.List;

import static com.example.explorer.weather.util.position.PositionUtil.findCityByName;
import static com.example.explorer.weather.util.position.PositionUtil.findSimilarCity;

/**
 * Created by explorer on 16-3-22.
 *
 */
public class ChooseAreaActivity extends AppCompatActivity {
    private EditText editText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.choose_area);

        //Init list view. datalist will change later
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);


        //return a city class when user choose a city in list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = dataList.get(position);
                City chooseCity = findCityByName(cityName, ChooseAreaActivity.this);
                if (chooseCity != null) {
                    Intent intent = new Intent();
                    Bundle bundle = chooseCity.getBundle();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        //Set a listen on edittext. Listen the change of edittext and make some suggestions
        editText = (EditText) findViewById(R.id.search_edit);
        editText.addTextChangedListener(new TextWatcher() {         //listen call back
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                showCitySuggestion();
            }
        });


    }

    /**
    * Make some suggestion by the string get from edittext
     **/
    private void showCitySuggestion() {
        String inputText = editText.getText().toString();
        List<City> cityList =  findSimilarCity(inputText, this);
        for(City city : cityList) {
            dataList.add(city.getCity());
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
