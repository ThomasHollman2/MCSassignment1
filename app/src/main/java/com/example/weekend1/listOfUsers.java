package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class listOfUsers extends AppCompatActivity {

    private static final String TAG = "listOfUsers";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queryData();

    }

    public void queryData() {
        UserDatabase dataBase = new UserDatabase(this);
        SQLiteDatabase readable = dataBase.getReadableDatabase();
        UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);
        String[] columns = {UserTable.USER_NAME,
                UserTable.USER_COUNTRY,
                UserTable.USER_GENDER,
                UserTable.USER_DOB};


        Cursor cursor = readable.query(
                UserTable.TABLE_NAME,
                columns, //if you need ALL pass null
                null, //null for getting ALL
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        List<UserData> data = new ArrayList<>();
        do {

            String name = cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME));
            String country = cursor.getString(cursor.getColumnIndex(UserTable.USER_COUNTRY));
            String gender = cursor.getString(cursor.getColumnIndex(UserTable.USER_GENDER));
            String date = cursor.getString(cursor.getColumnIndex(UserTable.USER_DOB));

            UserData userInfo = new UserData();
            userInfo.UserName = name;
            userInfo.UserCountry = country;
            userInfo.UserGender = gender;
            userInfo.UserDate = date;

            data.add(userInfo);
            Log.d(TAG, "Name: "+name+" Country: "+country+" Gender: "+gender+" Date of Birth: "+date);

            adapter.setDataSet(data);
        }while (cursor.moveToNext());
            cursor.close();





    }
}
