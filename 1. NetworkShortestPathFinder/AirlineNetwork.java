import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirlineNetwork {
    private String[] airports;

    private String[] flights;
    private Map<String, List<String>> adjacencyList;
    private Map<String, Integer> cost_map;
    AirlineNetwork(){}

    AirlineNetwork(String[] airports, String[] flights, Map<String, Integer>cost_map){
        this.adjacencyList = new HashMap<>();
        this.airports=airports;
        this.flights=flights;
        this.cost_map=cost_map;
        // Initialize the adjacency list
        for (String airport : airports) {
            this.adjacencyList.put(airport, new ArrayList<>());
        }

        // Add edges to the graph
        for (String flight : flights) {
            String[] nodes = flight.split("-");
            String to = nodes[0];
            String from = nodes[1];
            this.adjacencyList.get(to).add(from);
            this.adjacencyList.get(from).add(to);
        }
    }
    Map<String, List<String>> getGraphRepresentation() {
        return this.adjacencyList;
    }
    Integer calculateEdgeCost(String source, String destination){
        String firstPath=source+"-"+destination;
        String secondPath=destination+"-"+source;
        if (cost_map.containsKey(firstPath)) {
            return cost_map.get(firstPath);
        }
        else if (cost_map.containsKey(secondPath)) {
            return cost_map.get(secondPath);
        }
        // should never return -1
        return -1;
    }

    public String[] getAirports() {
        return airports;
    }

    public void setAirports(String[] airports) {
        this.airports = airports;
    }

    public String[] getFlights() {
        return flights;
    }

    public void setFlights(String[] flights) {
        this.flights = flights;
    }
    boolean airportExists(String airport) {
        for (String one : airports) {
            if (one.equals(airport)){
                return true;
            }
        }
        return false;
    }

}
