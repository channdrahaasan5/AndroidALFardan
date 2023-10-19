package com.self.androidsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    AppCompatEditText userName, email, password, confirm_password;
    AppCompatButton submit;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("\"[_a-zA-Z0-9]+(\\\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\\\.[A-Za-z0-9]+(\\\\.[A-Za-z0-9]*)*", Pattern.CASE_INSENSITIVE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validations()) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("name",String.valueOf(userName.getText()));
                        obj.put("email",String.valueOf(email.getText()));
                        obj.put("password",String.valueOf(password.getText()));
                        FileClass fc = new FileClass();
                        if(fc.writeJsonFile(getApplicationContext(),obj)) {
                            Toast.makeText(RegistrationActivity.this, "Date saved successfully.", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public Boolean validations() {
        if(userName.getText().length() == 0) {
            Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email.getText().length() == 0) {
            Toast.makeText(this, "Please Enter EmailID", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (validate(String.valueOf(email.getText()))) {
            Toast.makeText(this, "Please Enter Valide EmailID", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().length() == 0) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(confirm_password.getText().length() == 0) {
            Toast.makeText(this, "Please Enter Comfirm Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!String.valueOf(password.getText()).equals(String.valueOf(confirm_password.getText()))) {
            Toast.makeText(this, "Entered Passwords are incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}