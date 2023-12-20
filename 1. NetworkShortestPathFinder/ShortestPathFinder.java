import java.util.*;

public class ShortestPathFinder {
    private AirlineNetwork graph;
    private Path required_path;
    ShortestPathFinder(){}

    ShortestPathFinder(AirlineNetwork graph){
        this.graph=graph;
    }

    // Applying Dijkstra Algorithm
    public Path calculateShortestPath(String source, String target) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, String> previous = new HashMap<>();  // Track the previous node in the shortest path
        Map<String, List<String>> adjacencyList = graph.getGraphRepresentation();
        String[] airports=graph.getAirports();

        // priority queue of nodes (top priority is the one with least distance)
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        priorityQueue.add(source);

        // Initialize distances, visited flags, and previous nodes
        for (String airport : airports) {
            distance.put(airport, Integer.MAX_VALUE);
            visited.put(airport, false);
            previous.put(airport, null);
        }

        // Set distance for the source node to 0
        distance.put(source, 0);

        while (!priorityQueue.isEmpty()) {
            String currentAirport = priorityQueue.poll();

            if (visited.get(currentAirport)) {
                continue;
            }

            visited.put(currentAirport, true);
            List<String> current_neighbours = adjacencyList.get(currentAirport);
            for (String neighbor :current_neighbours) {
                int edgeCost = graph.calculateEdgeCost(currentAirport, neighbor);
                int newDistance = distance.get(currentAirport) + edgeCost;

                if (newDistance < distance.get(neighbor)) {
                    distance.put(neighbor, newDistance);
                    previous.put(neighbor, currentAirport);
                    priorityQueue.add(neighbor);
                }
            }
        }

        if (!visited.get(target))
            return null; // no path between source and target

        // Build the path from source to target
        String path_representation = buildPath(source, target, previous);


        return new Path(path_representation, distance.get(target));
    }

    private String buildPath(String source, String target, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        String current = target;

        while (!current.equals(source)) {
            path.add(current);
            current = previous.get(current);
        }
        path.add(source);
        Collections.reverse(path); // reverse the list
        return String.join(" - ", path);
    }
    static class Path {
        private String path;
        private int path_cost;
        Path(){}

        Path(String path, int cost){
            this.path=path;
            this.path_cost=cost;
        }
        public int getPathCost() {
            return path_cost;
        }

        public String getPath() {
            return path;
        }
    }
}
