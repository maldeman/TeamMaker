package com.example.teammaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TeamCreater extends AppCompatActivity {
private ArrayList<ArrayList<String>> teams;
private ArrayList<String>players;
 private ArrayList<String>players1;
private ArrayAdapter<String>adapter;
private ListView listview;
private EditText edittext;
private Intent recieve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_creater);
        listview = findViewById(R.id.listview);
        teams = new ArrayList<ArrayList<String>>();
        players = new ArrayList<>();
        players1 = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,players1);

        edittext = findViewById(R.id.editTextTextPersonName2);
        recieve = getIntent();

    }

    public void makeTeams(View view) {
        String numberString =(String) edittext.getText().toString();
        Integer amountofteams = Integer.parseInt(numberString);
        int teamsize = recieve.getExtras().getStringArrayList("players").size()/amountofteams;
        // Error checking

        for (String person:recieve.getExtras().getStringArrayList("players")){
            players.add(person);
        }

        if (teamsize * amountofteams > players.size()) {
            System.out.println("Error: Not enough players to form the specified number of teams with the specified team size.");
            return;
        }

        // Shuffle the players
        Collections.shuffle(players);

        // Create an ArrayList to store the teams
        for (int i = 0; i < amountofteams; i++) {
            teams.add(new ArrayList<String>());
        }

        // Distribute the players into the teams
        for (int i = 0; i < players.size(); i++) {
            int teamIndex = i % amountofteams;
            teams.get(teamIndex).add(players.get(i));
        }
        for (int i = 0; i <teams.size() ; i++) {
            players1.add(teams.get(i).toString());
        }

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void resetTeam(View view){
        for (int i = 0; i <teams.size() ; i++) {
            teams.remove(teams.get(i));
        }

        for (int i = 0; i < players1.size(); i++) {
            players1.remove(players1.get(i));
        }

        for (int i = 0; i < players.size(); i++) {
            players.remove(players.get(i));
        }

        listview.setAdapter(adapter);

    }

    }