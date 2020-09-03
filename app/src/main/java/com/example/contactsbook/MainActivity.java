package com.example.contactsbook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private TextView tvBirthDay;

    private EditText etName,etPhone,etEmail,etDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetDate = findViewById(R.id.btnSetDate);
        Button btnNext    = findViewById(R.id.btnNext);

        tvBirthDay    = findViewById(R.id.tvDate);
        etName        = findViewById(R.id.etName);
        etPhone       = findViewById(R.id.etPhone);
        etEmail       = findViewById(R.id.etEmail);
        etDescription = findViewById(R.id.etDescription);

        savedInstanceState = getIntent().getExtras();

        if (savedInstanceState != null){
            tvBirthDay.setText(savedInstanceState.getString("birthday"));
            etName.setText(savedInstanceState.getString("name"));
            etPhone.setText(savedInstanceState.getString("phone"));
            etEmail.setText(savedInstanceState.getString("email"));
            etDescription.setText(savedInstanceState.getString("description"));
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                createCalendar();
            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = dayOfMonth + "/" + month + "/" + year;
                tvBirthDay.setText(date);
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createCalendar(){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                    android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                    mOnDateSetListener,
                    year,month,day);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
    }
    public void next(View v){
        if(etName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty()) {
            Snackbar.make(v,"Please complete all fields with *",Snackbar.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(this, ConfirmData.class);
            i.putExtra("name",etName.getText().toString());
            i.putExtra("phone",etPhone.getText().toString());
            i.putExtra("birthday",tvBirthDay.getText().toString());
            i.putExtra("email",etEmail.getText().toString());
            i.putExtra("description",etDescription.getText().toString());
            startActivity(i);
            finish();
        }
    }
}
