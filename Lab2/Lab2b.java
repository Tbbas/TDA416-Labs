

public class Lab2b  {
    /**
     * The simplyfyshape first calls for a generatelist method wich generates a linkedlist for the x and y coordinates
     * stored in poly, then removes the nodes with the lowest value untill there is only k nodes left.
     * @param poly
     * @param k
     * @return
     */
  public static double[] simplifyShape(double[] poly, int k) {
  }

    /**
     * Returns a DLList from a array of coordinates with data equal a array with the two first places being the x and y coordinate and the third place being the value.
     * @param poly
     */
    private static  DLList generateLinkedList(double[] poly) {
    }


    /**
     * Takes the left node L, the node itself P and the right nodes R coordinates and calculates
     * the value.
     * @return
     */
    private static Double calculateValue(Double[] L, Double[] P, Double[] R) {
        //l-p
        Double LMP = getLength(L,P);
        Double PMR = getLength(P,R);
        Double LMR = getLength(L,R);

        return (LMP+PMR)-LMR;

    }

    /**
     * Returns the length between two sets of coordinates.
     * @param a
     * @param b
     * @return
     */
    private static double getLength(Double[] a, Double[] b){
        return Math.sqrt(Math.pow((a[1] - b[1]), 2) + Math.pow((a[0] - b[0]), 2));
    }
}
