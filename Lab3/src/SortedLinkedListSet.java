/**
 * The main class for Lab3. A simple linked list (no double linking)
 */
public class SortedLinkedListSet<E> implements SimpleSet {
    public class Entry {
         Comparable data;
        Entry next;
        public Entry(Comparable data, Entry next) {
            this.data = data;
            this.next = next;
        }

    }


    public Entry head;
    private Entry tail;


    public SortedLinkedListSet() {
        this.head = null;
        this.tail = null;
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
        if(head == null){
          head = new Entry(x, tail);
          return true;
        }
        if(head.data != null && x.getClass() != head.data.getClass()) {
            throw new ClassCastException();
        }

        Entry node = head;
        if(!contains(x)) { // adds the element to the last place if it does not exist
            if(x.compareTo(head.data)<0){
                Entry temp = head;
                head = new Entry(x, temp);
                return true;
            }
          while(node != null){
              if(node.next == null) {
                node.next = new Entry(x, null);
                  tail = node.next;
                  return true;
              }else if(x.compareTo(node.next.data)<0){
                Entry temp = node.next;
                  node.next = new Entry(x, temp);
                  return true;
              }
              node = node.next;
          }
          if(tail == null){
            Entry newEntry = new Entry(x,null);
            tail = newEntry;
            head.next = tail;
            return true;
          }else{
            Entry prevTail = this.tail;
            Entry newEntry = new Entry(x,null);
            prevTail.next = newEntry;
              tail = newEntry;
            return true;
          }
        }
        return false;
    }

    @Override
    public boolean remove(Comparable x) {
        //Error handling
        if(x == null) {
            throw new NullPointerException();
        }
        if(head == null) return false;
        if(head.data != null && x.getClass() != head.data.getClass()) {
            throw new ClassCastException();
        }

        boolean flag = contains(x);
        Entry currentEntry = head;
        if(flag && x.compareTo(head.data) == 0){
            if(head.next == null){
                head = null;
                tail = null;
                return true;

            }
            head = head.next;
            return true;
        }
        if(flag) { //removes the element
            while(currentEntry != null) {
                if(x.compareTo(currentEntry.next.data) == 0) {
                    if(currentEntry.next.next == null){
                        currentEntry.next = null;
                        tail = currentEntry;
                        return true;
                    }else{
                        Entry temp = currentEntry.next.next;
                        currentEntry.next.next = null;
                        currentEntry.next = temp;
                        return true;
                    }
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
        if(head == null){
          return false;
        }
        Entry node = head;
        while(node != null) {
          if(x.compareTo(node.data) == 0) {
              return true;
          }
          node = node.next;
        }
        return false;
    }

}
