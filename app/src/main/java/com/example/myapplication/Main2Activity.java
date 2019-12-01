package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
     Button btnShow, btnAdd;
     EditText ARTerm, ENTerm, ARDef, ENDef;
     Spinner Sections;
     private TextView ArTerm, EnTerm, ArDef, EnDef;
     List <Terms> termlist ;
    ArrayList<String> array_list;
    ArrayAdapter arrayAdapter;
     ListView listViewTerms;
     DatabaseReference TermsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //FireBase Database
        TermsDB = FirebaseDatabase.getInstance().getReference("Terms_of_Knitting_and_Tailoring");
        //activity_main2
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnShow = (Button)findViewById(R.id.btnShow);
        Sections = (Spinner)findViewById(R.id.Sections);
        ENTerm = (EditText) findViewById(R.id.ENTerm);
        ARTerm = (EditText) findViewById(R.id.ARTerm);
        ARDef = (EditText) findViewById(R.id.ARDef);
        ENDef = (EditText) findViewById(R.id.ENDef);

        listViewTerms = (ListView) findViewById(R.id.listViewTerms);
        termlist = new ArrayList<>();
        array_list = new ArrayList<>();

        //activity_main3
       /* ArTerm = (TextView) findViewById(R.id.ArTerm);
        EnTerm = (TextView) findViewById(R.id.EnTerm);
        ArDef = (TextView) findViewById(R.id.ArDef);
        EnDef = (TextView) findViewById(R.id.EnDef);*/

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  AddTerm();
            }
        });
       /* btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTerms();
            }
        });*/

    }//OnCreate Method




    private void AddTerm(){
        String arterm = ARTerm.getText().toString().trim();
        String enterm = ENTerm.getText().toString().trim();
        String ardef = ARDef.getText().toString().trim();
        String endef = ENDef.getText().toString().trim();
        String selectedSection = Sections.getSelectedItem().toString();
        if(!TextUtils.isEmpty(arterm)){
            String id =  TermsDB.push().getKey(); //generate uniqe id every time
            Terms term =  new Terms(id, arterm, enterm, ardef, endef, selectedSection);
            TermsDB.child(id).setValue(term);
            Toast.makeText(this, "تمت الإضافة بنجاح", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "الرجاء تعبئة الخانات اللازمة", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart(){

        super.onStart();

        //Intent intent = new Intent(this, Main3Activity.class);
       // startActivity(intent);

        TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Drawing");

        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
//                toastmsg(String.valueOf(dataSnapshot.getChildrenCount()));
                termlist.clear();
                for(DataSnapshot termsSnapshot : dataSnapshot.getChildren()){
                    //Terms terms = termsSnapshot.getValue(Terms.class);
                    //termlist.add(terms);
                    String title1 = termsSnapshot.child("arterm").getValue(String.class);
                    //String title2 = termsSnapshot.child("ardef").getValue(String.class);
                    //String title3 = termsSnapshot.child("enterm").getValue(String.class);
                   // String title4 = termsSnapshot.child("endef").getValue(String.class);
                    list.add(title1 + " " );
//+ title2 + " " +title3 + " " +title4
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                listViewTerms.setAdapter(arrayAdapter);

                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(Main2Activity.this,"ok"+position , Toast.LENGTH_SHORT).show();
                        Move();
                    }
                });


//                TermsList adapter = new TermsList(Main2Activity.this,termlist);
//                listViewTerms.setAdapter(adapter);

//                Map td = (HashMap<String,String>) dataSnapshot.getValue();
//                array_list.clear();
//                array_list.addAll(td.values());
//                arrayAdapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, array_list);
//                listViewTerms.setAdapter(arrayAdapter);

//                toastmsg(td.values().toArray()[0].toString());


               /* String ArD = dataSnapshot.child("ardef").getValue().toString();
                String ArT = dataSnapshot.child("arterm").getValue().toString();
                String EnT = dataSnapshot.child("endef").getValue().toString();
                String EnD = dataSnapshot.child("erterm").getValue().toString();
                ArTerm.setText(ArT);
                EnTerm.setText(EnT);
                ArDef.setText(ArD);
                EnDef.setText(EnD);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // to do later

            }
        });


    }

    public void toastmsg(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    private void Move(){
        Intent intent =  new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}
