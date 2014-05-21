package game;

import game.tree.NodeTree;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MiniMax {
	private boolean prune;
	private boolean tree;
	private int depth;
	private int time;
	private long startTime;

	public MiniMax(Boolean prune, Boolean tree, int depth, int time) {
		this.prune = prune;
		this.tree = tree;
		this.depth = depth;
		this.time = time;
	}

	private boolean validateTime() {
		return ((System.currentTimeMillis() - startTime) / 1000) < time;
	}

	public Point miniMax(State state) {
		startTime = System.currentTimeMillis();
		Point ans = null;
		NodeTree first = new NodeTree();

		if (time > 0) {
			Point aux = null;
			NodeTree auxTree = new NodeTree();
			while (validateTime()) {
				depth++;
				aux = miniMax(state, auxTree);
				if (validateTime()) {
					ans = aux;
					first = auxTree;
				}
			}
		} else {
			ans = miniMax(state, first);

		}

		if (tree)
			drawTree(first);

		return ans;
	}

	private int noMoreMoves(int hValue) {
		if (hValue > 0)
			return Integer.MAX_VALUE;
		else if (hValue < 0)
			return Integer.MIN_VALUE;
		else
			return hValue;
	}

	private Point miniMax(State current, NodeTree currentNode) {
		List<Point> moves = current.moves();
		Point ans = null;
		int maxHvalue = Integer.MIN_VALUE;
		NodeTree selectedNode = null;

		for (Point p : moves) {
			State actual = current.copyState();
			NodeTree actualNode = new NodeTree(p, Turn.HUMAN);
			currentNode.addChild(actualNode);

			if (prune && maxHvalue == Integer.MAX_VALUE) {
				actualNode.setPrune();
			} else {
				int h = minNode(p, actual, maxHvalue, Integer.MAX_VALUE, 1,
						actualNode);
				if (h >= maxHvalue) {
					ans = p;
					maxHvalue = h;
					selectedNode = actualNode;
				}
				actualNode.setHvalue(h);
			}

			if ((time > 0 && !validateTime())) {
				return ans;
			}
		}
		currentNode.setHvalue(maxHvalue);
		selectedNode.setSelected();
		return ans;
	}

	private int minNode(Point p, State current, int alpha, int beta,
			int height, NodeTree currentNode) {
		current.removeAndPoints(p, Turn.PC);
		List<Point> moves = current.moves();
		if (moves.isEmpty())
			return noMoreMoves(current.getHeuristicValue());
		if (height > depth)
			return current.getHeuristicValue();

		int minHvalue = Integer.MAX_VALUE;
		NodeTree selectedNode = null;

		for (Point point : moves) {
			State actual = current.copyState();
			NodeTree actualNode = new NodeTree(point, Turn.PC);
			currentNode.addChild(actualNode);

			if (prune && beta <= alpha) {
				actualNode.setPrune();
			} else {
				int h = maxNode(point, actual, alpha, beta, height + 1,
						actualNode);
				if (h <= minHvalue) {
					minHvalue = h;
					selectedNode = actualNode;
				}
				if (beta > minHvalue) {
					beta = minHvalue;
				}
				actualNode.setHvalue(h);
			}

			if ((time > 0 && !validateTime())) {
				return minHvalue;
			}
		}
		selectedNode.setSelected();
		return minHvalue;
	}

	private int maxNode(Point p, State current, int alpha, int beta,
			int height, NodeTree currentNode) {
		current.removeAndPoints(p, Turn.HUMAN);
		List<Point> moves = current.moves();
		if (moves.isEmpty())
			return noMoreMoves(current.getHeuristicValue());
		if (height > depth)
			return current.getHeuristicValue();

		int maxHvalue = Integer.MIN_VALUE;
		NodeTree selectedNode = null;

		for (Point point : moves) {
			State actual = current.copyState();
			NodeTree actualNode = new NodeTree(point, Turn.HUMAN);
			currentNode.addChild(actualNode);
			if (prune && beta <= alpha) {
				actualNode.setPrune();
			} else {
				int h = minNode(point, actual, alpha, beta, height + 1,
						actualNode);
				if (h >= maxHvalue) {
					maxHvalue = h;
					selectedNode = actualNode;
				}
				actualNode.setHvalue(h);
				if (alpha < maxHvalue) {
					alpha = maxHvalue;
				}
			}
			if ((time > 0 && !validateTime())) {
				return maxHvalue;
			}

		}
		selectedNode.setSelected();
		return maxHvalue;
	}

	private void drawTree(NodeTree node) {
		try {
			File tree = new File("tree.dot");
			FileWriter writeTree = new FileWriter(tree, false);

			writeTree.write("digraph {");
			writeTree.write('\n');

			node.printNode(writeTree);
			writeTree.write('\n');
			writeTree.write("}");
			writeTree.close();
		} catch (IOException e) {
			System.err.println("Error al escribir.");
		}
	}

}
