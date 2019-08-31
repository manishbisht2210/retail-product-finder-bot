package org.outliers.retailproductfinderservice.util.pathfinder;
import java.util.ArrayList;
import java.util.List;

public class Result
{
	List<Instruction> instructions;
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public void setInstructions(List<Instruction> instructions) {
		this.instructions= instructions;
	}

	public String getFinalDirection()
	{
		return !instructions.isEmpty()?instructions.get(instructions.size()-1).currentDirection:"";
	}
	
	public String getFinalNode()
	{
		return !instructions.isEmpty()?instructions.get(instructions.size()-1).currentNode:"";
	}
	
	void addInstruction(String action, int moveMetric, String currentDirection, String currentNode)
	{
		if(instructions==null)
		{
			instructions = new ArrayList<>();
		}
		instructions.add(new Instruction(action, moveMetric, currentDirection, currentNode));
	}

	
}