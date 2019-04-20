package model.entity;

public class Ticket {
    private Train train;
    private Seat seat;
    private Customer customer;

    public Ticket(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return train.getNumberOfTrain() + " - " + customer.getDesiredDepartureDate() + " - "
                + customer.getDesiredArriveDate() + " - " + customer.getDesiredDepartureStation() + " - "
                + customer.getDesiredArriveStation() + " - " + seat.toString();
    }
}
