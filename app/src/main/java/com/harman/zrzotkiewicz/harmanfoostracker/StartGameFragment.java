package com.harman.zrzotkiewicz.harmanfoostracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class StartGameFragment extends Fragment{

    StartGameInterface startGameInterface;

    private TextView totalGames;
    private TextView totalGoals;

    public interface StartGameInterface {
        public void StartGameFragStartGame();
        public void StartGameFragHMILoaded();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            startGameInterface = (StartGameInterface) activity;
        }
        catch (ClassCastException e){
            activity.toString();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.content_start_game, container, false);

        final Button startGameButton = (Button) view.findViewById(R.id.button_startGame);
        totalGames = (TextView) view.findViewById(R.id.textView_totalGamesNumber);
        totalGoals = (TextView) view.findViewById(R.id.textView_totalGoalsNumber);

        startGameButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        StartGameButtonClickedView(v);
                    }
                }
            );

        HMILoaded();

        return view;
    }

    public void StartGameButtonClickedView(View view){
        startGameInterface.StartGameFragStartGame();
    }

    public void HMILoaded(){ startGameInterface.StartGameFragHMILoaded(); }

    public void UpdateDisplayStats(final int numGames, final int numGoals){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalGames.setText(Integer.toString(numGames));
                totalGoals.setText(Integer.toString(numGoals));
            }
        });
    }

}
