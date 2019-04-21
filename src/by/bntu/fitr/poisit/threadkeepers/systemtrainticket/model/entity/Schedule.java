package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Schedule implements Serializable {
    public static final int DEFAULT_SIZE = 3;

    private List<Train> trains;
    private static int numberOfTrains;

    public Schedule() {
        trains = new ArrayList<>();
        numberOfTrains = 0;
    }

    public void addNewTrain(int trainNumber, int carriagesNumber, List<Station> listStation) {
        Train train = new Train(trainNumber, carriagesNumber, listStation);
        trains.add(train);
        numberOfTrains++;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public int getNumberOfTrains() {
        return numberOfTrains;
    }

    public List<Train> getTrains() {
        return trains;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "trains=" + trains +
                ", numberOfTrains=" + numberOfTrains +
                '}';
    }
}
