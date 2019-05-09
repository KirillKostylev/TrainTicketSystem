package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Schedule implements Serializable {
    public static final int DEFAULT_SIZE = 3;
    public static final String NULL_SCHEDULE_EXCEPTION = "Null schedule";

    private List<Route> routes;
    private static int routesNumber;

    public Schedule() {
        routes = new LinkedList<>();
        routesNumber = 0;
    }

//    public void addRoute(List<Station> stations, int trainNumber, int carriagesNumber, int seatsNumberInCarriage)
//            throws NonPositiveException {
//        if (trainNumber < 1) {
//            throw new NonPositiveException("Train" + Train.WRONG_TRAIN_NUMBER);
//        }
//        if (carriagesNumber < 0 || carriagesNumber > 20) {
//            throw new NonPositiveException("Carriages" + Train.WRONG_CARRIAGES_AMOUNT);
//        }
//        Train train = new Train(trainNumber, carriagesNumber, seatsNumberInCarriage);
//        routes.add(new Route(stations, train));
//        routesNumber++;
//    }


    public void addRoute(List<Station> stations, Train train){
        routes.add(new Route(stations, train));
        routesNumber++;
    }

    public Route getRoute(int index){
        return routes.get(index);
    }
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    public List<Route> getSortedRoutes(){
    //    routes.sort();
        return null;
    }


    public List<Route> getRoutes(){
        return routes;
    }

    public int getRoutesNumber() {
        return routesNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(routes, schedule.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }

}

