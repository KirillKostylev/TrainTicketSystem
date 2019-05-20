package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class DistanceCalculator {
    public static final String URL = "https://geocode-maps.yandex.ru/1.x/?geocode=";
    public static final String COORDINATES_TAG_NAME = "lowerCorner";
    public static final int EARTH_RADIUS = 6371;

//    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
//        System.out.println(findCoordinate("Bobruysk"));
////                distanceCalculate("Helsinki", "Tver"));
//    }

    public static double distanceCalculate(String departureStation, String arriveStation) {

        String coordinate1 = "", coordinate2 = "";
        try {
            coordinate1 = findCoordinate(departureStation);
            coordinate2 = findCoordinate(arriveStation);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return 0;
        }
        String[] latitudeAndLongitude1 = coordinate1.split("\\s");
        String[] latitudeAndLongitude2 = coordinate2.split("\\s");
        double latitude1 = Double.parseDouble(latitudeAndLongitude1[0]);
        double longitude1 = Double.parseDouble(latitudeAndLongitude1[1]);
        double latitude2 = Double.parseDouble(latitudeAndLongitude2[0]);
        double longitude2 = Double.parseDouble(latitudeAndLongitude2[1]);
        double dLatitude = Math.toRadians(latitude2 - latitude1);
        double dLongitude = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2) +
                Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
                        Math.sin(dLongitude / 2) * Math.sin(dLongitude / 2);

        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * b;
    }

    private static String findCoordinate(String cityName) throws ParserConfigurationException, IOException, SAXException {

        URL url = new URL(URL + cityName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new InputSource(url.openStream()));
        Node nl = doc.getDocumentElement().getElementsByTagName(COORDINATES_TAG_NAME).
                item(doc.getDocumentElement().getElementsByTagName(COORDINATES_TAG_NAME).getLength() - 1);

        String coordinates = "";
        String[] str = nl.getTextContent().split("\\s");

        coordinates += str[1] + " " + str[0];
        return coordinates;
    }


}
