/**
 * Implementation of a set using a splay tree.
 */
public class SplayTreeSet<E> implements SimpleSet {
    private class Entry<E> { // A node which has a right and a left child
        E data;
        Entry leftChild;
        Entry rightChild;
        Entry parent;
        Type typeOfNode;

        public Entry(E data,Entry parent,Entry leftC, Entry rightC,Type type) {
            this.data = data;
            this.parent = parent;
            this.leftChild = leftC;
            this.rightChild = rightC;
            typeOfNode = type;
        }


    }

    Entry root;

    /**
     * An enum to keep track of the type of a node.
     */
    private enum Type{
        RIGHT,LEFT,ROOT
    }


    public SplayTreeSet() {
        root = new Entry(null,null,null,null,Type.ROOT);
    }

    /**
     * Rebalances the tree untill the specified node is root
     * @param node
     */
    private void rebalanceTree(Entry node) {
        while(node.typeOfNode != Type.ROOT) {

            if(node.parent.typeOfNode == Type.ROOT) { //ZIG

            } else if((node.parent.typeOfNode == Type.LEFT
                    && node.parent.parent.typeOfNode == Type.LEFT) ||
                    (node.parent.typeOfNode == Type.RIGHT
                            && node.parent.parent.typeOfNode == Type.RIGHT)){ //Zig-Zig



            }


        }

    }

    /**
     * Performs a rebalance using the ZIG-pattern
     * @param node
     */
    private void ZIG(Entry node) {

    }

    /**
     * Performs a rebalance using the ZIG-ZIG-pattern
     * @param node
     * @param type 0 if Left-left/1 if Right-Right
     */
    private void ZIGZIG(Entry node,int type) {

    }

    /**
     * Performs a rebalance using the ZIG-ZAG-pattern
     * @param node
     * @param type 0 if Left-Right/1 if Right-Left
     */
    private void ZIGZAG(Entry node,int type) {

    }



    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(Comparable x) {
        return false;
    }

    @Override
    public boolean remove(Comparable x) {
        return false;
    }

    @Override
    public boolean contains(Comparable x) {
        return false;
    }
}
