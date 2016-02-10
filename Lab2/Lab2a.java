/**
 * The first implementation of Lab2, using multiple loops.
 */
public class Lab2a {

  /**
   * Loops untill there are k points left. For each loop-iteration, removes a point, and recalculate
   * the value for the remaining points.
   * @param poly
   * @param k
   * @return
     */
  public static double[] simplifyShape(double[] poly, int k)
  {
    while(poly.length > k*2){
      int index=2;
      double shortestLength=100;
      for(int i=2;i<poly.length-2;i+=2){
        double[] p = {poly[i], poly[i+1]};
        double[] l = {poly[i-2], poly[i-1]};
        double[] r = {poly[i+2], poly[i+3]};
        double l1 = getLength(p,l);
        double l2 = getLength(p,r);
        double l3 = getLength(l,r);
        if((l1 + l2 - l3) < shortestLength){
          index = i;
          shortestLength = ((l1+l2)-l3);
        }
      }
      poly = removeElement(poly, index+1);
      poly = removeElement(poly, index);
    }
    System.out.println(poly.length/2 + " nodes");
    return poly;
  }

  /**
   * removes the x and y coordinates from arr at index i and i+1, returns the new list.
   * @param arr
   * @param index
   * @return
     */
  public static double[] removeElement(double[] arr, int index){
    double[] ret = new double[arr.length - 1];
    for ( int i = 0; i < arr.length ; i++ )
    {
      if(i < index){
        ret[i] = arr[i];
      }else if(i > index){
        ret[i-1] = arr[i];
      }
    }
    return ret;
  }

  /**
   * returns the length between two sets of coordinants.
   * @param a
   * @param b
   * @return
     */
  public static double getLength(double[] a, double[] b){
      return Math.sqrt(Math.pow((a[1] - b[1]), 2) + Math.pow((a[0] - b[0]), 2));
  }
}
