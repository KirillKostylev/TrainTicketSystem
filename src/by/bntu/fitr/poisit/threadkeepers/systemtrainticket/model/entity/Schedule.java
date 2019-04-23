package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NonPositiveException;

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

    public void addNewTrain(int trainNumber, int carriagesNumber, List<Station> listStation) throws NonPositiveException {
        if (trainNumber < 0) {
            throw new NonPositiveException("Train" + Train.WRONG_TRAIN_NUMBER);
        }
        if (carriagesNumber < 0 || carriagesNumber > 20){
            throw new NonPositiveException("Carriages" + Train.WRONG_CARRIAGES_AMOUNT);
        }
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
