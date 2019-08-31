package org.outliers.retailproductfinderservice.util.pathfinder;
public class Instruction
	{
		String action;
		int moveMetric;
		String currentDirection;
		String currentNode;
		
		public Instruction(String action, int moveMetric, String currentDirection, String currentNode)
		{
			this.action = action;
			this.moveMetric = moveMetric;
			this.currentDirection = currentDirection;
			this.currentNode = currentNode;
		}
		
		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public int getMoveMetric() {
			return moveMetric;
		}

		public void setMoveMetric(int moveMetric) {
			this.moveMetric = moveMetric;
		}

		public String getCurrentDirection() {
			return currentDirection;
		}

		public void setCurrentDirection(String currentDirection) {
			this.currentDirection = currentDirection;
		}

		public String getCurrentNode() {
			return currentNode;
		}

		public void setCurrentNode(String currentNode) {
			this.currentNode = currentNode;
		}

		/**
		 * @return Edge as string
		 */
		@Override
		public String toString()
		{
			return "Instruction Action: " + action + " moveMetric: " + moveMetric + " currentDirection: " + currentDirection + " currentNode: " + currentNode;
		}
	}