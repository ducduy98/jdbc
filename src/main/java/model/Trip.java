package model;

import annotation.Column;
import annotation.Entity;
import annotation.Id;

import java.time.LocalTime;

@Entity(name = "CHUYENBAY")
public class Trip {
    @Id(value = "MaCb")
    private String id;
    @Column("GaDi")
    private String start;
    @Column("GaDen")
    private String destination;
    @Column("DoDai")
    private long length;
    @Column("GioDi")
    private LocalTime startTime;
    @Column("GioDen")
    private LocalTime destinationTime;
    @Column("ChiPhi")
    private int price;

    public Trip() {
    }

    public Trip(String id, String start, String destination, long length, LocalTime startTime, LocalTime destinationTime, int price) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.length = length;
        this.startTime = startTime;
        this.destinationTime = destinationTime;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(LocalTime destinationTime) {
        this.destinationTime = destinationTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id='" + id + '\'' +
                ", start='" + start + '\'' +
                ", destination='" + destination + '\'' +
                ", length=" + length +
                ", startTime=" + startTime +
                ", destinationTime=" + destinationTime +
                ", price=" + price +
                '}';
    }
}
