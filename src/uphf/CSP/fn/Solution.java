package uphf.CSP.fn;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	
	Map<Integer, Integer> assignation;
	
	public Solution(){
		this.assignation = new HashMap<Integer, Integer>();
	}

	@Override
	public String toString() {
		String solStr = "Solution : \n";
		for(Map.Entry<Integer, Integer> e : assignation.entrySet())
			solStr += "[ Variable : " + e.getKey() + ", Valeur : " + e.getValue() + " ]\n";
		return solStr;
	}
	
	

}