package com.appraham.polynomialCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Linear extends AppCompatActivity {

    private Button homeButton;
    private Button solve;
    private TextView solDisplay;
    private Polynomial poly;
    private InputConverter inputConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        //initialize poly solver, input converter, and solution display
        solDisplay = (TextView) findViewById(R.id.linearSolution);
        poly = new Polynomial();
        inputConverter = new InputConverter();
        //Home button
        homeButton = (Button) findViewById(R.id.homeButtonLinear);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenu();
            }
        });
        //Solve Button
        solve = (Button) findViewById(R.id.linearSolve);
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveProblem();
            }
        });
    }
    //solve linear problem
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
        String[] solution = poly.solve(0, 0, validInput[0], validInput[1]);
        displaySolution(solution);
    }
    //get user input from 2 fields to check for validity and solve
    private String[] retrieveInput()
    {
        EditText inputA = (EditText) findViewById(R.id.linearInputA);
        EditText inputB = (EditText) findViewById(R.id.linearInputB);
        String[] input = new String[2];
        input[0] = inputA.getText().toString();
        input[1] = inputB.getText().toString();

        return input;
    }
    //display solution
    private void displaySolution(String[] sol)
    {
        solDisplay.setText("\n\t\tSolution:");
        String addText = "";
        for(int i = 0; i < sol.length; i++)
            addText = addText+"\n\n\t\t\tx = "+sol[i];
        solDisplay.setText(solDisplay.getText().toString()+addText);
    }
    //display error message
    private void errorMsg()
    {
        solDisplay.setText("\n\n\t\tERROR: INVALID INPUT");
    }

    //go back to main menu
    private void mainMenu()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}