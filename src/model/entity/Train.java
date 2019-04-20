package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Train {
    public final static int DEFAULT_NUMBER_OF_CARRIAGE = 5;


    private String numberOfTrain;
    private List<Station> stationsInTransit;
    private List<Seat> freeSeats;
    private double costTicket;

    public Train() {
        freeSeats = new ArrayList<Seat>();
    }

    public List<Station> getStationsInTransit() {
        return stationsInTransit;
    }

    public List<Seat> getFreeSeats() {
        return freeSeats;
    }

    public double getCostTicket() {
        return costTicket;
    }

    public String getNumberOfTrain() {
        return numberOfTrain;
    }

    public void setNumberOfTrain(String numberOfTrain) {
        this.numberOfTrain = numberOfTrain;
    }

    public void setStationsInTransit(List<Station> stationsInTransit) {
        this.stationsInTransit = stationsInTransit;
    }

    public void setCostTicket(double costTicket) {
        this.costTicket = costTicket;
    }
}