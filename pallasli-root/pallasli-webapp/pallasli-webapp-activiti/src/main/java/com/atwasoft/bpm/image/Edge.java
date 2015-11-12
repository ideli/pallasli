package com.atwasoft.bpm.image;

public class Edge extends GraphElement {
	private Node src;
	private Node dest;
	private boolean cycle;

	public Node getSrc() {
		return this.src;
	}

	public void setSrc(Node src) {
		this.src = src;
	}

	public Node getDest() {
		return this.dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}

	public boolean isCycle() {
		return this.cycle;
	}

	public void setCycle(boolean cycle) {
		this.cycle = cycle;
	}
}