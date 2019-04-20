package model.entity;

public class Seat {
    public static final int DEFAULT_NUMBER_OF_SEATS_IN_CARRIAGE = 30;

    public static int counterOfSeat = 0;
    public static int counterOfCarriage = 0;


    public Seat() {
        counterOfSeat++;
        if (counterOfSeat > DEFAULT_NUMBER_OF_SEATS_IN_CARRIAGE){
            counterOfSeat = 0;
            counterOfCarriage++;
        }
    }

    @Override
    public String toString() {
        return counterOfCarriage + " _ " + counterOfSeat;
    }

}
