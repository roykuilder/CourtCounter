package com.example.android.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class Names extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.names);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                EditText playerNameA = (EditText) findViewById(R.id.name_player_a);
                EditText playerNameB = (EditText) findViewById(R.id.name_player_b);
                MainActivity.namePlayerA = playerNameA.getText().toString();
                MainActivity.namePlayerB = playerNameB.getText().toString();

                Intent mainActivityIntent = new Intent(Names.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}
