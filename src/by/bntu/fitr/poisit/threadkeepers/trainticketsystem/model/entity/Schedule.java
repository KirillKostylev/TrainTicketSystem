package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Schedule implements Serializable {
    public static final int DEFAULT_SIZE = 3;
    public static final String NULL_SCHEDULE_EXCEPTION = "Null schedule";

    private static final Logger LOG = Logger.getLogger(Schedule.class);

    private ObservableList<Route> routes;
    private static int routesNumber;

    public Schedule() {
        routes = FXCollections.observableArrayList();
        routesNumber = 0;
        LOG.trace("Schedule has been created");
    }


    public void addRoute(List<Station> stations, Train train) {
        routes.add(new Route(stations, train));
        routesNumber++;
        LOG.debug("Rout has been added in schedule");
    }


    public Route getRoute(int index) {
        return routes.get(index);
    }

    //    public void setRoutes(List<Route> routes) {
//        this.routes = routes;
//    }
    public List<Route> getSortedRoutes() {
        //    routes.sort();
        return null;
    }


    public ObservableList<Route> getRoutes() {
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

