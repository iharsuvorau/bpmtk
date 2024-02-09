package layout.internal.di;

public class Bounds {
    public Double x;
    public Double y;
    public Double width;
    public Double height;


    public Bounds(Double x, Double y, Double width, Double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    static public Bounds defaultBounds() {
        return new Bounds(0.0, 0.0, 100.0, 80.0);
    }

    static public Bounds defaultGatewayBounds() {
        return new Bounds(0.0, 0.0, 50.0, 50.0);
    }

    static public Bounds defaultEventBounds() {
        return new Bounds(0.0, 0.0, 36.0, 36.0);
    }

    static public Bounds defaultTaskBounds() {
        return new Bounds(0.0, 0.0, 100.0, 80.0);
    }
}
