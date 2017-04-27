package com.harman.zrzotkiewicz.harmanfoostracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddPlayerFragment extends Fragment {

    SubmitNewPlayerListener activityCommander;

    private EditText editTextPlayerName;
    private EditText editTextPin;
    private EditText editTextPinConfirm;
    private EditText editTextDob;
    private EditText editTextHomeTown;
    private EditText editTextBio;
    private Spinner spinnerJerseyNumber;
    private Spinner spinnerHandedness;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Calendar myCalendar = Calendar.getInstance();
    private ImageView playerPhoto;
    private Bitmap playerImage;


    public interface SubmitNewPlayerListener {
        void submitNewPlayer(PlayerData playerData);
        void playerImageClicked();
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
        editTextPin =(EditText) view.findViewById(R.id.editText_pin);
        editTextPinConfirm = (EditText) view.findViewById(R.id.editText_pin_confirm);
        editTextDob = (EditText) view.findViewById(R.id.editText_dob);
        editTextHomeTown = (EditText) view.findViewById(R.id.editText_hometown);
        editTextBio = (EditText) view.findViewById(R.id.editText_bio);
        spinnerJerseyNumber = (Spinner) view.findViewById(R.id.spinner_jerseyNumber);
        spinnerHandedness = (Spinner) view.findViewById(R.id.spinner_handedness);
        editTextHeight = (EditText) view.findViewById(R.id.editText_height);
        editTextWeight = (EditText) view.findViewById(R.id.editText_weight);
        final Button submitNewPlayerButton = (Button) view.findViewById(R.id.button_submit);
        playerPhoto = (ImageView) view.findViewById(R.id.imageView_playerPhoto);

        // Set player image captured to false
        playerImage = null;

        // Populate Handedness Spinner
        populateHandednessSpinner();

        // Populate Jersey Number Spinner
        populateJerseyNumberSpinner();

        // Create new player button clicked event listener
        submitNewPlayerButton.setOnClickListener((
                new View.OnClickListener() {
                    public void onClick(View v) {
                        submitNewPlayerButtonClickedView();
                    }
                }
        ));

        // Player photo image view clicked event listener
        playerPhoto.setOnClickListener((
                new View.OnClickListener() {
                    public void onClick(View v) {
                        playerPhotoIconClicked();
                    }
                }
        ));

        // Set calendar event listener
        editTextDob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    public void SetPlayerPhotoBitmap(Bitmap bitmap){
        playerPhoto.setImageBitmap(bitmap);
        playerImage = bitmap;
    }

    public void submitNewPlayerButtonClickedView(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                if(DatabaseManager.IsWebAppOnline()){
                    newPlayerButtonClickedActions();
                }
                else{
                    Utility.ShowAlertDialog(getActivity(), "Unable to reach server. You must be connected to the HARMAN intranet" +
                            " before adding a new player.");
                }
            }
        }).start();
    }

    public void newPlayerButtonClickedActions(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PlayerData playerData = new PlayerData();
                playerData.PlayerName = editTextPlayerName.getText().toString();
                playerData.Pin = editTextPin.getText().toString();
                playerData.PinConfirm = editTextPinConfirm.getText().toString();
                playerData.DOB = editTextDob.getText().toString();
                playerData.Hometown = editTextHomeTown.getText().toString();
                playerData.Bio = editTextBio.getText().toString();
                playerData.JerseyNumber = spinnerJerseyNumber.getSelectedItem().toString();
                playerData.Handedness = spinnerHandedness.getSelectedItem().toString();
                playerData.Height = editTextHeight.getText().toString();
                playerData.Weight = editTextWeight.getText().toString();
                playerData.PhotoBlob = Utility.BitmapToByteArrayBlobString(playerImage);
                activityCommander.submitNewPlayer(playerData);
            }
        });
    }

    public void playerPhotoIconClicked(){
        activityCommander.playerImageClicked();
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
        // Get available jersey numbers
        List<String> spinnerArray =  AddPlayerHelper.GetAvailableJerseyNumbers();
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
        editTextPin.setText("");
        editTextPinConfirm.setText("");
        playerPhoto.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera));
        playerImage = null;
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextDob.setText(sdf.format(myCalendar.getTime()));
    }



}
