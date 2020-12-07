package fight.connections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlightService {
    private static final String spaceDelimiter = " ";
    int citiesCount;
    int[][] data;
    private final String filePath;

    public FlightService(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Main method to read the source file and print out
     * all of the cheapest connections between respective cities
     *
     * @throws IOException To be handled with try/catch clause
     */
    public void getCheapestWay() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int testCount = Integer.parseInt(reader.readLine()); // assigning the tests count

        for (int i = 0; i < testCount; i++) {
            citiesCount = Integer.parseInt(reader.readLine()); // assigning the cities count
            String[] cities = new String[citiesCount];
            data = new int[citiesCount][citiesCount];

            // Getting cities amount and the number of neighbour cities
            for (int indexOfCity = 1; indexOfCity <= citiesCount; indexOfCity++) {
                String cityName = cities[indexOfCity - 1] = reader.readLine();
                cities[indexOfCity - 1] = cityName;
                int p = Integer.parseInt(reader.readLine());

                // Getting connections and its costs, then parsing
                for (int k = 0; k < p; k++) {
                    String[] cityAndCost = reader.readLine().split(spaceDelimiter);
                    int connectCityIndex = Integer.parseInt(cityAndCost[0]);
                    int cost = Integer.parseInt(cityAndCost[1]);
                    data[indexOfCity - 1][connectCityIndex - 1] = cost;
                }
            }

            // Getting the number of paths to find, departure and arrival cities.
            // Assigning the indexes respectively
            int r = Integer.parseInt(reader.readLine());
            for (int j = 0; j < r; j++) {
                String[] route = reader.readLine().split(spaceDelimiter);
                String departureCity = route[0];
                String arrivalCity = route[1];
                int departureIndex = 0;
                int arrivalIndex = 0;
                for (int l = 0; l < citiesCount; l++) {
                    if (cities[l].equals(departureCity)) {
                        departureIndex = l;
                    }
                    if (cities[l].equals(arrivalCity)) {
                        arrivalIndex = l;
                    }
                }
                System.out.println("The cheapest way from " + cities[departureIndex]
                        + " to " + cities[arrivalIndex] + " is "
                        + dijkstra(data, departureIndex)[arrivalIndex]
                );
            }
        }
    }

    // A utility function to find the vertex with minimum distance value
    // from the set of vertices which are not yet included into 'passed'

    // Implement Dijkstra's algorithm using adjacency matrix representation
    int[] dijkstra(int[][] data, int src) {
        int[] dist = new int[citiesCount]; // dist[i] will hold the shortest distance from src to i

        // 'processed[i]' will be true if vertex 'i' is included in 'processed[]'
        // or shortest distance from src to i is finalized
        Boolean[] processed = new Boolean[citiesCount];

        // Initialize all distances as 'INFINITE' and all 'processed[]' values as 'false'
        for (int i = 0; i < citiesCount; i++) {
            dist[i] = Integer.MAX_VALUE;
            processed[i] = false;
        }

        // Distance of source vertex to itself is always '0'
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < citiesCount - 1; count++) {
            // Getting the minimum distance to the neighbour vertex not yet processed
            int u = minDistance(dist, processed);
            processed[u] = true; // Mark the vertex as processed

            // Update value of the adjacent vertex was picked
            for (int v = 0; v < citiesCount; v++) {

                // Update 'dist[v]' only if it is not in 'processed', there is an
                // edge from 'u' to 'v', and total weight of path from src to
                // 'v' through 'u' is smaller than current value of 'dist[v]'
                if (!processed[v]
                        && data[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + data[u][v] < dist[v]) {
                    dist[v] = dist[u] + data[u][v];
                }
            }
        }
        return dist;
    }

    // Getting minimum distance value of the not yet processed vertex
    int minDistance(int[] dist, Boolean[] processed) {
        // Initialize 'min' value
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < citiesCount; v++) {
            if (!processed[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}
