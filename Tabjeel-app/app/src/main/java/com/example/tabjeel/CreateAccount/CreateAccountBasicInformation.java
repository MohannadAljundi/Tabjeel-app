package com.example.tabjeel.CreateAccount;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tabjeel.R;

import java.util.Calendar;

public class CreateAccountBasicInformation extends AppCompatActivity implements View.OnClickListener {

//    private Spinner gender_spinner;
    //private CheckedTextView gender_spinner;
    private String gender;
    DatePickerDialog picker;
    EditText Date_Picker_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_basic_information);

//        gender_spinner = findViewById(R.id.basic_info_sex_selection);
        Date_Picker_txt = findViewById(R.id.date_picker);
//        findViewById(R.id.continue_basic_activity_btn).setOnClickListener(this);

        /*
         String[] GenderArray = {"الجنس","ذكر","انثى"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_gender_row,R.id.basic_info_sex_selection,GenderArray);

        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = String.valueOf(gender_spinner.getSelectedItem());
                if(i == 0){
                    gender_spinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
         */

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, R.layout.spinner_item);
//        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//        gender_spinner.setAdapter(adapter);


        Date_Picker_txt.setInputType(InputType.TYPE_NULL);
        Date_Picker_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CreateAccountBasicInformation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Date_Picker_txt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.continue_basic_activity_btn:{
//                Intent i = new Intent(getApplicationContext(), CreateAccountLocation.class);
//                startActivity(i);
//            }break;
        }
    }
}