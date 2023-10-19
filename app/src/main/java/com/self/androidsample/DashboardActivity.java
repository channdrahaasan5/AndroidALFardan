package com.self.androidsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DashboardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AppCompatButton btn1, logOut;
    AppCompatEditText txt1, txt2;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btn1 = findViewById(R.id.btn1);
        logOut = findViewById(R.id.logOut);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        dropdown = findViewById(R.id.dropdown);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("isLogin");
                editor.apply();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String[] items = new String[]{"INR", "USD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        txt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(dropdown.getSelectedItem().toString().equals("INR")) {
                    aedToInr();
                } else {
                    aedToUsd();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void aedToInr() {
        if(String.valueOf(txt1.getText()).length() > 0) {
            double t1 = Double.parseDouble(String.valueOf(txt1.getText()));
            double aed = 22.67;
            double final_amt = t1 * aed;
            txt2.setText(String.valueOf(decfor.format(final_amt)));
        } else {
            txt2.setText("");
        }
    }

    public void aedToUsd() {
        if(String.valueOf(txt1.getText()).length() > 0) {
            Log.d("txt1",String.valueOf(txt2.getText()));
            double t2 = Double.parseDouble(String.valueOf(txt1.getText()));
            double usd = 0.27;
            double final_amt2 = t2 * usd;
            txt2.setText(String.valueOf(decfor.format(final_amt2)));
        } else {
            txt2.setText("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
               aedToInr();
                break;
            case 1:
                aedToUsd();
                break;
            default:
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}