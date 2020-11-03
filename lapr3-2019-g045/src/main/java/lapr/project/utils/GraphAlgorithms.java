/*
* A collection of graph algorithms.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class GraphAlgorithms {

	private static final double INF = Double.MAX_VALUE;

	/**
	 * Performs breadth-first search of a Graph starting in a Vertex
	 *
	 * @param g Graph instance
	 * @return qbfs a queue with the vertices of breadth-first search
	 */
	public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {

		if (!g.validVertex(vert)) {
			return null;
		}

		LinkedList<V> visitados = new LinkedList<>();
		LinkedList<V> lista = new LinkedList<>();
		LinkedList<V> bfs = new LinkedList<>();

		lista.add(vert);
		visitados.add(vert);
		bfs.add(vert);

		while (!lista.isEmpty()) {
			V vertice = lista.poll();
			for (V adjVert : g.adjVertices(vertice)) {
				if (!visitados.contains(adjVert)) {
					bfs.add(adjVert);
					lista.add(adjVert);
					visitados.add(adjVert);
				}
			}
		}

		return bfs;
	}

	/**
	 * Performs depth-first search starting in a Vertex
	 *
	 * @param g Graph instance
	 * @param vOrig Vertex of graph g that will be the source of the search
	 * @param visited set of discovered vertices
	 * @param qdfs queue with vertices of depth-first search
	 */
	private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
		qdfs.add(vOrig);
		visited[g.getKey(vOrig)] = true;

		for (V vAdj : g.adjVertices(vOrig)) {
			if (!visited[g.getKey(vAdj)]) {
				DepthFirstSearch(g, vAdj, visited, qdfs);
			}
		}
	}

	/**
	 * @param vert
	 * @param g Graph instance
	 * @return qdfs a queue with the vertices of depth-first search
	 */
	public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {

		if (!g.validVertex(vert)) {
			return null;
		}

		int n = g.numVertices();
		boolean[] visited = new boolean[n];

		LinkedList<V> qdfs = new LinkedList<>();
		DepthFirstSearch(g, vert, visited, qdfs);

		return qdfs;
	}

	public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

		ArrayList<LinkedList<V>> paths = new ArrayList<>();

		if (!g.validVertex(vOrig)) {
			return paths;
		}

		if (!g.validVertex(vDest)) {
			return paths;
		}

		LinkedList<V> path = new LinkedList<>();
		LinkedList<LinkedList<V>> queue = new LinkedList<>();
		queue.add(path);
		path.add(vOrig);
		while (!queue.isEmpty()) {
			path = queue.pop();
			if (path.getLast().equals(vDest)) {
				paths.add(path);
			}
			for (Edge<V, E> e : g.outgoingEdges(path.getLast())) {
				V v = e.getVDest();
				if (!path.contains(v)) {
					LinkedList<V> newPath = new LinkedList<>(path);
					newPath.add(v);
					queue.add(newPath);
				}
			}
		}
		return paths;

	}

	/**
	 * Computes shortest-path distance from a source vertex to all reachable
	 * vertices of a graph g with nonnegative edge weights This implementation
	 * uses Dijkstra's algorithm
	 *
	 * @param g Graph instance
	 * @param vOrig Vertex that will be the source of the path
	 * @param vertices
	 * @param visited set of discovered vertices
	 * @param dist minimum distances
	 * @param pathKeys
	 */
	protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices,
		boolean[] visited, int[] pathKeys, double[] dist) {

		dist[g.getKey(vOrig)] = 0;
		while (vOrig != null) {
			visited[g.getKey(vOrig)] = true;
			for (V vAdj : g.adjVertices(vOrig)) {
				if (!visited[g.getKey(vAdj)] && dist[g.getKey(vAdj)] > (dist[g.getKey(vOrig)] + g.getEdge(vOrig, vAdj).getWeight())) {
					dist[g.getKey(vAdj)] = dist[g.getKey(vOrig)] + g.getEdge(vOrig, vAdj).getWeight();
					pathKeys[g.getKey(vAdj)] = g.getKey(vOrig);
				}
			}
			int vOrigKey = getVertMinDist(dist, visited);
			vOrig = null;
			for (V v : g.vertices()) {
				if (vOrigKey == g.getKey(v)) {
					vOrig = v;
				}
			}
		}
	}

	protected static int getVertMinDist(double[] dist, boolean[] visited) {
		double minDist = Double.MAX_VALUE;
		int vertMinDistKey = -1;
		for (int i = 0; i < dist.length; i++) {
			if (!visited[i] && dist[i] < minDist) {
				vertMinDistKey = i;
				minDist = dist[i];
			}
		}
		return vertMinDistKey;
	}

	/**
	 * Extracts from pathKeys the minimum path between voInf and vdInf The path
	 * is constructed from the end to the beginning
	 *
	 * @param g Graph instance
	 * @param vOrig
	 * @param vDest
	 * @param path stack with the minimum path (correct order)
	 * @param pathKeys
	 * @param verts
	 */
	protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
		V vert = vDest;
		path.push(vDest);
		while (!vert.equals(vOrig)) {
			int vertKey = pathKeys[g.getKey(vert)];
			for (V v : g.vertices()) {
				if (g.getKey(v) == vertKey) {
					vert = v;
				}
			}
			path.push(vert);
		}
	}

	//shortest-path between vOrig and vDest
	public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
		if (!g.validVertex(vDest) || !g.validVertex(vOrig)) {
			return 0;
		}
		shortPath.clear();
		ArrayList<LinkedList<V>> paths = new ArrayList<>();
		ArrayList<Double> dists = new ArrayList<>();

		shortestPaths(g, vOrig, paths, dists);

		shortPath.addAll(paths.get(g.getKey(vDest)));
		if (dists.get(g.getKey(vDest)) != Double.MAX_VALUE) {
			return dists.get(g.getKey(vDest));
		} else {
			return 0;
		}
	}

	//shortest-path between voInf and all other
	public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

		if (!g.validVertex(vOrig)) {
			return false;
		}

		int nverts = g.numVertices();
		boolean[] visited = new boolean[nverts]; //default value: false
		int[] pathKeys = new int[nverts];
		double[] dist = new double[nverts];
		V[] vertices = g.allkeyVerts();

		for (int i = 0; i < nverts; i++) {
			dist[i] = Double.MAX_VALUE;
			pathKeys[i] = -1;
		}

		shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

		dists.clear();
		paths.clear();
		for (int i = 0; i < nverts; i++) {
			paths.add(null);
			dists.add(null);
		}
		for (int i = 0; i < nverts; i++) {
			LinkedList<V> shortPath = new LinkedList<>();
			if (dist[i] != Double.MAX_VALUE) {
				getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
			}
			paths.set(i, shortPath);
			dists.set(i, dist[i]);
		}
		return true;
	}

	/**
	 * Reverses the path
	 *
	 * @param path stack with path
	 */
	private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

		LinkedList<V> pathcopy = new LinkedList<>(path);
		LinkedList<V> pathrev = new LinkedList<>();

		while (!pathcopy.isEmpty()) {
			pathrev.push(pathcopy.pop());
		}

		return pathrev;
	}

	/**
	 * Algorithm that returns takes returns a map whose values are paths (linked
	 * lists) going from an origin to a destination passing through a set of
	 * vertices in between.The map is naturally ordered by it's key which is the
	 * total distance of the path.We can set a max number of results by the
	 * maxNumResults
	 *
	 * @param <V>
	 * @param <E>
	 * @param g Graph
	 * @param vOrig Origin vertex
	 * @param vDest Destiny vertex
	 * @param nosIntermedios Intermediate nodes
	 * @param maxNumResults
	 * @return Minimum distance between origin - destiny passing thorugh the
	 * intermediate nodes
	 */
	public static <V, E> List<LinkedList<V>> pathsWithConstraints(Graph<V, E> g, V vOrig, V vDest,
		List<V> nosIntermedios, int maxNumResults) {

		// pulls all possible permutations of the intermediate nodes
		List<LinkedList<V>> possiblePaths = permutations(nosIntermedios);

		// adds the origin and destination to the possible paths
		for (LinkedList<V> path : possiblePaths) {
			path.addFirst(vOrig);
			path.addLast(vDest);
		}
//		System.out.println("possible paths: " + possiblePaths.toString());
		// use the welsh powell algorithm for getting the transitive closure
		// (a map whose key are the V origin and V dest and the value is the 
		// minimum distance between these nodes
		Graph<V, E> transitiveClosure = floydWarshall(g);

		// constructs a tree to order the possiblePaths by their total distance
		// using the transitiveClosure
		SortedSet<LinkedList<V>> sortedPossiblePaths = new TreeSet<>(new Comparator<LinkedList<V>>() {
			@Override
			public int compare(LinkedList<V> o1, LinkedList<V> o2) {
				double dist1 = 0.0;
				double dist2 = 0.0;
				for (int i = 0; i < o1.size() - 1; i++) {
					dist1 = dist1 + transitiveClosure.getEdge(o1.get(i), o1.get(i + 1)).getWeight();
					dist2 = dist2 + transitiveClosure.getEdge(o2.get(i), o2.get(i + 1)).getWeight();
				}
				return Double.compare(dist1, dist2);
			}
		});
		

//		System.out.println("\nTRANSITIVE CLOSURE:\n");
//		g.vertices().forEach(vi -> {
//			g.vertices().forEach(vj -> {
//				System.out.print(vi + " -> " + vj + ": ");
//				int weigth = (int) transitiveClosure.getEdge(vi, vj).getWeight();
//				if (Double.compare(weigth, INF) == 0) {
//					System.out.println("INF");
//				} else {
//					System.out.println(weigth);
//				}
//			});
//		});

		// before adding the possible paths to the sortedPossiblePaths tree, we
		// need to make sure this path is REALLY possible, meaning the transitiveClosure
		// value for any two adjacent elements of the path is not "INFINITE". We will add
		// the really possible paths to another list
		List<LinkedList<V>> filteredPossiblePaths = new ArrayList<>();
		for (LinkedList<V> path : possiblePaths) {
//			System.out.println("testing path: " + path);
			boolean doNotAdd = false;
			for (int i = 0; i < path.size() - 1; i++) {
				V v1 = path.get(i);
				V v2 = path.get(i + 1);
				Double weigth = transitiveClosure.getEdge(v1, v2).getWeight();
				if (Double.compare(weigth, INF) == 0) {
					doNotAdd = true;
					break;
				}
			}
			if (!doNotAdd) {
//				System.out.println("The path is ok. adding to paths");
				filteredPossiblePaths.add(path);
			}
		}
//		System.out.println("filtered paths:" + filteredPossiblePaths.toString());
		// adds the paths that can really be achieved to the ordering tree
		filteredPossiblePaths.forEach(p -> {
			sortedPossiblePaths.add(p);
		});

		// expands the paths to get all the vertices they go through and add
		// to the return list up to a maximum number;
		List<LinkedList<V>> fullPaths = new ArrayList<>();
		Iterator<LinkedList<V>> it = sortedPossiblePaths.iterator();

		int counter = 0;
		while (it.hasNext()) {
			LinkedList<V> list = it.next();
			LinkedList<V> fullPath = new LinkedList<>();
			for (int i = 0; i < list.size() - 1; i++) {
				LinkedList<V> partialPath = new LinkedList<>();
				shortestPath(g, list.get(i), list.get(i + 1), partialPath);
				if (i != 0) {
					partialPath.removeFirst();
				}
				fullPath.addAll(partialPath);
			}
			fullPaths.add(fullPath);
			counter++;
			if (counter == maxNumResults) {
				break;
			}
		}
		return fullPaths;
	}

	/**
	 * *
	 * Calls the heap's algoritm for generating all permutations for a given
	 * list of elements;
	 *
	 * @param <V>
	 * @param list
	 * @return
	 */
	public static <V> List<LinkedList<V>> permutations(List<V> list) {
		List<LinkedList<V>> perms = new ArrayList<>();
		permutations(list, list.size(), perms);
		return perms;
	}

	//Heap's Algorithm
	/**
	 * *
	 * Heap's algorithm for creating permutations for a given list.
	 *
	 * @param <V>
	 * @param list
	 * @param n
	 * @param perms
	 */
	public static <V> void permutations(List<V> list, int n, List<LinkedList<V>> perms) {
		if (n == 1) {
			perms.add(new LinkedList<>(list));
		} else {
			for (int i = 0; i < n; i++) {
				permutations(list, n - 1, perms);
				int j = (n % 2 == 0) ? i : 0;
				V t = list.get(n - 1);
				list.set(n - 1, list.get(j));
				list.set(j, t);
			}
		}
	}

	/**
	 * Builds the transitive closure in the form of a Graph whose distance from
	 * a node to another is the distance of the shortest path between them. If
	 * there is no path from a node to another, an edge with very high value is
	 * added to the graph
	 *
	 * @param <V>
	 * @param <E>
	 * @param g
	 * @return
	 */
	public static <V, E> Graph<V, E> floydWarshall(Graph<V, E> g) {

		// copies the graph to gt, the transitive closure graph
		Graph<V, E> gt = g.clone();

		int i, j, k;
		V vi, vj, vk;
		V[] vertices = g.allkeyVerts();
//		System.out.print("vertices: ");
//		for(V v : vertices) { System.out.print(v); }
//		System.out.println("");
		for (k = 0; k < g.numVertices(); k++) {
			vk = vertices[k];
			for (i = 0; i < g.numVertices(); i++) {
				vi = vertices[i];
				for (j = 0; j < g.numVertices(); j++) {
					vj = vertices[j];
					
					if (gt.getEdge(vi, vk) == null) {
						gt.insertEdge(vi, vk, null, INF);
					}
					if (gt.getEdge(vk, vj) == null) {
						gt.insertEdge(vk, vj, null, INF);
					}
					if (gt.getEdge(vi, vj) == null) {
						gt.insertEdge(vi, vj, null, INF);
					}

					double wik = gt.getEdge(vi, vk).getWeight();
					double wkj = gt.getEdge(vk, vj).getWeight();
					double wij = gt.getEdge(vi, vj).getWeight();

					if (wik + wkj < wij) {
						gt.getEdge(vi, vj).setWeight(wik + wkj);
					}
				}
			}
		}
		return gt;
	}

	/**
	 * *
	 * Returns all possible paths from an origin to a destination given that
	 * they go through a list of in between nodes. The list of paths is sorted
	 * by their total weigth
	 *
	 * @param <V>
	 * @param <E>
	 * @param g
	 * @param vOrig
	 * @param vDest
	 * @param inBetween
	 * @param maxNumResults
	 * @return
	 */
	public static <V, E> List<LinkedList<V>> allPathsWithConstraints(Graph<V, E> g, V vOrig, V vDest,
		List<V> inBetween, int maxNumResults) {

		List<LinkedList<V>> possiblePaths = allPaths(g, vOrig, vDest);

		SortedSet<LinkedList<V>> sortedPossiblePaths = new TreeSet<>(new Comparator<LinkedList<V>>() {
			@Override
			public int compare(LinkedList<V> o1, LinkedList<V> o2) {
				double w1 = pathWeigth(o1, g);
				double w2 = pathWeigth(o2, g);
				return Double.compare(w1, w2);
			}
		});

		for (LinkedList<V> path : possiblePaths) {
			boolean doNotAdd = false;
			for (V v : inBetween) {
				if (!path.contains(v)) {
					doNotAdd = true;
				}
			}
			if (!doNotAdd) {
				sortedPossiblePaths.add(path);
			}
		}

		int counter = 0;
		List<LinkedList<V>> paths = new ArrayList<>();
		for (LinkedList<V> path : sortedPossiblePaths) {
			paths.add(path);
			counter++;
			if (counter == maxNumResults) {
				break;
			}
		}

		return paths;
	}

	/**
	 * Calcula a soma do peso de todas as arestar de uma rota
	 *
	 * @param rota
	 * @return
	 */
	public static <V, E> double pathWeigth(List<V> path, Graph<V, E> g) {
		double totalWeigth = 0.0;
		for (int i = 0; i < path.size() - 1; i++) {
			V v1 = path.get(i);
			V v2 = path.get(i + 1);
			double vertWeigth = g.getEdge(v1, v2).getWeight();
			totalWeigth = totalWeigth + vertWeigth;
		}
		return totalWeigth;
	}

}
