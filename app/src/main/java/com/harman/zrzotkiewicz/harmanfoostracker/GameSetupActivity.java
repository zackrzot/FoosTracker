package com.harman.zrzotkiewicz.harmanfoostracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameSetupActivity extends AppCompatActivity {

    private Spinner spinnerRTP1;
    private Spinner spinnerRTP2;
    private Spinner spinnerBTP1;
    private Spinner spinnerBTP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        final Button kickOffButton = (Button) this.findViewById(R.id.button_kickoff);

        kickOffButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        KickOffButtonClicked(v);
                    }
                }
        );

        // Locate spinners
        spinnerRTP1 = (Spinner) findViewById(R.id.spinner_rt_p1);
        spinnerRTP2 = (Spinner) findViewById(R.id.spinner_rt_p2);
        spinnerBTP1 = (Spinner) findViewById(R.id.spinner_bt_p1);
        spinnerBTP2 = (Spinner) findViewById(R.id.spinner_bt_p2);

        // Populate player spinners
        populatePlayerSpinners();
    }

    public void KickOffButtonClicked(View view){

        if(validatePlayerSelection()) {

            // Set game values
            GameState.SetRTP1Name(spinnerRTP1.getSelectedItem().toString());
            GameState.SetRTP2Name(spinnerRTP2.getSelectedItem().toString());
            GameState.SetBTP1Name(spinnerBTP1.getSelectedItem().toString());
            GameState.SetBTP2Name(spinnerBTP2.getSelectedItem().toString());

            GameState.SetStartTime();
            GameState.SetGameActive();

            startGameActivity();
        }

    }

    private Boolean validatePlayerSelection(){

        // Red Team - If player 1 is empty but player 2 is assigned, move 2 to 1
        if(!isPlayerSelectedInSpinner(spinnerRTP1) && isPlayerSelectedInSpinner(spinnerRTP2)){
            spinnerRTP1.setSelection(spinnerRTP2.getSelectedItemPosition());
            spinnerRTP2.setSelection(0);
        }

        // Blue Team - If player 1 is empty but player 2 is assigned, move 2 to 1
        if(!isPlayerSelectedInSpinner(spinnerBTP1) && isPlayerSelectedInSpinner(spinnerBTP2)){
            spinnerBTP1.setSelection(spinnerBTP2.getSelectedItemPosition());
            spinnerBTP2.setSelection(0);
        }

        // Make sure each team has at least one player
        if(!isPlayerSelectedInSpinner(spinnerRTP1) || !isPlayerSelectedInSpinner(spinnerBTP1)) {
            new AlertDialog.Builder(this)
                    .setMessage("There must be at least one player on each team.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    })
                    .show();
            return false;
        }

        // Make sure players are unique
        if((spinnerRTP1.getSelectedItemPosition() != 0) && (
                (spinnerRTP1.getSelectedItemPosition() == spinnerRTP2.getSelectedItemPosition()) ||
                (spinnerRTP1.getSelectedItemPosition() == spinnerBTP1.getSelectedItemPosition()) ||
                (spinnerRTP1.getSelectedItemPosition() == spinnerBTP2.getSelectedItemPosition()))){
            warnOfDuplicatePlayer(spinnerRTP1.getSelectedItem().toString());
            return false;
        }
        if((spinnerRTP2.getSelectedItemPosition() != 0) && (
                (spinnerRTP2.getSelectedItemPosition() == spinnerBTP1.getSelectedItemPosition()) ||
                (spinnerRTP2.getSelectedItemPosition() == spinnerBTP2.getSelectedItemPosition()) ||
                (spinnerRTP2.getSelectedItemPosition() == spinnerRTP1.getSelectedItemPosition()))){
            warnOfDuplicatePlayer(spinnerRTP2.getSelectedItem().toString());
            return false;
        }
        if((spinnerBTP1.getSelectedItemPosition() != 0) && (
                (spinnerBTP1.getSelectedItemPosition() == spinnerBTP2.getSelectedItemPosition()) ||
                (spinnerBTP1.getSelectedItemPosition() == spinnerRTP1.getSelectedItemPosition()) ||
                (spinnerBTP1.getSelectedItemPosition() == spinnerRTP2.getSelectedItemPosition()))){
            warnOfDuplicatePlayer(spinnerBTP1.getSelectedItem().toString());
            return false;
        }
        if((spinnerBTP2.getSelectedItemPosition() != 0) && (
                (spinnerBTP2.getSelectedItemPosition() == spinnerRTP1.getSelectedItemPosition()) ||
                (spinnerBTP2.getSelectedItemPosition() == spinnerRTP2.getSelectedItemPosition()) ||
                (spinnerBTP2.getSelectedItemPosition() == spinnerBTP1.getSelectedItemPosition()))){
            warnOfDuplicatePlayer(spinnerBTP2.getSelectedItem().toString());
            return false;
        }

        return true;
    }

    private void warnOfDuplicatePlayer(String name){
        new AlertDialog.Builder(this)
                .setMessage("The player "+name+" cannot be used in more than one spot.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .show();
    }

    private Boolean isPlayerSelectedInSpinner(Spinner spinner){
        if(spinner.getSelectedItemPosition()!=0)
            return true;
        return false;
    }

    private void startGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("A", "B");
        finish();
        startActivity(intent);
    }

    private  void populatePlayerSpinners(){
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("No Player");
        spinnerArray.add("Zack");
        spinnerArray.add("Adam");
        spinnerArray.add("Kelly");
        spinnerArray.add("Chad");
        spinnerArray.add("Nancy");


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerRTP1.setAdapter(adapter);
        spinnerRTP2.setAdapter(adapter);
        spinnerBTP1.setAdapter(adapter);
        spinnerBTP2.setAdapter(adapter);

    }

}
