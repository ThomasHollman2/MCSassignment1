package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class listOfUsers extends AppCompatActivity {

    private static final String TAG = "listOfUsers";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter(this);
        recyclerView.setAdapter(adapter);
        queryData();

    }

    public void queryData() {
        UserDatabase dataBase = new UserDatabase(this);
        SQLiteDatabase readable = dataBase.getReadableDatabase();

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
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME));
            String country = cursor.getString(cursor.getColumnIndex(UserTable.USER_COUNTRY));
            String gender = cursor.getString(cursor.getColumnIndex(UserTable.USER_GENDER));
            String date = cursor.getString(cursor.getColumnIndex(UserTable.USER_DOB));



            Log.d(TAG, "Name: "+name+" Country: "+country+" Gender: "+gender+" Date of Birth: "+date);

        }
    }
}
