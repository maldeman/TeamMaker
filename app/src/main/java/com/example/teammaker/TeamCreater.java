package com.example.teammaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class TeamCreater extends AppCompatActivity {
private ArrayList<ArrayList<String>> teams;
private ArrayList<String>players;
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
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,players);

        edittext = findViewById(R.id.editTextTextPersonName2);
        recieve = getIntent();

    }

    public void makeTeams(View view){
        Random random = new Random();
        String numberString =(String) edittext.getText().toString();
        Integer number = Integer.parseInt(numberString);
        int randomNumb =(int) (Math.random()*(number-1))+1;
        int teamSize = recieve.getExtras().getStringArrayList("players").size()/number;
        int allPlayers = recieve.getExtras().getStringArrayList("players").size();

        for (int i = 0; i < number; i++) {
            teams.add(new ArrayList<String>());
        }

        for (String person:recieve.getExtras().getStringArrayList("players")) {
            if (teams.get(randomNumb).size()<teamSize) {
                teams.get(randomNumb).add(randomNumb + person);
            } else {
                randomNumb = (randomNumb+1)%number;
                teams.get(randomNumb).add(randomNumb + person);
            }
        }

        for (int i = 0; i < teams.size(); i++) {
            players.add(teams.get(i).toString());
        }

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void newTeam(View view){
        for (int i = 0; i <teams.size() ; i++) {
            teams.remove(teams.get(i));
        }

        for (int i = 0; i < players.size(); i++) {
            players.remove(players.get(i));
        }
        listview.setAdapter(adapter);

    }

    }