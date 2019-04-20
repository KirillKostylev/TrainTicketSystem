package model.entity;

import java.util.List;

public class Train {
    public final static int DEFAULT_NUMBER_OF_CARRIAGE = 5;


    private String numberOfTrain;
    private List<Station> stationsInTransit;
    private SeatContainer seatContainers;

    public Train() {
        //freeSeatContainers = new ArrayList<SeatContainer>();
    }

    public List<Station> getStationsInTransit() {
        return stationsInTransit;
    }

    public SeatContainer getSeatContainers() {
        return seatContainers;
    }

    public void setSeatContainers(SeatContainer seatContainers) {
        this.seatContainers = seatContainers;
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
}