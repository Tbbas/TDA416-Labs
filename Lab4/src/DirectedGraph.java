
import java.util.*;

public class DirectedGraph<E extends Edge> {


	/**
	 * A class for comparing two edges
	 */
	private class CompKruskasEdge implements Comparator<E> {

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
		pq = new PriorityQueue(noOfNodes,new CompKruskasEdge());
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
		PriorityQueue<DijkstrasCompPath> pq = new PriorityQueue<>();
		pq.add(new DijkstrasCompPath(from));

		boolean[] visited = new boolean[adjacantNodes.size()]; //To keep track of visited


		for(boolean b : visited) {
			b = false;
		} //Set visited 2 false



		while(!pq.isEmpty()) {

		DijkstrasCompPath dc = pq.poll();

		if(!visited[dc.node]){
			if(dc.node == to) {
				return d.path.iterator();
			} else {
				visited[d.node] = true;

				Iterator<E> iter = adjacantNodes.get(dc.node).iterator();

				while(iter.hasNext()) {
					E e = iter.next();
					if(!visited[e.to]) {
						DijkstrasCompPath dc2 = new DijkstrasCompPath(e.to,dc.path);
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

		
	public Iterator<E> minimumSpanningTree() {
	}


	private class DijkstrasCompPath implements Comparable {

		public DijkstrasCompPath(int node) {

		}

		@Override
		public int compareTo(Object o) {
			return 0;
		}
	}

}
  
