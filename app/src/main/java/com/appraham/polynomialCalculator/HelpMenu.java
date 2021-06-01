package com.appraham.polynomialCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpMenu extends AppCompatActivity {
    private Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_menu);
        //set help text
        //set textview to have superscript
        TextView instr = (TextView) findViewById(R.id.instructionText); // RETRIEVE YOUR TEXT VIEW
        instr.setText(Html.fromHtml("<div style = \"text-align:center\">Here is how it works:</div><div></div><div></div>Take whatever question you have and figure out, is it a linear, quadratic, or cubic Equation?<div>Linear Equations have no exponent, in other words they may have the forms ax+b=0 or ax=b. Quadratic equations have an exponent of 2, and Cubic equations have an exponent of 3.</div><div>Once you know what kind of problem it is, get everything to the same side so that the problem is equal to 0.<div>For example:</div><div>3x<sup>2</sup>=2x-1\tThis can be rewritten as 3x<sup>2</sup>-2x+1=0. Once you have done this simply put in the correct constants to the corresponding letter! Then press solve and your answer(s) will be produced!!!</div><div>Example of Valid inputs:<div>a = 3/2.1 b = -4.5/2 c = 3.32</div><div>Example of Invalid inputs:</div><div>a = 3.3w b = (2.2) c = 1.1.3</div><div></div>"));
        //set home button
        homeButton = (Button) findViewById(R.id.homeButtonHelp);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenu();
            }
        });
    }

    //go back to main menu
    public void mainMenu()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}