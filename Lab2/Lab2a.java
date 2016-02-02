
public class Lab2a {
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
    System.out.println(poly.length);
    return poly;
  }
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

  public static double getLength(double[] a, double[] b){
      return Math.sqrt(Math.pow((a[1] - b[1]), 2) + Math.pow((a[0] - b[0]), 2));
  }
}
