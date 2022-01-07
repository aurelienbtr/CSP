package uphf.CSP.fn;
import uphf.CSP.CSP;
import uphf.CSP.Contraintes;
import uphf.CSP.Variables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking
{

	public Solution BackT(CSP csp){
		Solution solution = new Solution();
		int i = 0;
		int x = 0;
		Map<Integer, List<Integer>> domaines = new HashMap<Integer, List<Integer>>();
		while(i < csp.getListeVariables().size()){
			System.out.println("oui");
			domaines.put(i, new ArrayList<Integer>(csp.getListeVariables().get(i).getDomaine()));
			i++;
		}
		i = 0;
		while(i >= 0 && i < csp.getListeVariables().size()){
			List<Integer> domain = domaines.get(i);
			boolean valide = false;
			while (!valide && !domain.isEmpty()){
				x = domain.get(0);
				domain.remove(0);
				if (csp.isCoherent(i, x)){
					valide = true;
					System.out.println("coherent" +i + "et "+ x);
				}
			}
			if (!valide){

				domaines.put(i, new ArrayList<Integer>(csp.getListeVariables().get(i).getDomaine()));
				i--;
				solution.assignation.remove(i);
			}
			else{
				solution.assignation.put(i, x);
				i++;
			}	
		}
		if (i == -1 || solution.assignation.isEmpty())
		{
			System.out.println("BackTracking : UNSAT (pas de solution)");
			return solution= null;
		}
		return solution;
	}

}
