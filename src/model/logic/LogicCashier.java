package model.logic;

//import by.bntu.fitr.poisit.threadkeepers.model.logic.domain.*;

public class LogicCashier {
//    public List<Train> findTrain(Schedule trains, Customer customer) {
//        Train[] listOfTrains = trains.getTrains();
//        ArrayList<Train> suitableTrains = new ArrayList<>();
//        if (listOfTrains.length > 0) {
//            for (Train train : listOfTrains) {
//                int indexOfDepartureStation = train.getStationsInTransit().indexOf(customer.getDesiredDepartureStation());
//                int indexOfArriveStation = train.getStationsInTransit().indexOf(customer.getDesiredArriveStation());
//                if (indexOfDepartureStation >= 0 && indexOfArriveStation > indexOfDepartureStation) {
//                    suitableTrains.add(train);
//                }
//            }
//        }
//        return suitableTrains;
//    }
//
//    public Ticket sellTicket(Train train, Customer customer) {
//        Ticket ticket = null;
//        if (train.getFreeSeats().size() <= Seat.DEFAULT_NUMBER_OF_SEATS_IN_CARRIAGE * Train.DEFAULT_NUMBER_OF_CARRIAGE
//                && customer.getAmountOfMoney() >= train.getCostTicket()) {
//            Seat seat = new Seat();
//            ticket = new Ticket(seat);
//            customer.setAmountOfMoney(customer.getAmountOfMoney() - train.getCostTicket());
//            customer.getTickets().add(ticket);
//        }
//        return ticket;
//    }
}