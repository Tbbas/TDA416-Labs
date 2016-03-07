
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
	 * Sets the location.
	 */
	public void setLocation() {

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
				return dc.path.iterator();
			} else {
				visited[dc.node] = true;

				Iterator<E> iter = adjacantNodes.get(dc.node).iterator();

				while(iter.hasNext()) {
					E e = iter.next();
					if(!visited[e.to]) {
						CompDijkstraPath dc2 = new CompDijkstraPath(e.to,dc.path);
						dc2.addEdge(e);
						pq.add(dc2);
					}
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

	/**
	 * Returns minimal spanning tree
	 * @return Iterator of mst
	 */
	public Iterator<E> minimumSpanningTree() {
		Set<E> set = new HashSet<E>();
		//Gets all edges from pritority queue
		for(E e = pq.poll(); !pg.isEmpty(); e = pq.poll()){
			set.add(e);
			//Checkf if there is a cycle after adding e, in that case remove it.
			if(checkForCycle(e, e.getSource(), e.getDest())){
				set.remove(e);
			}
		}
		return set.iterator();
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
			calcWeight();
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
