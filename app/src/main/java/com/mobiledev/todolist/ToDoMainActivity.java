package com.mobiledev.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ToDoMainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView mCurrentDay;
    private EditText mInputEditText;
    private int currentDay = 0;
    private Button previousDay, nextDay;
    private String[] plans = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);

        mInputEditText = (EditText) findViewById(R.id.activity_main_edittext);
        mCurrentDay = (TextView) findViewById(R.id.activity_main_current_day);
        previousDay = (Button) findViewById(R.id.activity_previous_day_button);
        nextDay = (Button) findViewById(R.id.activity_next_day_button);
        Button save = (Button) findViewById(R.id.activity_save_button);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.date_toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Spinner dateSpinner = (Spinner) findViewById(R.id.days_of_week);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days_of_the_week, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);

        previousDay.setOnClickListener(this);
        nextDay.setOnClickListener(this);
        save.setOnClickListener(this);
        dateSpinner.setOnItemSelectedListener(this);

        previousDay.setText(getResources().getStringArray(R.array.days_of_the_week)[(currentDay + 6) % 7]);
        nextDay.setText(getResources().getStringArray(R.array.days_of_the_week)[(currentDay+1)%7]);
        mCurrentDay.setText(getResources().getStringArray(R.array.days_of_the_week)[currentDay]);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.activity_previous_day_button:
                if (currentDay == 0) {
                    currentDay = 6;
                } else {
                    currentDay--;
                }
                updateLayout();
                break;

            case R.id.activity_save_button:
                plans[currentDay] = mInputEditText.getText().toString().trim();
                Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
                getPreferences(MODE_PRIVATE).edit().putString(currentDay + " ", plans[currentDay]).apply();
                break;

            case R.id.activity_next_day_button:
                if (currentDay == 6) {
                    currentDay = 0;
                } else {
                    currentDay++;
                }
                updateLayout();
                break;
        }

    }

    /** Updates user facing text. */
    public void updateLayout() {
        mInputEditText.setText("");
        mCurrentDay.setText(getResources().getStringArray(R.array.days_of_the_week)[currentDay]);
        previousDay.setText(getResources().getStringArray(R.array.days_of_the_week)[(currentDay + 6) % 7]);
        nextDay.setText(getResources().getStringArray(R.array.days_of_the_week)[(currentDay + 1) % 7]);
        if (plans[currentDay] == null) {
            mInputEditText.setHint("Your plans for " + getResources().getStringArray(R.array.days_of_the_week)[currentDay]);
        } else {
            mInputEditText.setText(plans[currentDay]);
        }
        if (getPreferences(MODE_PRIVATE).getString(currentDay + " ", plans[currentDay]).equals("")) {
            System.out.println("jews" + currentDay);
            mInputEditText.setHint("Your plans for " + getResources().getStringArray(R.array.days_of_the_week)[currentDay]);
        } else {
            System.out.println(currentDay);
            mInputEditText.setText(getPreferences(MODE_PRIVATE).getString(currentDay + " ", plans[currentDay]));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getItemAtPosition(position).toString()) {
            case "Monday":
                currentDay = 1;
                break;
            case "Tuesday":
                currentDay = 2;
                break;
            case "Wednesday":
                currentDay = 3;
                break;
            case "Thursday":
                currentDay = 4;
                break;
            case "Friday":
                currentDay = 5;
                break;
            case "Saturday":
                currentDay = 6;
                break;
            case "Sunday":
                currentDay = 0;
                break;
        }

        updateLayout();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}