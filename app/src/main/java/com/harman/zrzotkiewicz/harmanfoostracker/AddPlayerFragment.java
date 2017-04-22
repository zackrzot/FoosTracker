package com.harman.zrzotkiewicz.harmanfoostracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPlayerFragment extends Fragment {

    SubmitNewPlayerListener activityCommander;

    private EditText editTextPlayerName;
    private EditText editTextDob;
    private EditText editTextHomeTown;
    private EditText editTextBio;
    private Spinner spinnerJerseyNumber;
    private Spinner spinnerHandedness;
    private EditText editTextHeight;
    private EditText editTextWeight;


    public interface SubmitNewPlayerListener {
        public void submitNewPlayer(PlayerData playerData);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommander = (SubmitNewPlayerListener) activity;
        } catch (ClassCastException e) {
            activity.toString();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_add_player, container, false);

        editTextPlayerName = (EditText) view.findViewById(R.id.editText_name);
        editTextDob = (EditText) view.findViewById(R.id.editText_dob);
        editTextHomeTown = (EditText) view.findViewById(R.id.editText_hometown);
        editTextBio = (EditText) view.findViewById(R.id.editText_bio);
        spinnerJerseyNumber = (Spinner) view.findViewById(R.id.spinner_jerseyNumber);
        spinnerHandedness = (Spinner) view.findViewById(R.id.spinner_handedness);
        editTextHeight = (EditText) view.findViewById(R.id.editText_height);
        editTextWeight = (EditText) view.findViewById(R.id.editText_weight);
        final Button submitNewPlayerButton = (Button) view.findViewById(R.id.button_submit);

        // Populate Handedness Spinner
        populateHandednessSpinner();

        // Populate Jersey Number Spinner
        populateJerseyNumberSpinner();

        submitNewPlayerButton.setOnClickListener((
                new View.OnClickListener() {
                    public void onClick(View v) {
                        submitNewPlayerButtonClickedView(v);
                    }
                }
        ));

        editTextDob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    public void submitNewPlayerButtonClickedView(View view){
        PlayerData playerData = new PlayerData();
        playerData.PlayerName = editTextPlayerName.getText().toString();
        playerData.DOB = editTextDob.getText().toString();
        playerData.Hometown = editTextHomeTown.getText().toString();
        playerData.Bio = editTextBio.getText().toString();
        playerData.JerseyNumber = spinnerJerseyNumber.getSelectedItem().toString();
        playerData.Handedness = spinnerHandedness.getSelectedItem().toString();
        playerData.Height = editTextHeight.getText().toString();
        playerData.Weight = editTextWeight.getText().toString();

        activityCommander.submitNewPlayer(playerData);
    }

    private void populateHandednessSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.handedness_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerHandedness.setAdapter(adapter);
    }

    private void populateJerseyNumberSpinner(){
        List<String> spinnerArray =  new ArrayList<String>();
        for(int i = 1; i < 100; i++){
            spinnerArray.add(Integer.toString(i));
        }
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerJerseyNumber.setAdapter(adapter);
    }

    public void ResetFields(){
        editTextPlayerName.setText("");
        editTextDob.setText("");
        editTextHomeTown.setText("");
        editTextBio.setText("");
        spinnerJerseyNumber.setSelection(0);
        spinnerHandedness.setSelection(0);
        editTextHeight.setText("");
        editTextWeight.setText("");
    }

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTextDob.setText(sdf.format(myCalendar.getTime()));
    }

}
