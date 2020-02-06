package model;

public class Plane {
    @Override
    public String toString() {
        return "model.Plane{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", distance=" + distance +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    private long id;
    private String type;
    private long distance;


}
