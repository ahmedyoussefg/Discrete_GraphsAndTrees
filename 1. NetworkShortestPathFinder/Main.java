import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter list of airports: ");
        String[] airports = in.nextLine().replaceAll(" ", "").split(",");
        System.out.print("Enter the flights: ");
        String[] flights = in.nextLine().replaceAll(" ", "").split(",");

        System.out.println("The distance for each flight (in miles)");
        Map<String, Integer> cost_map = new HashMap<>();
        for (String flight : flights) {
            System.out.print(flight + ": ");
            int cost = in.nextInt();
            cost_map.put(flight, cost);
        }
        AirlineNetwork network = new AirlineNetwork(airports, flights, cost_map);
        System.out.print("Enter source airport: ");
        String source=in.next();
        System.out.print("Enter destination airport: ");
        String target=in.next();
        ShortestPathFinder dijkstra = new ShortestPathFinder(network);
        ShortestPathFinder.Path path = dijkstra.calculateShortestPath(source, target);
        if (path==null){
            System.out.println("No path between " +source +" and " +target);
            exit(0);
        }
        System.out.println("Shortest path from " + source + " to " + target + ": " + path.getPath());
        System.out.println("Total distance: " + path.getPathCost() + " miles");
    }
}
