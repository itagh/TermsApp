package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGoToTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoToTerm = (Button)findViewById(R.id.gotoTerms);

        btnGoToTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch();
            }
        }); // call the method to move to Activity 2 (Add Term)
    }

    private void Switch(){

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }


}
