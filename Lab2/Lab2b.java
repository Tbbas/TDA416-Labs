import java.awt.geom.Point2D;

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
      DLList list = new DLList<ListElement>();
      for(i = 0; i<poly.length-2; i+=2){
        ListElement element = new ListElement(poly[i], poly[i+1], 0.0);
        if(i == 0){
          list.addFirst(element);
        }else {
          list.addLast(element);
        }
      }
      calculateWholeListValues(list);
      return list;
    }

    private static void calculateWholeListValues(DLList list){
        Node node = list.getFirst();
        while(node.next != null){
          if(node != list.getFirst() && node != list.getLast()){
            node.value = calculateValue(node)
          }
          node = node.next;
        }
    }

    /**
     * Takes the left node L, the node itself P and the right nodes R coordinates and calculates
     * the value.
     * @return
     */
    private static Double calculateValue(DLList.Node node) {
        Double LMP = getLength(((ListElement)node.getPrev().elt).getCoordinatesAsPoint(),((ListElement)node.elt).getCoordinatesAsPoint());
        Double PMR = getLength(((ListElement)node.elt).getCoordinatesAsPoint(),((ListElement)node.getNext().elt).getCoordinatesAsPoint());
        Double LMR = getLength(((ListElement)node.getPrev().elt).getCoordinatesAsPoint(),((ListElement)node.getNext().elt).getCoordinatesAsPoint());
        return (LMP+PMR)-LMR;

    }

    /**
     * Returns the length between two points.
     * @param a
     * @param b
     * @return
     */
    private static double getLength(Point2D a, Point2D b){
        return Math.sqrt(Math.pow(a.getY()-b.getY(), 2) + Math.pow(a.getX()-b.getX(), 2));
    }
}
