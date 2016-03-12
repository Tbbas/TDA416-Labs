
import java.util.*;

public class DirectedGraph<E extends Edge> {


	/**
	 * A class for comparing two edges
	 */
	private class CompKruskalEdge implements Comparator<E> {

		@Override
		public int compare(E t, E t1) {
			if(t.getWeight() > t1.getWeight()) return 1;

			if(t1.getWeight() < t1.getWeight()) return -1;

			return 0;
		}
	}


	HashMap<Integer,Set<E>> adjacantNodes;

	PriorityQueue<E> pq;


	/**
	 * Creates a DirectedGraph, containing noOfNodes nodes and a priorityqueue
	 * @param noOfNodes
     */
	public DirectedGraph(int noOfNodes) {
		pq = new PriorityQueue(noOfNodes,new CompKruskalEdge());
		adjacantNodes = new HashMap<>();

		for(int i =0; i<noOfNodes;i++) {
			adjacantNodes.put(i,new HashSet<E>());
		}
	}

	/**
	 * Adds an edge t adjacantNodes
	 * @param e
     */
	public void addEdge(E e) {
		Set<E>  set = adjacantNodes.get(e.from);
		set.add(e);

		adjacantNodes.put(e.from,set);

		pq.add(e);
	}
	/**
	 * Returns the shortest path from the two given nodes using djikstrats algorithm
	 * @param from 	origin point
	 * @param to 		target point
	 * returns iterator of edges in path
	 */
	public Iterator<E> shortestPath(int from, int to) {
		PriorityQueue<CompDijkstraPath> pq = new PriorityQueue<>();
		pq.add(new CompDijkstraPath(from));

		boolean[] visited = new boolean[adjacantNodes.size()]; //To keep track of visited


		for(boolean b : visited) {
			b = false;
		} //Set visited 2 false



		while(!pq.isEmpty()) {

		CompDijkstraPath dc = pq.poll();

		if(!visited[dc.node]){
			if(dc.node == to) {
				//add all the edges to pq
				return dc.path.iterator();
			} else {
				visited[dc.node] = true;

				Iterator<E> iter = adjacantNodes.get(dc.node).iterator();

				while(iter.hasNext()) {
					E e = iter.next();
					CompDijkstraPath dc2 = new CompDijkstraPath(e.to, new HashSet<>(dc.path));
						dc2.addEdge(e);
						pq.add(dc2);
					}
				}
			}
		}
			return null;
	}

	/**
	 * Returns the edge between two nodes,
	 * iterates over all nodes that have connection to "from" untill the specified edge is found.
	 * @param from
	 * @param to
   * @return
   */
		private E getEdge(int from, int to) {
		Set<E> set = adjacantNodes.get(from);

		Iterator<E> iter = set.iterator();

		while(iter.hasNext()) {
			E e = iter.next();
			if(e.from == from && e.to == to) {
				return  e;
			}
		}
		return null;
	}


	/**
	 * Checks if there is a cycle in the graph
	 * @param set
	 * @param from
	 * @param to
     * @return
     */
	private boolean checkForCycle(Set<E> set,int from, int to) {
		Iterator<E> iter = set.iterator();
		Set<Integer> conset = new HashSet<Integer>();

		//Add all the nodes to this new set.
		while(iter.hasNext()) {
			E e = iter.next();
			conset.add(e.from);
			conset.add(e.to);
		}

		return conset.contains(from) && conset.contains(to);

	}
	private int nbrofedges = 0;
	/**
	 * Returns minimal spanning tree, calculated using kruskals algorithm
	 * @return Iterator of mst
	 */
	public Iterator<E> minimumSpanningTree() {
		PriorityQueue<E> copyOfPq = new PriorityQueue(adjacantNodes.size(),new CompKruskalEdge());
		Map<Integer, Set<E>> map = new HashMap<>();
		for(int i = 0 ; i < adjacantNodes.size(); i++){
			map.put(i, new HashSet<E>());
		}
		//Gets all edges from pritority queue
		for(E e = pq.poll(); !pq.isEmpty() && nbrofedges < adjacantNodes.size(); e = pq.poll()){
			int from = e.getSource();
			int to = e.getDest();
			double weight = e.getWeight();
			copyOfPq.add(e);

			if(map.get(from) != map.get(to)){
				Set<E> newSet = null;
				Set<E> nodes = null;
				if(map.get(from).size() >= map.get(to).size()){
					newSet =  merge(map.get(from), map.get(to), e);
					nodes = map.get(e.to);
				}else{
					newSet =  merge(map.get(to), map.get(from), e);
					nodes = map.get(e.from);
				}
				map.put(from, newSet);
				map.put(to, newSet);

				Iterator<E> iter = nodes.iterator();
				while(iter.hasNext()){
					E node = iter.next();
					map.put(node.to, newSet);
					map.put(node.from, newSet);
				}
			}
		}
		for(E e: copyOfPq) {
			pq.add(e);
		}//so that no edges are lost
		return map.get(0).iterator();
	}
	private Set<E> merge(Set<E> from, Set<E> to, E e){
		// if there is not a cycle, merge and add the new node.
		// Else the nodes already are in the same list.
		if(from != to) {
			Iterator<E> iter = to.iterator();

			while (iter.hasNext()) {
				from.add(iter.next());
			}

			from.add(e);
			nbrofedges++;
		}

		return from;
	}


	private class CompDijkstraPath implements Comparable {

		private int node;
		private Set<E> path;
		private double weight;

		private CompDijkstraPath(int node) {
			this.node = node;
			this.path = new HashSet<>();
			this.weight = 0;
		}

		private CompDijkstraPath(int node, Set<E> set) {
			this.node = node;
			this.path = set;
			calcWeight();
		}

		private void addEdge(E e) {
			path.add(e);
			weight += e.getWeight();
		}

		private void addPath(Set<E> path) {
			this.path.addAll(path);
			for(E e:path) {
				weight += e.getWeight();
			}
		}


		private void calcWeight() {
			Iterator<E> iter = path.iterator();
			double sum = 0;

			while(iter.hasNext()) {
				sum += iter.next().getWeight();
			}

			weight = sum;
		}

		@Override
		public int compareTo(Object o) {
			CompDijkstraPath o1 = (CompDijkstraPath)o;

			if(this.weight > o1.weight) return 1;
			if(this.weight < o1.weight) return -1;


			return 0;
		}
	}

}
