package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

public class Screen3 extends AppCompatActivity {
    Button country;
    Button birthday;
    Button exitbutton;
    DatePicker date;
    TextView birthdate;
    TextView age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_screen3);
        age = findViewById(R.id.age);
        exitbutton = findViewById(R.id.exitbutton);
        birthdate = findViewById(R.id.birthdate);
        birthday = findViewById(R.id.button_birthdate);
        date = findViewById(R.id.calendar);
        date.setVisibility(View.GONE);
        exitbutton.setVisibility(View.GONE);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitbutton.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                String day = date.getDayOfMonth() + "/";
                String month = date.getMonth() + 1 + "/";
                String year = " " + date.getYear();
                birthdate.setText(month + day + year);
                String ages = String.valueOf(2020 - date.getYear());
                age.setText(ages + " years old");




            }
        });
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitbutton.setVisibility(View.GONE);
                date.setVisibility(View.GONE);
            }
        });
        country = findViewById(R.id.postal);
        country.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
                        picker.setListener(new CountryPickerListener() {
                            @Override
                            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                                country.setText(name);
                                picker.dismiss();
                            }
                        });
                        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
                    }
                }
        );

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

