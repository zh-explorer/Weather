package com.example.explorer.weather.activity;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.explorer.weather.R;
import com.example.explorer.weather.util.position.Position;

import static com.example.explorer.weather.util.position.Position.locationInit;
import static com.example.explorer.weather.util.position.PositionUtil.checkDBData;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private TextView response;
    private TextView titleCity;
    private ProgressDialog progressDialog;
    private Position position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.search_button);
        response = (TextView) findViewById(R.id.response_text);
        titleCity = (TextView) findViewById(R.id.city_text);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        checkDBData(this);
        locationInit(this);
        position = Position.getInstance();

    }

    public void showProgressDialog() {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    public void closeProgressDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        position.removeLister(this);
    }
}
