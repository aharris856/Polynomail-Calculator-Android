package com.appraham.polynomialCalculator;

public class Polynomial{
    // ax^3+bx^2+cx+d = 0
    private double a, b, c, d;
    //RETURNS STRING ARRAY TO ACCOUNT FOR IMAGINARY NUMBERS
    public String[] solve(double v1, double v2, double v3, double v4)
    {
        if(v1 == 0 && v2 == 0 && v3 == 0)return new String[0];
        // initialize solution that will be returned
        // and populate a b c and d
        String[] sol;
        a = v1;
        b = v2;
        c = v3;
        d = v4;

        if(a!=0){ //a isn't 0 => cubic
            sol = cubic();
            resetValues();
            return sol;
        }

        if(b!=0){ //b isn't 0 => quadratic
            sol = quad();
            resetValues();
            return sol;
        }
        //c isn't 0 => linear
        sol = linear();
        resetValues();
        return sol;
    }

    //----- find linear sol to cx + d = 0 -------------------------------------------------------
    private String[] linear()
    {
        String[] solStr = new String[1]; // solution string array to return
        double sol = round((-d)/c);
        solStr[0] = ""+sol;
        return solStr;
    }

    //----- find quadratic sol to bx^2+cx+d = 0 -------------------------------------------------
    private String[] quad()
    {
        //2 solution cases, imaginary && not imaginary
        double disc = discriminant(b, c, d);
        String[] solStr;

        if(disc == 0){ // only 1 root
            solStr = new String[1];
            double sol = round((-c)/(2*b));
            solStr[0] = ""+sol;
            return solStr;
        }

        if(disc > 0){ // 2 real roots
            solStr = new String[2];
            //initialize first and second halves of quadratic formula
            double[] section = splitQuad(disc, b, c);
            double[] sol = new double[2];
            //generate 2 solutions
            sol[0] = round(section[0]+section[1]);
            sol[1] = round(section[0]-section[1]);
            solStr[0] = ""+sol[0];
            solStr[1] = ""+sol[1];

            return solStr;
        }

        if(disc < 0){ //2 imaginary roots
            solStr = new String[2];
            //initialize first and second halves of quadratic formula
            //however when calculating split, change disc to be -disc then in solStr add 'i' for imaginary
            double[] section = splitQuad(-disc, b, c); //section[1] will be the half containing the appended i
            //generate 2 solutions
            solStr[0] = round(section[0])+" + i*"+round(section[1]);
            solStr[1] = round(section[0])+" - i*"+round(section[1]);
            return solStr;
        }

        return null;
    }
    //split quadratic formula into 2 parts, will never be imaginary because input is pre adjusted.
    private double[] splitQuad(double disc, double a1, double b1)
    {
        double[] sol = new double[2];
        sol[0] = (-b1)/(2*a1);
        sol[1] = Math.sqrt(disc)/(2*a1);
        return sol;
    }
    //discriminant of a b and c aka (b^2-4ac)
    private double discriminant(double a1, double b1, double c1){
        return ((b1*b1)-4*a1*c1);
    }

    //----- find cubic sol to ax^3+bx^2+cx+d = 0 ------------------------------------------------
    private String[] cubic()
    {
        double[] determine = splitCubic();
        if(determine[2]>0)return method1(determine);
        else if(determine[0] == 0 && determine[1] == 0 && determine[2] == 0)return method2(determine);
        else return method3(determine); //determine[2] <= 0
    }

    //Only 1 real root method
    private String[] method1(double[] det)
    {
        double i = -det[1]/2+Math.sqrt(det[2]);
        double j = Math.cbrt(i);
        double k = -det[1]/2-Math.sqrt(det[2]);
        double l = Math.cbrt(k);
        String[] sol = new String[3];
		/*First half of the 2 imaginary solutions. if first part = 0
		then no str needed (do not want 0.0000000000+i*...)*/
        double holdSol = round(-(j+l)/2-b/(3*a));
        String holdSolStr = "";
        if(holdSol!=0) holdSolStr=String.valueOf(holdSol);

        sol[0] = String.valueOf(round(j+l-b/(3*a)));

        sol[1] = holdSolStr; //inital part of sol 1
        sol[2] = sol[1];     //and sol2

        sol[1] = sol[1]+" + i*"+round((j-l)*Math.sqrt(3)/2); //append imaginary parts
        sol[2] = sol[2]+" - i*"+round((j-l)*Math.sqrt(3)/2);

        return sol;
    }

    //3 equal real roots
    private String[] method2(double[] det)
    {
        String[] sol = new String[3];
        for(int i = 0; i < sol.length; i++)
            sol[i] = String.valueOf(round(-Math.cbrt(d/a)));
        return sol;
    }

    //3 real roots method
    private String[] method3(double[] det)
    {

        double i = Math.sqrt((det[1]*det[1])/4-det[2]);
        double j = Math.cbrt(i);
        double k = Math.acos(-det[1]/(2*i));
        double l = -j;
        double m = Math.cos(k/3);
        double n = Math.sqrt(3)*Math.sin(k/3);
        double o = -(b/(3*a));
        String[] sol = new String[3];
        sol[0] = String.valueOf(round((2*j*m+o)));
        sol[1] = String.valueOf(round(l*(m+n)+o));
        sol[2] = String.valueOf(round(l*(m-n)+o));
        return sol;
    }

    //split cubic formula into 3 factors that are used in the formula AND to determine the number of solutions and type of solutions.
    private double[] splitCubic()
    {
        double[] sol = new double[3];
        sol[0] = (c/a)-(b*b)/(3*a*a);
        sol[1] = ((2*b*b*b)/(a*a*a) - (9*b*c)/(a*a) + 27*d/a)/27;
        sol[2] = sol[0]*sol[0]*sol[0]/27+sol[1]*sol[1]/4;

        return sol;
    }
    //--------------------Extra tools------------------------------------------------------------
    //round to nearest 10 digits
    private double round(double n){
        return (double) Math.round(n*10000000000d)/10000000000d;
    }

    //reset values, done every calculation since only 1 'polynomial' object is created
    private void resetValues()
    {
        a = 0;
        b = 0;
        c = 0;
        d = 0;
    }
}