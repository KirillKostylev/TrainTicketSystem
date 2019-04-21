package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Schedule implements Serializable {
    public static final int DEFAULT_SIZE = 3;

    private List<Train> trains;
    private int trainNumber;

    public Schedule() {
        trains = new ArrayList<>();
    }

    public void addNewTrain(int trainNumber, int carriagesNumber, List<Station> listStation) {
        Train train = new Train(trainNumber, carriagesNumber, listStation);
        trains.add(train);
        trainNumber++;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public List<Train> getTrains() {
        return trains;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "trains=" + trains +
                ", trainNumber=" + trainNumber +
                '}';
    }
}
