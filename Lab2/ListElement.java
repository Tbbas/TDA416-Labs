

/**
 * This class is supposed to be used with Lab2 of the TDA416 course, contains a x and y coordinates for a point as well as the value for the point.
 */
public class ListElement implements Comparable {
    private double x,y,value;


    public ListElement(double x, double y, double value) {
        this.x = x;
        this.y = y;
        this.value = value;

    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() != ListElement.class) return 0;

        else {
            return (int) Math.signum(this.value - ((ListElement)o).getValue());
        }


    }
}
