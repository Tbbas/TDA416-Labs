/**
 * Implementation of a set using a splay tree.
 */
public class SplayTreeSet<E> implements SimpleSet {
    class Entry { // A node which has a right and a left child
        Comparable data;
        Entry leftChild;
        Entry rightChild;

        public Entry(Comparable data) {
            this.data = data;
            leftChild = rightChild = null;
        }


    }

    Entry root;
    Entry header; // for splay

    public SplayTreeSet() {
        root = null;
        header = new Entry(null);
    }

    /**
     * Moves the node with the specified value x to the root.
     * @param x
     */
    private void moveToRoot(Comparable x) {
        Entry l,r,t,y;
        l = r = header;
        t = root;
        header.leftChild = header.rightChild = null;
        for(;;) {
            if(x.compareTo(t.data) < 0) {
                if(t.leftChild == null) break;
                r.leftChild = t;
                r = t;
                r = t.leftChild;
            } else if(x.compareTo(t.data) > 0) {
                if(t.rightChild == null) break;
                l.rightChild = t;
                l = t;
                t = t.rightChild;
            } else {
                break;
            }
        }
        l.rightChild = t.leftChild;
        r.leftChild = t.rightChild;
        t.leftChild = header.rightChild;
        t.rightChild = header.leftChild;
        root = t;
    }

    /**
     * Splays the tree on the specified tree, making x (if exists in tree) the root.
     * Otherwise makes the rightmost node becomes the root.
     * @param x
     */
    private void splay(Comparable x) {
        Entry l,r,t,y;
        l = r =  header;
        t = root;
        header.leftChild = header.rightChild = null;
        for(;;) {
            if(x.compareTo(t.data) < 0 ) {
                if(t.leftChild == null) break;
                if(x.compareTo(t.leftChild.data) < 0) {     //Rotate right
                    y = t.leftChild;
                    t.leftChild = y.rightChild;
                    y.rightChild = t;
                    t = y;
                    if(t.leftChild == null) break;
                }
                r.leftChild = t;
                r = t;
                t = t.leftChild;
            } else if(x.compareTo(t.data) > 0) {
                if(t.rightChild == null) break;
                if(x.compareTo(t.rightChild.data) > 0) {    //Rotate left
                    y = t.rightChild;
                    t.rightChild = y.leftChild;
                    y.leftChild = t;
                    t = y;
                    if(t.rightChild == null) break;
                }
                l.rightChild = t;
                l = t;
                t = t.rightChild;
            } else {
                break;
            }
        }
        l.rightChild = t.leftChild;
        r.rightChild = t.rightChild;
        t.leftChild = header.rightChild;
        t.rightChild = header.leftChild;
        root = t;
    }


    @Override
    public int size() {
        return count(root);
    }


    /**
     * Recursive helper method for counting the nodes below e
     * @param e
     */
    private int count(Entry e) {
        if(e.rightChild == null && e.leftChild == null) {
            return 1;
        } else {
            return 1 + count(e.leftChild) + count(e.rightChild);
        }
    }

    @Override
    public boolean add(Comparable x) {
        if(root == null) {
            root = new Entry(x);
            return true;
        }
        if(contains(x)) {
            return false;
        } else {
            Entry current = root;
            while(true) {
                int c = x.compareTo(current.data);
                if(c < 0) {
                    if(current.leftChild == null) {
                        current.leftChild = new Entry(x);
                        break;
                    }
                    else current = current.leftChild;

                } else {
                    if(current.rightChild == null) {
                        current.rightChild = new Entry(x);
                        break;
                    }
                    else current = current.rightChild;
                }
            }
            splay(x);
        }
        return true;


    }

    @Override
    public boolean remove(Comparable x) {
        Entry e;
        splay(x);
        if(root.data.compareTo(x) != 0) return false;

        if(root.leftChild == null) {
            root = root.rightChild;
        } else {
            e = root.rightChild;
            root = root.leftChild;
            splay(x);
            root.rightChild = e;
        }
        return true;
    }

    @Override
    public boolean contains(Comparable x) {
        //Error handling
        if (x == null) {
            throw new NullPointerException();
        }
        if (root.data != null && x.getClass() != root.data.getClass()) {
            throw new ClassCastException();
        }
        if(root == null) {
            return false;
        }
        if (root.leftChild == null && root.rightChild == null) {
            return false;
        }

        splay(x);
        if (x.compareTo(root.data) != 0) {
            return false;
        } else {
            return true;
        }
    }

}
