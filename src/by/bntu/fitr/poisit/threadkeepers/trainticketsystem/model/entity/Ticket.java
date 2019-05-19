package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import java.util.Objects;

public class Ticket {
    private int trainNumber;
    private String departureStation;
    private String arriveStation;
    private String departureTime;
    private String arriveTime;
    private int seatNumber;
    private int carriageNumber;
    private double cost;

    public Ticket(int trainNumber, String departureStation, String arriveStation, String departureTime,
                  String arriveTime, int carriageNumber, int seatNumber, double cost) {
        this.trainNumber = trainNumber;
        this.departureStation = departureStation;
        this.arriveStation = arriveStation;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.seatNumber = seatNumber;
        this.carriageNumber = carriageNumber;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "trainNumber=" + trainNumber +
                ", departureStation='" + departureStation + '\'' +
                ", arriveStation='" + arriveStation + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", seatNumber=" + seatNumber +
                ", carriageNumber=" + carriageNumber +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return trainNumber == ticket.trainNumber &&
                seatNumber == ticket.seatNumber &&
                carriageNumber == ticket.carriageNumber &&
                Double.compare(ticket.cost, cost) == 0 &&
                Objects.equals(departureStation, ticket.departureStation) &&
                Objects.equals(arriveStation, ticket.arriveStation) &&
                Objects.equals(departureTime, ticket.departureTime) &&
                Objects.equals(arriveTime, ticket.arriveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, departureStation, arriveStation, departureTime, arriveTime, seatNumber, carriageNumber, cost);
    }
}
