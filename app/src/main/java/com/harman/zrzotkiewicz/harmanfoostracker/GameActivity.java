package com.harman.zrzotkiewicz.harmanfoostracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    //region Variables
    private int previousRedScore = 0;
    private int previousBlueScore = 0;

    private TextView textView_gameWin;

    private TextView textView_red_score;
    private TextView textView_blue_score;

    private TextView textView_rtp1_name;
    private TextView textView_rtp2_name;

    private TextView textView_rtp2_label;
    private TextView textView_btp2_label;

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

    private Button button_end_game;

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

    public Vibrator vibe;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Keep the game screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Locate the views on the HMI
        LocateViews();

        // Set button press listeners
        SetListeners();

        updateFullHMI();

        button_end_game.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        EndGameButtonClicked(v);
                    }
                }
        );

        // Set up vibrator
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void updateFullHMI(){
        textView_rtp1_name.setText(GameState.RTP1.PlayerName);
        textView_btp1_name.setText(GameState.BTP1.PlayerName);

        if(GameState.RTP2 == null)
            disableRTP2();
        else
            textView_rtp2_name.setText(GameState.RTP2.PlayerName);

        if(GameState.BTP2 == null)
            disableBTP2();
        else
            textView_btp2_name.setText(GameState.BTP2.PlayerName);
    }

    private void disableRTP2(){
        button_rtp2_offg.setVisibility(View.INVISIBLE);
        button_rtp2_defg.setVisibility(View.INVISIBLE);
        button_rtp2_owng.setVisibility(View.INVISIBLE);
        button_rtp2_slap.setVisibility(View.INVISIBLE);

        textView_rtp2_label.setVisibility(View.INVISIBLE);
        textView_rtp2_name.setVisibility(View.INVISIBLE);

        textView_rtp2_offg.setVisibility(View.INVISIBLE);
        textView_rtp2_defg.setVisibility(View.INVISIBLE);
        textView_rtp2_owng.setVisibility(View.INVISIBLE);
        textView_rtp2_slap.setVisibility(View.INVISIBLE);
    }

    private void disableBTP2(){
        button_btp2_offg.setVisibility(View.INVISIBLE);
        button_btp2_defg.setVisibility(View.INVISIBLE);
        button_btp2_owng.setVisibility(View.INVISIBLE);
        button_btp2_slap.setVisibility(View.INVISIBLE);

        textView_btp2_label.setVisibility(View.INVISIBLE);
        textView_btp2_name.setVisibility(View.INVISIBLE);

        textView_btp2_offg.setVisibility(View.INVISIBLE);
        textView_btp2_defg.setVisibility(View.INVISIBLE);
        textView_btp2_owng.setVisibility(View.INVISIBLE);
        textView_btp2_slap.setVisibility(View.INVISIBLE);
    }

    public void LocateViews(){

        textView_red_score = (TextView) findViewById(R.id.textView_red_score);
        textView_blue_score = (TextView) findViewById(R.id.textView_blue_score);
        textView_gameWin = (TextView) findViewById(R.id.textView_gameWin);

        textView_rtp1_name = (TextView) findViewById(R.id.textView_rtp1_name);
        textView_rtp2_name = (TextView) findViewById(R.id.textView_rtp2_name);

        textView_btp1_name = (TextView) findViewById(R.id.textView_btp1_name);
        textView_btp2_name = (TextView) findViewById(R.id.textView_btp2_name);

        textView_rtp2_label = (TextView) findViewById(R.id.textView_rtp2_label);
        textView_btp2_label = (TextView) findViewById(R.id.textView_btp2_label);

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

        button_end_game = (Button) findViewById(R.id.button_end_game);

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
                GameState.RTP1.AddOffGoal();
                updateHMIValues();
            }
        });

        button_rtp1_offg.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                GameState.RTP1.SubOffGoal();
                updateHMIValues();
                return true;
            }
        });

        button_rtp1_defg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.RTP1.AddDefGoal();
                updateHMIValues();
            }
        });

        button_rtp1_defg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP1.SubDefGoal();
                updateHMIValues();
                return true;
            }
        });

        button_rtp1_owng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            GameState.RTP1.AddOwnGoal();
            updateHMIValues();
            }
        });

        button_rtp1_owng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP1.SubOwnGoal();
                updateHMIValues();
                return true;
            }
        });

        button_rtp1_slap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                GameState.RTP1.AddSlap();
                updateHMIValues();
            }
        });

        button_rtp1_slap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP1.SubSlap();
                updateHMIValues();
                return true;
            }
        });
        //endregion

        //region RTP2 Listeners
        button_rtp2_offg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                GameState.RTP2.AddOffGoal();
                updateHMIValues();
            }
        });

        button_rtp2_offg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP2.SubOffGoal();
                updateHMIValues();
                return true;
            }
        });

        button_rtp2_defg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                GameState.RTP2.AddDefGoal();
                updateHMIValues();
            }
        });

        button_rtp2_defg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP2.SubDefGoal();
                updateHMIValues();
                return true;
            }
        });

        button_rtp2_owng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.RTP2.AddOwnGoal();
                updateHMIValues();
            }
        });

        button_rtp2_owng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP2.SubOwnGoal();
                updateHMIValues();
                return true;
            }
        });

        button_rtp2_slap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.RTP2.AddSlap();
                updateHMIValues();
            }
        });

        button_rtp2_slap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.RTP2.SubSlap();
                updateHMIValues();
                return true;
            }
        });
        //endregion

        //region BTP1 Listeners
        button_btp1_offg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                GameState.BTP1.AddOffGoal();
                updateHMIValues();
            }
        });

        button_btp1_offg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP1.SubOffGoal();
                updateHMIValues();
                return true;
            }
        });

        button_btp1_defg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                GameState.BTP1.AddDefGoal();
                updateHMIValues();
            }
        });

        button_btp1_defg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP1.SubDefGoal();
                updateHMIValues();
                return true;
            }
        });

        button_btp1_owng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.BTP1.AddOwnGoal();
                updateHMIValues();
            }
        });

        button_btp1_owng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP1.SubOwnGoal();
                updateHMIValues();
                return true;
            }
        });

        button_btp1_slap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.BTP1.AddSlap();
                updateHMIValues();
            }
        });

        button_btp1_slap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP1.SubSlap();
                updateHMIValues();
                return true;
            }
        });
        //endregion

        //region BTP2 Listeners
        button_btp2_offg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                GameState.BTP2.AddOffGoal();
                updateHMIValues();
            }
        });

        button_btp2_offg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP2.SubOffGoal();
                updateHMIValues();
                return true;
            }
        });

        button_btp2_defg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                GameState.BTP2.AddDefGoal();
                updateHMIValues();
            }
        });

        button_btp2_defg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP2.SubDefGoal();
                updateHMIValues();
                return true;
            }
        });

        button_btp2_owng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.BTP2.AddOwnGoal();
                updateHMIValues();
            }
        });

        button_btp2_owng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP2.SubOwnGoal();
                updateHMIValues();
                return true;
            }
        });

        button_btp2_slap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                GameState.BTP2.AddSlap();
                updateHMIValues();
            }
        });

        button_btp2_slap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                GameState.BTP2.SubSlap();
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
        vibe.vibrate(100);

        textView_red_score.setText(formatInt(GameState.GetRedTeamScore()));
        textView_blue_score.setText(formatInt(GameState.GetBlueTeamScore()));

        textView_rtp1_offg.setText(formatInt(GameState.RTP1.GetNumOffGoals()));
        textView_rtp1_defg.setText(formatInt(GameState.RTP1.GetNumDefGoals()));
        textView_rtp1_owng.setText(formatInt(GameState.RTP1.GetNumOwnGoals()));
        textView_rtp1_slap.setText(formatInt(GameState.RTP1.GetNumSlaps()));

        if(GameState.RTP2 != null) {
            textView_rtp2_offg.setText(formatInt(GameState.RTP2.GetNumOffGoals()));
            textView_rtp2_defg.setText(formatInt(GameState.RTP2.GetNumDefGoals()));
            textView_rtp2_owng.setText(formatInt(GameState.RTP2.GetNumOwnGoals()));
            textView_rtp2_slap.setText(formatInt(GameState.RTP2.GetNumSlaps()));
        }

        textView_btp1_offg.setText(formatInt(GameState.BTP1.GetNumOffGoals()));
        textView_btp1_defg.setText(formatInt(GameState.BTP1.GetNumDefGoals()));
        textView_btp1_owng.setText(formatInt(GameState.BTP1.GetNumOwnGoals()));
        textView_btp1_slap.setText(formatInt(GameState.BTP1.GetNumSlaps()));

        if(GameState.BTP2 != null) {
            textView_btp2_offg.setText(formatInt(GameState.BTP2.GetNumOffGoals()));
            textView_btp2_defg.setText(formatInt(GameState.BTP2.GetNumDefGoals()));
            textView_btp2_owng.setText(formatInt(GameState.BTP2.GetNumOwnGoals()));
            textView_btp2_slap.setText(formatInt(GameState.BTP2.GetNumSlaps()));
        }

        button_end_game.setEnabled(isGameOver());

        checkForWin();

    }

    private Boolean isGameOver(){
        if((GameState.GetRedTeamScore() >= 10) || (GameState.GetBlueTeamScore() >= 10))
            return true;
        return false;
    }

    public void EndGameButtonClicked(View view){

        if((GameState.GetRedTeamScore() == 10) && (GameState.GetBlueTeamScore() == 10)){
            Utility.ShowAlertDialog(this, "A tied game is not possible.\n\nPlease fix the score before ending the game.");
            return;
        }

        if((GameState.GetRedTeamScore() > 10) || (GameState.GetBlueTeamScore() > 10)){
            Utility.ShowAlertDialog(this, "A score greater than 10 is not possible.\n\nPlease fix the score before ending the game.");
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure you want to end the game?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        FinalizeGame();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }

    public void FinalizeGame(){
        // Set end game time
        GameState.SetEndTime();

        // Submit score to web
        submitScore();

        // Reset the game state
        GameState.ResetGameState();

        // Return to home screen.
        startMainActivity();
    }

    private void submitScore(){




    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Back button pressed")
                .setMessage("Are you sure you want to end the game?\n\nAll progress will be lost.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        GameState.ResetGameState();
                        backButtonEvent();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void backButtonEvent(){
        super.onBackPressed();
    }

    private void checkForWin(){
        if(previousRedScore == 9 && GameState.GetRedTeamScore() == 10)
            redTeamWinAnimation();

        if(previousBlueScore == 9 && GameState.GetBlueTeamScore() == 10)
            blueTeamWinAnimation();

        previousRedScore = GameState.GetRedTeamScore();
        previousBlueScore = GameState.GetBlueTeamScore();
    }

    private void redTeamWinAnimation(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    UpdateGameWinView(true, "RED TEAM WINS!", R.color.redTeam);
                    try { Thread.sleep(100); }
                    catch(Exception ex) {}
                    UpdateGameWinView(false, "RED TEAM WINS!", R.color.redTeam);
                    try { Thread.sleep(100); }
                    catch(Exception ex) {}
                }
            }
        }).start();
    }

    private void blueTeamWinAnimation(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    UpdateGameWinView(true, "BLUE TEAM WINS!", R.color.blueTeam);
                    try { Thread.sleep(100); }
                    catch(Exception ex) {}
                    UpdateGameWinView(false, "BLUE TEAM WINS!", R.color.blueTeam);
                    try { Thread.sleep(100); }
                    catch(Exception ex) {}
                }
            }
        }).start();
    }

    public void UpdateGameWinView(final Boolean visible, final String text, final int resourceColorId){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(visible)
                    textView_gameWin.setVisibility(View.VISIBLE);
                else
                    textView_gameWin.setVisibility(View.INVISIBLE);
                textView_gameWin.setText(text);
                textView_gameWin.setTextColor(getResources().getColor(resourceColorId));
            }
        });
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("A", "B");
        finish();
        startActivity(intent);
    }


}
