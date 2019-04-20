package model.entity;

import java.util.List;

public class Customer {
    private double amountOfMoney;
    private String desiredDepartureDate;
    private String desiredArriveDate;
    private Station desiredDepartureStation;
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Station getDesiredDepartureStation() {
        return desiredDepartureStation;
    }

    public Station getDesiredArriveStation() {
        return desiredArriveStation;
    }

    private Station desiredArriveStation;

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public String getDesiredDepurtureDate() {
        return desiredDepartureDate;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getDesiredDepartureDate() {
        return desiredDepartureDate;
    }

    public void setDesiredDepartureDate(String desiredDepartureDate) {
        this.desiredDepartureDate = desiredDepartureDate;
    }

    public void setDesiredArriveDate(String desiredArriveDate) {
        this.desiredArriveDate = desiredArriveDate;
    }

    public void setDesiredDepartureStation(Station desiredDepartureStation) {
        this.desiredDepartureStation = desiredDepartureStation;
    }

    public void setDesiredArriveStation(Station desiredArriveStation) {
        this.desiredArriveStation = desiredArriveStation;
    }

    public String getDesiredArriveDate() {
        return desiredArriveDate;
    }
}
