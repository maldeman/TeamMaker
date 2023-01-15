package com.example.teammaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText plaintext;
    private ListView listview;
    private ArrayList<String> players;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plaintext = findViewById(R.id.personname);
        listview = findViewById(R.id.listview);
        players = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,players);
    }

    public void addPlayer(View view){
        String playerName = plaintext.getText().toString();
        if(!playerName.isEmpty()) {
            players.add(playerName);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public void goToteamcreater(View view){
        Intent intent = new Intent(this,TeamCreater.class);
        intent.putExtra("players",players);
        startActivity(intent);
    }

}