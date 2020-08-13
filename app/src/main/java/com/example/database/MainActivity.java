package com.example.database;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editid, editname, editprofile;
    Button btninsert, btnfetch;
    DBhelper db;
    String id, name, profile;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editid = findViewById(R.id.edit1);
        editname = findViewById(R.id.edit2);
        editprofile = findViewById(R.id.edit3);
        btninsert = findViewById(R.id.btninsert);
        btnfetch = findViewById(R.id.btniFetch);
        db = new DBhelper(MainActivity.this);
        listView=findViewById(R.id.listview);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id = editid.getText().toString();
                final String name = editname.getText().toString();
                final String profile = editprofile.getText().toString();

                //boolean b=db.execute("insert into student(name,phone,Address)values('"+name+"','"+phone+"','"+Address+"')");

                boolean b = db.InsertEMP(id, name, profile);


                if (b == true) {
                    if (id != null && name != null && profile != null) {
                        Toast.makeText(MainActivity.this, id + "", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, name + "", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, profile + "", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
                editname.setText("");
                editid.setText("");
                editprofile.setText("");

            }
        });


        btnfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = db.getData();
                cs.moveToFirst();
                String id1 = cs.getString(cs.getColumnIndex(DBhelper.COL_1));
                String name1 = cs.getString(cs.getColumnIndex(DBhelper.COL_2));
                String profile1 = cs.getString(cs.getColumnIndex(DBhelper.COL_3));


                    //   Log.e("Name", cs.getString(cs.getColumnIndex(DBhelper.COL_2)));
                    // Log.e("profile", cs.getString(cs.getColumnIndex(DBhelper.COL_3)));


                  editname.setText("" + name1);
                    editid.setText("" + id1);
                    editprofile.setText("" + profile1);

            }
        });
    }
}
