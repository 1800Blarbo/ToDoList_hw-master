package com.mobiledev.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ToDoMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.date_toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Spinner dateSpinner = (Spinner) findViewById(R.id.days_of_week);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days_of_the_week, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dateSpinner.setAdapter(adapter);

        mInputEditText = (EditText) findViewById(R.id.activity_main_edittext);

        Button previousDay = (Button) findViewById(R.id.activity_previous_day_button);
        Button save = (Button) findViewById(R.id.activity_save_button);
        Button nextDay = (Button) findViewById(R.id.activity_next_day_button);

        previousDay.setOnClickListener(this);
        save.setOnClickListener(this);
        nextDay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_previous_day_button:
                /**
                 * change to previous day somehow, probably using the Tomas' ArrayAdapter above
                 */
                break;

            case R.id.activity_save_button:
                String inputString = mInputEditText.getText().toString().trim();
                // save inputString to "Preferences" somehow
                break;

            case R.id.activity_next_day_button:
                /**
                 * change to previous day somehow, probably using the Tomas' ArrayAdapter above
                 */
                break;

        }

    }

}