package com.appraham.polynomialCalculator;

public class InputConverter {
    private double[] convertedInput;
    public boolean isValid(String[] input)
    {
        convertedInput = new double[0];
        for(int i = 0; i < input.length; i++)
            if(!checkValidHelper(input[i]))return false;
        return true;
    }
    public double[] getInput()
    {
        return convertedInput;
    }
    //----------------------------------------------
    private boolean checkValidHelper(String input)
    {
        try{
            double tmpConvert = 0;
            if(input.contains("/")){
                String[] inputArr = input.split("/");
                if(inputArr.length!=2)return false;
                else{
                    double num = Double.parseDouble(inputArr[0]);
                    double denom = Double.parseDouble(inputArr[1]);
                    tmpConvert = num/denom;
                    addInput(tmpConvert);
                }
            }else{
                tmpConvert = Double.parseDouble(input);
                addInput(tmpConvert);
            }
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    private void addInput(double number)
    {
        double[] newArr = new double[convertedInput.length+1];
        for(int i = 0; i < convertedInput.length; i++)
            newArr[i] = convertedInput[i];
        newArr[convertedInput.length] = number;
        convertedInput = newArr;
    }
}