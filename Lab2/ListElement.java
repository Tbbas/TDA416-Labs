import java.awt.geom.Point2D;

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

    /**
     * Returns the x and y coordinants as a point.
     * @return
     */
    public Point2D getCoordinatesAsPoint() {
        return new Point2D.Double(x,y);
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() != ListElement.class) return 0;
        return (int) Math.signum(this.value - ((ListElement)o).getValue());
    }
}
