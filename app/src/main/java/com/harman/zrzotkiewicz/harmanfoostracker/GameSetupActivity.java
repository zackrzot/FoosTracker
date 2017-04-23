package com.harman.zrzotkiewicz.harmanfoostracker;

import android.content.Intent;
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
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("A", "B");
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
