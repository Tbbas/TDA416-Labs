/**
 * The main class for Lab3. A simple linked list (no double linking)
 */
public class SortedLinkedListSet<E> implements SimpleSet {
    private class Entry<E> {
         E data;
        Entry next;
        public Entry(E data, Entry next) {
            this.data = data;
            this.next = next;
        }

    }


    private Entry<E> head;
    private Entry<E> tail;


    public SortedLinkedListSet() {
        Entry newEntry = new Entry<E>(null,null);
        this.head = newEntry;
        this.tail = newEntry;
    }

    @Override
    public int size() {
       int count = 0;
        Entry current = head;
        while(current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean add(Comparable x) {
        //Error handling
        if(x == null) {
            throw new NullPointerException();
        }
        if(head.data != null && x.getClass() != head.data.getClass()) {
            throw new ClassCastException();
        }

        boolean flag = contains(x);

        if(!flag) { // adds the element to the last place if it does not exist
            Entry prevTail = this.tail;
            Entry newEntry = new Entry(x,null);
            prevTail.next = newEntry;
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean remove(Comparable x) {
        //Error handling
        if(x == null) {
            throw new NullPointerException();
        }
        if(head.data != null && x.getClass() != head.data.getClass()) {
            throw new ClassCastException();
        }

        boolean flag = contains(x);
        Entry currentEntry = head;

        if(flag) { //removes the element
            while(currentEntry != null) {
                if(currentEntry.next.data.equals(x)) {
                    Entry elementWithData = currentEntry.next;
                    currentEntry.next = elementWithData.next;
                    elementWithData.data = null;
                    elementWithData.next = null;
                    break;
                }
                currentEntry = currentEntry.next;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean contains(Comparable x) {
        boolean flag = false;
        while(head.next != null && !flag) {
            if(head.data.equals(x)) {
                flag = true;
            }
        }
        return flag;
    }

}
