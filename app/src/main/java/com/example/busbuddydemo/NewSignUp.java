package com.example.busbuddydemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewSignUp extends AppCompatActivity {

    EditText username , password, dob, email , mobileNumber;
    Button create_account;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_up);

         username = findViewById(R.id.username);
         password = findViewById(R.id.password);
         dob = findViewById(R.id.dob);
         email = findViewById(R.id.email);
         mobileNumber = findViewById(R.id.mobile_number);
         create_account = findViewById(R.id.create_account_button);
         firebaseAuth = FirebaseAuth.getInstance();
         progressDialog = new ProgressDialog(this);
         firebaseFirestore = FirebaseFirestore.getInstance();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == dob) {
                    // on below line we are getting
                    // the instance of our calendar.
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    // on below line we are creating a variable for date picker dialog.
                    DatePickerDialog datePickerDialog = new DatePickerDialog(NewSignUp.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // on below line we are setting date to our text view.
                                    dob.setText(dayOfMonth + "-" + getMonth(monthOfYear + 1) + "-" + year);
                                }
                            },
                            // on below line we are passing year,
                            // month and day for selected date in our date picker.
                            year, month, day);
                    // at last we are calling show to
                    // display our date picker dialog.
                    datePickerDialog.show();
                }
            }
        });


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!username.getText().toString().trim().equals("")  && !email.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("") && !mobileNumber.getText().toString().trim().equals("") && !dob.getText().toString().trim().equals(""))
                if (mobileNumber.getText().length() == 10) {

                    String usernamepass, emailpass, passwordpass, dobpass, numberpass;
                    usernamepass = String.valueOf(username.getText()).trim();
                    passwordpass = String.valueOf(password.getText()).trim();
                    emailpass = email.getText().toString().trim();
                    dobpass = String.valueOf(dob.getText()).trim();
                    numberpass = String.valueOf(mobileNumber.getText()).trim();

                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(emailpass, passwordpass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(NewSignUp.this, "User Registered Successfully.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(NewSignUp.this, LoginPage.class));
                                    progressDialog.cancel();

                                    firebaseFirestore.collection("User")
                                            .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                            .set(new UserModel(usernamepass, emailpass, numberpass, dobpass, passwordpass));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(NewSignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.cancel();
                                }
                            });
                } else {
                    Toast.makeText(NewSignUp.this, "Mobile no. should contain 10 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewSignUp.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

    private void redirectPage_signup() {
        finish();
        Intent intent = new Intent(this, BusActivity.class);
        startActivity(intent);
    }

    private void redirectPage_signup2(){
        finish();
        Intent intent = new Intent(this, First_Interface.class);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private String getMonth(int month) {
        if (month == 1)
            return "01";
        if (month == 2)
            return "02";
        if (month == 3)
            return "03";
        if (month == 4)
            return "04";
        if (month == 5)
            return "05";
        if (month == 6)
            return "06";
        if (month == 7)
            return "07";
        if (month == 8)
            return "08";
        if (month == 9)
            return "09";
        if (month == 10)
            return "10";
        if (month == 11)
            return "11";
        if (month == 12)
            return "12";

        //default should never happen
        return "01";

    }
}