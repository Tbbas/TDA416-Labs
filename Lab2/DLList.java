/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
  public class Node {
    /** The contents of the node is public */
    public E elt;

    protected Node prev, next;

    Node() {
      this(null);
    }
    Node(E elt) {
      this.elt = elt;
      prev = next = null;
    }

    public Node getNext() {
      return this.next;
    }

    public Node getPrev() {
      return this.prev;
    }
  }

  /** first and last nodes in list, null when list is empty */
  Node first, last;

  DLList() {
    first = last = null;
  }

  /** inserts an element at the beginning of the list
   * @param e   the new element value
   * @return    the node holding the added element
   */
  public Node addFirst(E e) {
    Node node = new Node(e);
    if(first != null){
      first.prev = node;
      node.next = first;
    }
    if(last == null){
      last = node;
    }
    first = node;
    return node;
  }

  /** inserts an element at then end of the list
   * @param e   the new element
   * @return    the node holding the added element
   */
  public Node addLast(E e) {
      Node node = new Node(e);
      if(last != null){
        last.next = node;
        node.prev = last;
      }
      if(first == null){
        first = node;
      }
      last = node;
    return node;
  }

  /**
   * @return    the node of the list's first element, null if list is empty
   */
  public Node getFirst() {
      return first;
  }

  /**
   * @return    the node of the list's last element, null if list is empty
   */
  public Node getLast() {
      return last;
  }

  /** inserts a new element after a specified node
    * @param e   the new element
    * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertAfter(E e, Node l) {
    Node temp = l.getNext();
    Node newNode = new Node(e);
    l.next = newNode;
    newNode.prev = l;
    newNode.next = temp;
    temp.prev = newNode;
    return newNode;
  }

  /** inserts a new element before a specified node
    * @param e   the new element
    * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertBefore(E e, Node l) {
      Node temp = l.getPrev();
      Node newNode = new Node(e);
      temp.next = newNode;
      newNode.prev = temp;
      l.prev = newNode;
      newNode.next = l;
      return newNode;
  }

  /** removes an element
    * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
    */
  public void remove(Node l) {
      l.getNext().prev = l.getPrev();
      l.getPrev().next = l.getNext();

  }
}
