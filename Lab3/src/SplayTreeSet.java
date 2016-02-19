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
                ZIG(node);
            } else if((node.typeOfNode == Type.LEFT
                    && node.parent.typeOfNode == Type.LEFT) ||
                    (node.typeOfNode == Type.RIGHT
                            && node.parent.typeOfNode == Type.RIGHT)){ //Zig-Zig
                if((node.typeOfNode == Type.RIGHT
                        && node.parent.typeOfNode == Type.RIGHT)) {
                    ZIGZIG(node,1);
                }
                else {
                    ZIGZIG(node,0);
                }
            } else { //ZIG-ZAG
                if((node.typeOfNode == Type.LEFT
                        && node.parent.typeOfNode == Type.RIGHT)) {
                    ZIGZAG(node,0);
                } else {
                    ZIGZAG(node,0);
                }
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
        if(type == 0) {
            rotateRight(node.parent.parent);
            rotateRight(node.parent);
            rotateRight(node.parent);
            rotateRight(node);
        } else {
            rotateLeft(node.parent.parent);
            rotateLeft(node.parent);
            rotateLeft(node.parent);
            rotateLeft(node);
        }
    }

    /**
     * Performs a rebalance using the ZIG-ZAG-pattern
     * @param node
     * @param type 0 if Left-Right/1 if Right-Left
     */
    private void ZIGZAG(Entry node,int type) {
        if(type == 0) {
            rotateLeft(node);
            rotateLeft(node.parent);
            rotateRight(node);
            rotateRight(node.parent.parent);
        } else {
            rotateRight(node);
            rotateRight(node.parent);
            rotateLeft(node);
            rotateLeft(node.parent.parent);

        }

    }


    /**
     * Performs a right rotation on the entry.
     * @param n
     */
    private void rotateRight(Entry n) {

    }

    /**
     * Performs a left rotation on the entry.
     * @param n
     */
    private void rotateLeft (Entry n) {

    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(Comparable x) {
        //Error handling
        if(x == null) {
            throw new NullPointerException();
        }
        if(root.data != null && x.getClass() != root.data.getClass()) {
            throw new ClassCastException();
        }
        boolean flag = contains(x);
        Entry currentNode = root;
        while(flag) {
            if(x.compareTo(currentNode) < 0) { // left child
                if(currentNode.leftChild == null) {
                    Entry newNode = new Entry(x,currentNode,null,null,Type.LEFT);
                    currentNode.leftChild = newNode;
                    rebalanceTree(newNode);
                    break;
                }else {
                    currentNode = currentNode.leftChild;
                }
            } else if(x.compareTo(currentNode) > 0) { //right child
                if(currentNode.leftChild == null) {
                    Entry newNode = new Entry(x,currentNode,null,null,Type.RIGHT);
                    currentNode.rightChild = newNode;
                    rebalanceTree(newNode);
                    break;
                }else {
                    currentNode = currentNode.rightChild;

                }
            }

            }
        return flag;
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
