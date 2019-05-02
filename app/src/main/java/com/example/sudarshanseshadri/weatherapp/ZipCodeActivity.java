package com.example.sudarshanseshadri.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ZipCodeActivity extends AppCompatActivity {
    EditText zipCode;
    ImageView go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code);
        zipCode=findViewById(R.id.editText);
        go = findViewById(R.id.imageView_go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("ZIP", zipCode.getText().toString());
                startActivity(intent);
            }
        });

        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()==5)
                {
                    go.setImageResource(R.drawable.go);
                }
                else
                {
                    go.setImageDrawable(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}
