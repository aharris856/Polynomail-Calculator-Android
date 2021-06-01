package com.appraham.polynomialCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //initialize buttons
    private Button linearButton, quadButton, cubicButton, helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign buttons
        linearButton = (Button) findViewById(R.id.linearB);
        quadButton = (Button) findViewById(R.id.quadraticB);
        cubicButton = (Button) findViewById(R.id.cubicB);
        helpButton = (Button) findViewById(R.id.helpB);
        //set button action listeners
        linearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity(1);
            }
        });
        quadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity(2);
            }
        });
        cubicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity(3);
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity(0);
            }
        });

    }
    public void openActivity(int activityNumber)
    {
        Intent intent;
        if(activityNumber == 1)intent = new Intent(this, Linear.class);
        else if(activityNumber == 2)intent = new Intent(this, Quadratic.class);
        else if(activityNumber == 3)intent = new Intent(this, Cubic.class);
        else intent = new Intent(this, HelpMenu.class);
        startActivity(intent);
    }
}