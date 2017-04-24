package com.harman.zrzotkiewicz.harmanfoostracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        StartGameFragment.StartGameInterface,
        AddPlayerFragment.SubmitNewPlayerListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Create initial fragment
        Fragment fragment = new StartGameFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentFrame, fragment);
        transaction.commit();

        // Config toolbar
        setToolbarTitle("");
    }

    public void setToolbarTitle(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    // This is called by the startGameFragment when the start game button is pressed
    @Override
    public void StartGameFragStartGame() {
        Intent intent = new Intent(this, GameSetupActivity.class);
        intent.putExtra("A", "B");
        startActivity(intent);
    }

    @Override
    public void StartGameFragHMILoaded() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                StartGameFragment fragment = (StartGameFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentFrame);
                fragment.UpdateDisplayStats(WebAPIHelper.GetTotalNumberOfGames(), WebAPIHelper.GetTotalNumberOfGoals());
            }
        }).start();
    }

    public void onFabClicked(){
        Snackbar.make(findViewById(android.R.id.content), "Connecting to server...", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        new Thread(new Runnable(){
            @Override
            public void run() {
                if(WebAPIHelper.IsWebAppOnline()){
                    Snackbar.make(findViewById(android.R.id.content), "Syncing data...", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(findViewById(android.R.id.content), "ERROR: Unable to reach server.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        }).start();
    }

    // This is called by the addNewPlayer fragment when the form is submitted
    @Override
    public void submitNewPlayer(PlayerData playerData) {
        // Close keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        // Check for valid values
        // If any fields are empty
        if (playerData.AreAnyFieldsEmpty()) {
            // Create a dialog and return if incomplete
            showAlertDialog("You must complete all fields to create a new player.");
        }
        // All fields have been completed
        else {
            // Attempt to create new player
            String result = NewPlayerHelper.CreateNewPlayer(playerData);
            // No issues, player created
            if (result.equals("null")) {
                showAlertDialog("New player created!");
                AddPlayerFragment fragment = (AddPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentFrame);
                fragment.ResetFields();
            }
            // Unable to create the player
            else {
                // Alert user of player add failure
                showAlertDialog("Unable to create a new player:\n" + result);
            }
        }
    }

    private void showAlertDialog(String message){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = new Fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_start_game) {
            fragment = new StartGameFragment();
            setToolbarTitle("");
        } else if (id == R.id.nav_add_player) {
            fragment = new AddPlayerFragment();
            setToolbarTitle("Add Player");
        }else if (id == R.id.nav_leaderboards) {
            fragment = new LeaderboardsFragment();
            setToolbarTitle("Leaderboards");
        } else if (id == R.id.nav_global_stats) {
            fragment = new GlobalStatsFragment();
            setToolbarTitle("Global Stats");
        } else if (id == R.id.nav_player_stats) {
            fragment = new PlayerStatsFragment();
            setToolbarTitle("Player Stats");
        }
        // Finalize transaction
        transaction.replace(R.id.fragmentFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
