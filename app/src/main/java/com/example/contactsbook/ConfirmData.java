package com.example.contactsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmData extends AppCompatActivity {
    TextView tvName, tvPhone, tvEmail, tvDescription, tvBirthDay;
    Button btnEdit;
    Contacts myContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_data);

        Bundle parameters = getIntent().getExtras();

        assert parameters != null;
        myContact = new Contacts(parameters.getString("name"),parameters.getString("phone"),parameters.getString("email"),
                    parameters.getString("birthday"),parameters.getString("description"));

        tvName        = findViewById(R.id.tvName);
        tvPhone       = findViewById(R.id.tvPhone);
        tvEmail       = findViewById(R.id.tvEmail);
        tvDescription = findViewById(R.id.tvDescription);
        tvBirthDay    = findViewById(R.id.tvBirthDay);
        btnEdit       = findViewById(R.id.btnEdit);

        tvName.setText       (myContact.getName());
        tvPhone.setText      (myContact.getPhone());
        tvBirthDay.setText   (myContact.getBirthday());
        tvEmail.setText      (myContact.getEmail());
        tvDescription.setText(myContact.getDescription());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }
    public void edit(){
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("name",tvName.getText().toString());
        i.putExtra("phone",tvPhone.getText().toString());
        i.putExtra("birthday",tvBirthDay.getText().toString());
        i.putExtra("email",tvEmail.getText().toString());
        i.putExtra("description",tvDescription.getText().toString());
        startActivity(i);
        finish();
    }
}