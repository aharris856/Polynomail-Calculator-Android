package com.appraham.polynomialCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cubic extends AppCompatActivity {

    private Button homeButton;
    private Button solve;
    private TextView solDisplay;
    private Polynomial poly;
    private InputConverter inputConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubic);
        //initialize poly solver, input converter, and solution display
        solDisplay = (TextView) findViewById(R.id.cubicSolution);
        inputConverter = new InputConverter();
        poly = new Polynomial();
        //set textview to have superscript
        TextView text = (TextView) findViewById(R.id.questionCubic); // RETRIEVE YOUR TEXT VIEW
        text.setText(Html.fromHtml("Equation Format: ax<sup>3</sup> + bx<sup>2</sup> + cx + d = 0"));
        //set home button
        homeButton = (Button) findViewById(R.id.homeButtonCubic);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenu();
            }
        });
        //Solve Button
        solve = (Button) findViewById(R.id.cubicSolve);
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveProblem();
            }
        });
    }
    //solve cubic problem
    private void solveProblem()
    {
        //get user Input
        String[] input = retrieveInput();
        //check if user input is valid, if valid save to validInput array
        double[] validInput;
        if(inputConverter.isValid(input))validInput = inputConverter.getInput();
        else{
            errorMsg();
            return;
        }
        //solve solution
        String[] solution = poly.solve(validInput[0], validInput[1], validInput[2], validInput[3]);
        displaySolution(solution);
    }
    //get user input from 2 fields to check for validity and solve
    private String[] retrieveInput()
    {
        EditText inputA = (EditText) findViewById(R.id.cubicInputA);
        EditText inputB = (EditText) findViewById(R.id.cubicInputB);
        EditText inputC = (EditText) findViewById(R.id.cubicInputC);
        EditText inputD = (EditText) findViewById(R.id.cubicInputD);
        String[] input = new String[4];
        input[0] = inputA.getText().toString();
        input[1] = inputB.getText().toString();
        input[2] = inputC.getText().toString();
        input[3] = inputD.getText().toString();

        return input;
    }
    //display solution
    private void displaySolution(String[] sol)
    {
        solDisplay.setText("\n\t\tSolutions:");
        String addText = "";
        for(int i = 0; i < sol.length; i++)
            addText = addText+"\n\n\t\t\tx"+(i+1)+" = "+sol[i];
        solDisplay.setText(solDisplay.getText().toString()+addText);
    }
    //display error message
    private void errorMsg()
    {
        solDisplay.setText("\n\n\t\tERROR: INVALID INPUT");
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