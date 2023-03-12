package com.example.busbuddydemo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.BarringInfo;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.busbuddydemo.Bus_List_Package.BUS_1;
import com.example.busbuddydemo.Bus_List_Package.BUS_2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class Driver_Signup extends AppCompatActivity {

    EditText username, email, password, number;
    Button button;
    Spinner spinner;
    String[] array = {"Choose Bus :-","Bus No 1" , "Bus No 2" ,"Bus No 3","Bus No 4","Bus No 5","Bus No 6","Bus No 7","Bus No 8"
                       ,"Bus No 9","Bus No 10","Bus No 11","Bus No 12","Bus No 13","Bus No 14","Bus No 15"};
    int index = 0;

    public static String busId;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Driver");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        button = findViewById(R.id.create_account_button);
        spinner = findViewById(R.id.spinner1);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        firebaseFirestore = FirebaseFirestore.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Driver_Signup.this, android.R.layout.simple_dropdown_item_1line, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //String busId = spinner.getSelectedItem().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!username.getText().toString().trim().equals("")  && !email.getText().toString().trim().equals("") &&
                        !password.getText().toString().trim().equals("") && !number.getText().toString().trim().equals("")) {

                String name = username.getText().toString().trim();
                String email_ = email.getText().toString().trim();
                String number_ = number.getText().toString().trim();
                String pass = password.getText().toString().trim();
                busId = spinner.getSelectedItem().toString();

                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email_, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(Driver_Signup.this, "Driver Registered Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Driver_Signup.this, DriverActivity2.class);
                                    intent.putExtra("calling-activity", ActivityConstants.ACTIVITY_FROM_SIGNUP);
                                    startActivity(intent);
                                    progressDialog.cancel();

                                    firebaseFirestore.collection("Driver")
                                            .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                            .set(new UserModel(name, email_, number_, pass));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Driver_Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.cancel();
                                }
                            });
                }
                else {
                    Toast.makeText(Driver_Signup.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }



//                HashMap<String , String> hashMap = new HashMap<>();
//
//                hashMap.put("name" , name);
//                hashMap.put("email" , email_);
//                hashMap.put("number" , number_);
//                hashMap.put("password" , pass);
//
//                Log.e(TAG, busId);
//
//                databaseReference.child(busId).setValue(hashMap);
//
//                Toast.makeText(Driver_Signup.this, "Driver Registered Successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }
}