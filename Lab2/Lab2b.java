import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lab2b  {
    private static PriorityQueue<ListElement> queue = new PriorityQueue<ListElement>();
    /**
     * The simplyfyshape first calls for a generatelist method wich generates a linkedlist for the x and y coordinates
     * stored in poly, then removes the nodes with the lowest value untill there is only k nodes left.
     * @param poly
     * @param k
     * @return
     */
  public static double[] simplifyShape(double[] poly, int k) {
      System.out.println(poly.length/2 - k + " nodes to be removed");
      System.out.println(poly.length/2 + " nodes");
      DLList list = generateLinkedList(poly);
      int removedNodes = 0;
      while(removedNodes < poly.length/2 - k) {
          ListElement elt = queue.peek();
          DLList.Node node = findNodeByValue(list,elt);
          DLList.Node prev = node.getPrev();
          DLList.Node next = node.getNext();
          list.remove(node);
          queue.remove(elt);
          ((ListElement)prev.elt).setValue(calculateValue(node));
          ((ListElement)next.elt).setValue(calculateValue(node));
          removedNodes++;
      }
      System.out.println("Removed " + removedNodes + " nodes");
      ArrayList<Double> returnList = new ArrayList<Double>();
      DLList.Node current = list.getFirst();
      while(current != null) {
        returnList.add(((ListElement)current.elt).getX());
        returnList.add(((ListElement)current.elt).getY());
        current = current.getNext();
      }
      System.out.println(returnList.size()/2 + " nodes");

      double[] returnArray = new double[returnList.size()];
      for(int i = 0; i<returnList.size();i++) {
        returnArray[i] = returnList.get(i);
      }
      return returnArray;
  }

    /**
     * Returns the node corresponding to the specified Value
     * @return
     */
    private static DLList.Node findNodeByValue(DLList list, ListElement elt) {
        DLList.Node current = list.getFirst();
        while(current.getNext() != null) {
            if(((ListElement)current.elt).equals(elt)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Returns a DLList from a array of coordinates with data equal a array with the two first places being the x and y coordinate and the third place being the value.
     * @param poly
     */
    private static  DLList generateLinkedList(double[] poly) {
      DLList list = new DLList<ListElement>();
      for(int i = 0; i<poly.length-2; i+=2){
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
        DLList.Node node = list.getFirst();
        while(node.next != null){
          if(node != list.getFirst() && node != list.getLast()){
              ((ListElement)node.elt).setValue(calculateValue(node));
              queue.add((ListElement)node.elt);
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
