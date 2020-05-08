package com.example.myrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static java.lang.Integer.getInteger;

public class add_item extends AppCompatActivity {
    EditText name_et ,number_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        name_et = findViewById(R.id.et_name);
        number_et = findViewById(R.id.et_number);

        if(getIntent().hasExtra("items")){
            Items items = (Items) getIntent().getSerializableExtra("items");
            name_et.setText(items.getName());
            number_et.setText(items.getNumber());

        }
        // i add these lines here it unable to take an Extras IDK its about the normal situation or its new bug so i just wanna test it


//        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
//                AppDataBase.class, "database-name").build();



        FloatingActionButton add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Items info = createContact(name_et.getText().toString(),number_et.getText().toString());
                    if (info != null) {
                        String mname = info.getName();
                        String mnumber = info.getNumber();
                        Intent intent = new Intent();
                        intent.putExtra("info", info);
                        intent.putExtra("pos",getIntent().getIntExtra("pos",0));
                        intent.putExtra("mname",mname);
                        intent.putExtra("mnumber",mnumber);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                }



            }
        });
    }

    // the block response on empty name or number

    private Items createContact(String name, String number) {
        boolean invalid = false;
        if (name == null || name.isEmpty()) {
            invalid = true;
            name_et.setError("لا يمكن إضافة مستحدم بدون اسم");
        }
        if (number == null || number.isEmpty()) {
            invalid = true;
            number_et.setError("لا يمكن إضافة مستخدم بدون رقم ");
        }
        if (invalid) return null;
        else return new Items(name, number);
    }
    }
