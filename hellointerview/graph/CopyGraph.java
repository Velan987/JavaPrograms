package hellointerview.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyGraph {
    class IntGraphNode {
        int value;
        IntGraphNode[] neighbors;
    }

    public Map<Integer, List<Integer>> copy_graph(IntGraphNode node) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        if (node != null) {
            dfs(node, adjList);
        }

        return adjList;
    }

    private void dfs(IntGraphNode node, Map<Integer, List<Integer>> adjList) {
        if (adjList.containsKey(node.value)) {
            return;
        }

        List<Integer> neighborValues = new ArrayList<>();
        for (IntGraphNode neighbor : node.neighbors) {
            neighborValues.add(neighbor.value);
        }
        adjList.put(node.value, neighborValues);

        for (IntGraphNode neighbor : node.neighbors) {
            dfs(neighbor, adjList);
        }
    }
}

/**
 * Given a reference to a variable node which is part of an undirected, connected graph, write a function that returns an adjacency list representation of the graph in dictionary form. The keys of the adjacency list are the values of the nodes, and each value is a list of that node's neighbors' values.

This isn't a deep copy of the node objects. You're converting the node-and-pointers structure into a dictionary adjacency list that describes the same graph.

node is an instance of the following class, where neighbors is a list of references to other nodes in the graph (also of type IntGraphNode):

class IntGraphNode:
    def __init__(self, value = 0, neighbors = None):
    self.value = value
    self.neighbors = neighbors if neighbors is not None else []
Example 1:

Input:

node = IntGraphNode(1, [IntGraphNode(2), IntGraphNode(3)])

Output:

>>> copy_graph(node)
{1: [2, 3], 2: [1], 3: [1]}
Example 2: Input:

n1 = IntGraphNode(1)
n2 = IntGraphNode(2)
n3 = IntGraphNode(3)
n4 = IntGraphNode(4)

n1.neighbors = [n2, n4]
n2.neighbors = [n1, n3]
n3.neighbors = [n2, n4]
n4.neighbors = [n1, n3]

Output:

>>> copy_graph(n1)
{1: [2, 4], 2: [1, 3], 3: [2, 4], 4: [1, 3]}
 */