package com.harman.zrzotkiewicz.harmanfoostracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView textView_red_score;
    private TextView textView_blue_score;

    private TextView textView_rtp1_name;
    private TextView textView_rtp2_name;

    private TextView textView_btp1_name;
    private TextView textView_btp2_name;

    private Button button_rtp1_offg;
    private Button button_rtp1_defg;
    private Button button_rtp1_owng;
    private Button button_rtp1_slap;

    private Button button_rtp2_offg;
    private Button button_rtp2_defg;
    private Button button_rtp2_owng;
    private Button button_rtp2_slap;

    private Button button_btp1_offg;
    private Button button_btp1_defg;
    private Button button_btp1_owng;
    private Button button_btp1_slap;

    private Button button_btp2_offg;
    private Button button_btp2_defg;
    private Button button_btp2_owng;
    private Button button_btp2_slap;

    private TextView textView_rtp1_offg;
    private TextView textView_rtp1_defg;
    private TextView textView_rtp1_owng;
    private TextView textView_rtp1_slap;

    private TextView textView_rtp2_offg;
    private TextView textView_rtp2_defg;
    private TextView textView_rtp2_owng;
    private TextView textView_rtp2_slap;

    private TextView textView_btp1_offg;
    private TextView textView_btp1_defg;
    private TextView textView_btp1_owng;
    private TextView textView_btp1_slap;

    private TextView textView_btp2_offg;
    private TextView textView_btp2_defg;
    private TextView textView_btp2_owng;
    private TextView textView_btp2_slap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Locate the views on the HMI
        LocateViews();

        // Set button press listeners
        SetListeners();

    }



    public void LocateViews(){

        textView_red_score = (TextView) findViewById(R.id.textView_red_score);
        textView_blue_score = (TextView) findViewById(R.id.textView_blue_score);

        textView_rtp1_name = (TextView) findViewById(R.id.textView_rtp1_name);
        textView_rtp2_name = (TextView) findViewById(R.id.textView_rtp2_name);

        textView_btp1_name = (TextView) findViewById(R.id.textView_btp1_name);
        textView_btp2_name = (TextView) findViewById(R.id.textView_btp2_name);

        button_rtp1_offg = (Button) findViewById(R.id.button_rtp1_offg);
        button_rtp1_defg = (Button) findViewById(R.id.button_rtp1_defg);
        button_rtp1_owng = (Button) findViewById(R.id.button_rtp1_owng);
        button_rtp1_slap = (Button) findViewById(R.id.button_rtp1_slap);

        button_rtp2_offg = (Button) findViewById(R.id.button_rtp2_offg);
        button_rtp2_defg = (Button) findViewById(R.id.button_rtp2_defg);
        button_rtp2_owng = (Button) findViewById(R.id.button_rtp2_owng);
        button_rtp2_slap = (Button) findViewById(R.id.button_rtp2_slap);

        button_btp1_offg = (Button) findViewById(R.id.button_btp1_offg);
        button_btp1_defg = (Button) findViewById(R.id.button_btp1_defg);
        button_btp1_owng = (Button) findViewById(R.id.button_btp1_owng);
        button_btp1_slap = (Button) findViewById(R.id.button_btp1_slap);

        button_btp2_offg = (Button) findViewById(R.id.button_btp2_offg);
        button_btp2_defg = (Button) findViewById(R.id.button_btp2_defg);
        button_btp2_owng = (Button) findViewById(R.id.button_btp2_owng);
        button_btp2_slap = (Button) findViewById(R.id.button_btp2_slap);


        textView_rtp1_offg = (TextView) findViewById(R.id.textView_rtp1_offg);
        textView_rtp1_defg = (TextView) findViewById(R.id.textView_rtp1_defg);
        textView_rtp1_owng = (TextView) findViewById(R.id.textView_rtp1_owng);
        textView_rtp1_slap = (TextView) findViewById(R.id.textView_rtp1_slap);

        textView_rtp2_offg = (TextView) findViewById(R.id.textView_rtp2_offg);
        textView_rtp2_defg = (TextView) findViewById(R.id.textView_rtp2_defg);
        textView_rtp2_owng = (TextView) findViewById(R.id.textView_rtp2_owng);
        textView_rtp2_slap = (TextView) findViewById(R.id.textView_rtp2_slap);

        textView_btp1_offg = (TextView) findViewById(R.id.textView_btp1_offg);
        textView_btp1_defg = (TextView) findViewById(R.id.textView_btp1_defg);
        textView_btp1_owng = (TextView) findViewById(R.id.textView_btp1_owng);
        textView_btp1_slap = (TextView) findViewById(R.id.textView_btp1_slap);

        textView_btp2_offg = (TextView) findViewById(R.id.textView_btp2_offg);
        textView_btp2_defg = (TextView) findViewById(R.id.textView_btp2_defg);
        textView_btp2_owng = (TextView) findViewById(R.id.textView_btp2_owng);
        textView_btp2_slap = (TextView) findViewById(R.id.textView_btp2_slap);

    }

    public void SetListeners(){

        //region RTP1 Listeners
        button_rtp1_offg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GameState.AddRTP1OffG();
                        updateHMIValues();
                    }
                }
        );
        button_rtp1_offg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.SubRTP1OffG();
                updateHMIValues();
                return true;
            }
        });
        button_rtp1_defg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    GameState.AddRTP1DefG();
                    updateHMIValues();
                    }
                }
        );
        button_rtp1_defg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.SubRTP1DefG();
                updateHMIValues();
                return true;
            }
        });
        button_rtp1_owng.setOnClickListener(
                new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        GameState.AddRTP1OwnG();
                        updateHMIValues();
                    }
                }
        );
        button_rtp1_owng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.SubRTP1OwnG();
                updateHMIValues();
                return true;
            }
        });
        button_rtp1_slap.setOnClickListener(
                new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        GameState.AddRTP1Slap();
                        updateHMIValues();
                    }
                }
        );
        button_rtp1_slap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.SubRTP1Slap();
                updateHMIValues();
                return true;
            }
        });
        //endregion


    }

    private String formatInt(int val){
        String str = Integer.toString(val);
        if(str.length()==1)
            return "0"+str;
        return str;
    }


    public void updateHMIValues(){

        textView_red_score.setText(formatInt(GameState.GetRedTeamScore()));
        textView_blue_score.setText(formatInt(GameState.GetBlueTeamScore()));

        textView_rtp1_offg.setText(formatInt(GameState.GetRTP1OffG()));
        textView_rtp1_defg.setText(formatInt(GameState.GetRTP1DefG()));
        textView_rtp1_owng.setText(formatInt(GameState.GetRTP1OwnG()));
        textView_rtp1_slap.setText(formatInt(GameState.GetRTP1Slaps()));

        textView_rtp2_offg.setText(formatInt(GameState.GetRTP2OffG()));
        textView_rtp2_defg.setText(formatInt(GameState.GetRTP2DefG()));
        textView_rtp2_owng.setText(formatInt(GameState.GetRTP2OwnG()));
        textView_rtp2_slap.setText(formatInt(GameState.GetRTP2Slaps()));

        textView_btp1_offg.setText(formatInt(GameState.GetBTP1OffG()));
        textView_btp1_defg.setText(formatInt(GameState.GetBTP1DefG()));
        textView_btp1_owng.setText(formatInt(GameState.GetBTP1OwnG()));
        textView_btp1_slap.setText(formatInt(GameState.GetBTP1Slaps()));

        textView_btp2_offg.setText(formatInt(GameState.GetBTP2OffG()));
        textView_btp2_defg.setText(formatInt(GameState.GetBTP2DefG()));
        textView_btp2_owng.setText(formatInt(GameState.GetBTP2OwnG()));
        textView_btp2_slap.setText(formatInt(GameState.GetBTP2Slaps()));

    }




    
}
