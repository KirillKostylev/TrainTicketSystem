package model.entity;

import model.logic.WriterData;

import java.util.List;

public class Schedule {
    private List<Train> trains;

    public void addTrain(Train train){

        trains.add(train);
        WriterData.saveData(train);
    }




    public List<Train> getTrains() {
        return trains;
    }
}
