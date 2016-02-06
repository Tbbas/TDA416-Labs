

public class Lab2b  {
    /**
     * The simplyfyshape first calls for a generatelist method wich generates a linkedlist for the x and y coordinates
     * stored in poly, then removes the nodes with the lowest value untill there is only k nodes left.
     * @param poly
     * @param k
     * @return
     */
  public static double[] simplifyShape(double[] poly, int k) {
      int removedNodes = 0;
      DLList<Double> nodeList = generateLinkedList(poly);

      while (removedNodes < ((poly.length/2) - k)) {

          
      }
      return poly;
  }

    /**
     * Returns a DLList from a array of coordinates with data equal a double[] containing the x and y coordinate
     * @param poly
     */
    private static  DLList generateLinkedList(double[] poly) {
        DLList<Double[]> list = new DLList<Double[]>();
        DLList.Node current = null;
        Double[] currentCoordinates;

        //Generate Points
        for(int i = 0; i<poly.length;i+=2) {
            currentCoordinates = new Double[]{poly[i],poly[i+1]};
            if(list.getFirst() == null) {
                list.addFirst(currentCoordinates);
                current = list.getFirst();
            } else {
                current = list.insertAfter(currentCoordinates,current);

            }
        }


    }


    /**
     * Returns the value for the Node
     * @return
     */
    private static Double calculateValue(DLList.Node n) {
        //l-p
        Double LMP = getLength((Double[])n.prev.elt,(Double[])n.elt);
        Double PMR = getLength((Double[])n.elt,(Double[])n.next.elt);
        Double LMR = getLength((Double[])n.prev.elt,(Double[])n.next.elt);

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
