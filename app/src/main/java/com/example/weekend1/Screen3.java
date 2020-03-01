package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

public class Screen3 extends AppCompatActivity {
    Button country;
    Button birthday;
    Button exitbutton;
    DatePicker date;
    TextView birthdate;
    TextView age;
    Button save;
    TextView name;
    RadioGroup gender;
    private static final String TAG = "Screen3";

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
        name = findViewById(R.id.name);
        gender = findViewById(R.id.genderbuttons);

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
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();


                Intent RVScreen = new Intent(Screen3.this, listOfUsers.class);
                startActivity(RVScreen);
            }
        });

    }

    public void newUser() {
        UserData data = new UserData();
        data.UserName = name.getText().toString();
        data.UserCountry = country.getText().toString();
        int id = gender.getCheckedRadioButtonId();
        RadioButton UserGender = findViewById(id);
        data.UserGender = UserGender.getText().toString();
        data.UserDate = birthdate.getText().toString();
        saveData( data.UserName, data.UserCountry, data.UserGender, data.UserDate);


    }


    public void saveData(

                         String name,
                         String country,
                         String gender,
                         String date) {
        try {


            UserDatabase dataBase = new UserDatabase(this);
            SQLiteDatabase writable = dataBase.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(UserTable.USER_NAME, name);
            contentValues.put(UserTable.USER_COUNTRY, country);
            contentValues.put(UserTable.USER_GENDER, gender);
            contentValues.put(UserTable.USER_DOB, date);

            writable.insert(   UserTable.TABLE_NAME,
                    null,contentValues

            )  ;
            long row = writable.insert(UserTable.TABLE_NAME,
                    null,
                    contentValues);
            //row -1 means failed to insert
            if (row < 0) {
                Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
            }
        }
        catch (SQLException E )          {
            Log.d(TAG, "saveData: "+ E.toString());
        }

    }


}

