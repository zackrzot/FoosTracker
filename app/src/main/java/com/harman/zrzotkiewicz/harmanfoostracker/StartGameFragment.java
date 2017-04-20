package com.harman.zrzotkiewicz.harmanfoostracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;

public class StartGameFragment extends Fragment{

    StartGameListener activityCommander;

    public interface StartGameListener{
        public void startGame();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCommander = (StartGameListener) activity;
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

        startGameButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        startGameButtonClickedView(v);
                    }
                }
            );

        return view;
    }

    public void startGameButtonClickedView(View view){
        activityCommander.startGame();
    }


}
