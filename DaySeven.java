import java.util.Iterator;
import java.util.LinkedList;

enum Bags {
    DARK_ORANGE(0), LIGHT_RED(1), BRIGHT_WHITE(2), MUTED_YELLOW(3), SHINY_GOLD(4),
    DARK_OLIVE(5), VIBRANT_PLUM(6), FADED_BLUE(7), DOTTED_BLACK(8);

    private final int value;
    private Bags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class DaySeven {
    static int dfsCount;
    private int V;
    /* Array  of lists for Adjacency List Representation */
    private LinkedList<Integer> adj[];

    // Constructor
    @SuppressWarnings("unchecked") DaySeven(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to v's list.
    }

    // A function used by DFS
    int DFSUtil(int v, boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.println(v);
        dfsCount++;

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
        return dfsCount;
    }

    /* The function to do DFS traversal.
     It uses recursive DFSUtil() */
    int DFS(int v)
    {
        // Mark all the vertices as
        // not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];

        // Call the recursive helper
        // function to print DFS
        // traversal
        return DFSUtil(v, visited);
    }

    public static void main(String args[])
    {
        DaySeven g = new DaySeven(9);

        /* construct the graph*/
        g.addEdge(Bags.DARK_ORANGE.getValue(), Bags.BRIGHT_WHITE.getValue());
        g.addEdge(Bags.DARK_ORANGE.getValue(), Bags.MUTED_YELLOW.getValue());
        g.addEdge(Bags.LIGHT_RED.getValue(), Bags.BRIGHT_WHITE.getValue());
        g.addEdge(Bags.LIGHT_RED.getValue(), Bags.MUTED_YELLOW.getValue());
        g.addEdge(Bags.BRIGHT_WHITE.getValue(), Bags.SHINY_GOLD.getValue());
        g.addEdge(Bags.MUTED_YELLOW.getValue(), Bags.SHINY_GOLD.getValue());
        g.addEdge(Bags.SHINY_GOLD.getValue(), Bags.DARK_OLIVE.getValue());
        g.addEdge(Bags.SHINY_GOLD.getValue(), Bags.VIBRANT_PLUM.getValue());
        g.addEdge(Bags.DARK_OLIVE.getValue(), Bags.FADED_BLUE.getValue());
        g.addEdge(Bags.DARK_OLIVE.getValue(), Bags.DOTTED_BLACK.getValue());
        g.addEdge(Bags.VIBRANT_PLUM.getValue(), Bags.FADED_BLUE.getValue());
        g.addEdge(Bags.VIBRANT_PLUM.getValue(), Bags.DOTTED_BLACK.getValue());

        System.out.println(
                "Following is Depth First Traversal "
                        + "(starting from vertex shiny gold)");

        int count = 9 - g.DFS(4);

        System.out.println("There are " + count + " bag colors can eventually contain at least one shiny" +
                " gold bag");
    }
}
