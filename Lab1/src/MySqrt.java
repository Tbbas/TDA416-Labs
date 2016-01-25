/**
 * The first assignment of the lab.
 * @author Tobias Alldén
 *
 */
public class MySqrt {
    public static final double EPS = 0.000001;
    public static double mySqrtLoop(double x, double epsilon) {
        double yMin = 0;
        double yMax = 0;
        double yMitt = 0;
        if(x==0) {
            return 0;
        }
        if(x>=0 && x<=1 ) {
            yMin = 0;
            yMax = 1;
        }
        if(x>1) {
            yMin = 1;
            yMax = x;
        }

        while (true) {
            yMitt = (yMin + yMax)/2;
            if((yMitt*yMitt < x+epsilon && yMitt*yMitt > x)
                    || yMitt*yMitt > x-epsilon && yMitt*yMitt <x) {
                break;
            } else {
                if(yMitt*yMitt>x) {
                    yMax = yMitt;
                } else {
                    yMin = yMitt;
                }
            }
        }
        return yMitt;

    }

    /**
     * First decides bounds, then calls for the recursive helper function with the bounds.
     * @param x
     * @param epsilon
     * @return
     */
    public static double mySqrtRecurse(double x, double epsilon) {
        double yMin = 0;
        double yMax = 0;
        if(x==0) {
            return 0;
        }
        if(x>=0 && x<=1 ) {
            yMin = 0;
            yMax = 1;
        }
        if(x>1) {
            yMin = 1;
            yMax = x;
        }
        return mySqrtRecurseHelp(x,epsilon,yMin,yMax);
    }

    /**
     * If yMitt is within bounds (x+-epsilon) returns yMitt else calculates new bounds and calls the recursive function again.
     * @param x
     * @param epsilon
     * @param yMin
     * @param yMax
     * @return
     */
    private static double mySqrtRecurseHelp(double x, double epsilon,double yMin,double yMax) {
        double yMitt = (yMin+yMax)/2;
        if((yMitt*yMitt > x-epsilon && yMitt*yMitt < x) ||
                (yMitt*yMitt < x+epsilon && yMitt*yMitt > x)){
            return yMitt;
        } else {
            if (yMitt*yMitt > x) {
                return mySqrtRecurseHelp(x,epsilon,yMin,yMitt);
            }else {
                return mySqrtRecurseHelp(x,epsilon,yMitt,yMax);
            }
        }
    }

    /**
     * Loops over the numbers from 1 to five and calculates the square root of the number squared
     * @param args
     */
    public static void main(String[] args) {
        for(int i = 0;i<5;i++) {
            System.out.println("Indata: " + i*i + " svar: " + mySqrtLoop(i*i,EPS) + " Loop");
            System.out.println("Indata: " + i*i + " svar: " + mySqrtRecurse(i*i,EPS) + " Recursive");
        }

    }

}
