package game.tree;

import game.Turn;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NodeTree {
	private Point point;
	private int hValue;
	private boolean prune;
	private boolean selected;
	private List<NodeTree> children = new LinkedList<NodeTree>();;
	private Turn turn;

	public NodeTree() {
		super();
	}

	public NodeTree(Point p, Turn turn) {
		this.point = p;
		this.prune = false;
		this.selected = false;
		this.turn = turn;
	}

	public void setSelected() {
		this.selected = true;
	}

	public void setHvalue(int h) {
		this.hValue = h;
	}

	public void addChild(NodeTree node) {
		this.children.add(node);
	}

	public void setPrune() {
		this.prune = true;
	}

	public void printNode(FileWriter fw) throws IOException {
		if (point == null) {
			fw.write(this.hashCode() + "[label =" + '"' + "START " + hValue() + '"' + ",shape=rectangle,style=filled,fillcolor=red]");
		} else if (selected) {
			fw.write(this.hashCode() + " [label=" + '"' + "(" + point.y + ","
					+ point.x + ") " + hValue() + '"'
					+ (turn == Turn.PC ? ",shape=rectangle" : "")
					+ ",style=filled,fillcolor=red]");
		} else if (prune) {
			fw.write(this.hashCode() + " [label=" + '"' + "(" + point.y + ","
					+ point.x + ") " + '"'
					+ (turn == Turn.PC ? ",shape=rectangle" : "")
					+ ",style=filled,fillcolor=grey]");
		} else {
			fw.write(this.hashCode() + " [label=" + '"' + "(" + point.y + ","
					+ point.x + ") " + hValue() + '"'
					+ (turn == Turn.PC ? ",shape=rectangle" : "") + "]");
		}
		fw.write('\n');
		for (NodeTree node : children) {
			fw.write(this.hashCode() + " -> " + node.hashCode());
			fw.write('\n');
			node.printNode(fw);
		}
	}
	
	private String hValue() {
		if ( hValue == Integer.MAX_VALUE ) {
			return "+inf";
		} else if ( hValue == Integer.MIN_VALUE ) {
			return "-inf";
		} else {
			return String.valueOf(hValue);
		}
	}
}
