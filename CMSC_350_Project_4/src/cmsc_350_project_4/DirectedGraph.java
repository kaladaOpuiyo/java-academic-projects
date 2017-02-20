package cmsc_350_project_4;

import java.io.*;
import java.util.*;

/**
 *
 * @author Kalada Opuiyo
 * @param <E>
 */
public class DirectedGraph<E> {

    HashMap<String, Integer> nameIndex = new HashMap<>();
    LinkedList<Integer> adjacencyList[];
    int count = 0;
    String[] dataArray;
    List<String[]> container = new ArrayList<>();

    public void nameIndexCreate(String line) throws FileNotFoundException {

        dataArray = line.split(" ");
        container.add(dataArray);

        for (String className : dataArray) {

            if (!nameIndex.containsKey(className)) {

                nameIndex.put(className, count);

                count++;
            }
        }
    }

    public void createAdjList() {

        adjacencyList = new LinkedList[count];

        for (int i = 0; i < container.size(); i++) {

            adjacencyList[getLocation(i, 0)] = new LinkedList<>();

            for (int j = 1; j < container.get(i).length; j++) {

                adjacencyList[getLocation(i, 0)].add(getLocation(i, j));
            }

            for (int x = 0; x < adjacencyList.length; x++) {

                if (adjacencyList[x] == null) {

                    adjacencyList[x] = new LinkedList<>();
                }

            }
        }

        container.clear();
    }

    private Integer getLocation(int x, int y) {

        return nameIndex.get(container.get(x)[y]);
    }

    public ArrayList<String> sort(String className) {

        Stack<Integer> order = new Stack<>();

        search(order, nameIndex.get(className));

        ArrayList<String> sortList = new ArrayList<>();

        while (!order.empty()) {

            int value = order.pop();

            nameIndex.keySet().stream().filter((key) -> (nameIndex.get(key).equals(value))).forEach((key) -> {
                sortList.add(key);
            });

        }
        return sortList;

    }
     private void search(Stack<Integer> order, int value) {

        adjacencyList[value].stream().forEach((item) -> {
            search(order, item);
        });

        order.add(value);

    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[nameIndex.size()];
        boolean[] present = new boolean[nameIndex.size()];
        for (int i = 0; i < nameIndex.size(); i++) {
            if (!visited[i]) {
                if (cycleCheck(i, visited, present)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean cycleCheck(int start, boolean[] visited, boolean[] present) {
        visited[start] = true;
        present[start] = true;

        for (int next : adjacencyList[start]) {

            if (!visited[start] && cycleCheck(next, visited, present)) {
                return true;
            } else if (present[next]) {
                return true;
            }
        }
        present[start] = false;
        return false;
    }

   

    public void addEdge(String from, String to) {
        adjacencyList[nameIndex.get(from)].add(nameIndex.get(to));
    }

}
