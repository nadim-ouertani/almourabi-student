package com.nadim.almourabi.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nadim.almourabi.R;
import com.nadim.almourabi.Student;
import com.nadim.almourabi.helpers.studentTeacherR;

public class register extends AppCompatActivity {

    private String fn, ln, e, l, g, p, con;
    private EditText firstname, lastname, email, password;
    private Spinner level, group;
    private FirebaseAuth mAuth;

    studentTeacherR myFun = new studentTeacherR();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        spinners();
        firstname = findViewById(R.id.fn);
        lastname = findViewById(R.id.ln);
        email = findViewById(R.id.email);
        level = findViewById(R.id.level);
        group = findViewById(R.id.group);
        password = findViewById(R.id.password);
        TextView gotologin = findViewById(R.id.sign_in_button);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert all content to string
                fn = firstname.getText().toString().trim();
                ln = lastname.getText().toString().trim();
                e = email.getText().toString().trim();
                l = level.getSelectedItem().toString().trim();
                g = group.getSelectedItem().toString().trim();
                p = password.getText().toString().trim();

                con = getLevel(l) + "*" + g;

                if (myFun.validateRegister(firstname, lastname, email, password)) {
                    mAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Student student = new Student(mAuth.getCurrentUser().getUid(), fn, ln, con);
                                student.writeNewUser(mAuth.getCurrentUser().getUid(), fn, ln, con);
                                startActivity(new Intent(register.this, MainActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                            }

                            // ...
                        }
                    });
                } else {
                    System.out.println("ERROR");
                }
            }
        });

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, login.class));
                finish();
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        //password.setGravity(Gravity.RIGHT);
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(register.this, MainActivity.class));
            finish();
        }
    }


    private void spinners() {

        //LEVEL SPINNER

        Spinner level_spinner = findViewById(R.id.level);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> level_adapter = ArrayAdapter.createFromResource(this,
                R.array.level_array, R.layout.spinner);
        // Specify the layout to use when the list of choices appears
        level_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        level_spinner.setAdapter(level_adapter);

        //FIELD SPINNER

        Spinner field_spinner = findViewById(R.id.group);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> field_adapter = ArrayAdapter.createFromResource(this,
                R.array.group_array, R.layout.spinner);
        // Specify the layout to use when the list of choices appears
        field_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        field_spinner.setAdapter(field_adapter);
    }

    public String getLevel(String l) {
        switch (l) {
            case "First":
                l = "1";
                break;

            case "Second":
                l = "2";
                break;

            case "Third":
                l = "3";
                break;

            case "Fourth":
                l = "4";
                break;

            case "Fifth":
                l = "5";
                break;

            case "Sixth":
                l = "6";
                break;

            default:
                l = "Null";
                break;
        }

        return l;
    }

}


