package uphf.CSP.fn;
import uphf.CSP.CSP;
import uphf.CSP.Contraintes;
import uphf.CSP.Variables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Backtracking
{

	public Solution BackT(CSP csp){
		Solution solution = new Solution();
		int i = 0;
		int x = 0;
		Map<Integer, List<Integer>> domaines = new HashMap<Integer, List<Integer>>(); // utilisation d'une map, la variable a une liste d'entiers (domaines) associés
		while(i < csp.getListeVariables().size()){
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
				if (csp.isCoherent(i, x)){ // verification de mon CSP, si mon affectation est coherente
					valide = true;
					
				}
			}
			if (!valide){ // si mon csp n'est pas coherent

				domaines.put(i, new ArrayList<Integer>(csp.getListeVariables().get(i).getDomaine()));
				i--;
				solution.assignation.remove(i); // on supprime l'assignation de ma variable : on fait du backtracking
			}
			else{
				solution.assignation.put(i, x);
				i++;
			}	
		}
		if (i == -1 || solution.assignation.isEmpty()) // si mon CSP n'as pas de solutions
		{
			System.out.println("BackTracking : UNSAT (pas de solution)");
			return solution= null;
		}
		System.out.println("Les solutions de mes variables sont :\n");
		affecteVariableValeurs(csp, solution);
		
		
		Iterator iterator = solution.assignation.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          System.out.println("variable: "+mapentry.getKey()				// on affiche UNE solution de mon CSP
                            + " | valeur: " + mapentry.getValue());
        } 

		return solution;
	}

	// on affecte la valeur de la solution à ma variables : en gros on assigne ma variable
	public void affecteVariableValeurs(CSP csp, Solution solution)
	{
		for(int k=0; k< csp.nbVar; k++)
		{
			
			Iterator iterator = solution.assignation.entrySet().iterator();
	        while (iterator.hasNext()) {
	          Map.Entry mapentry = (Map.Entry) iterator.next();
	          if (mapentry.getKey().equals(csp.listeVariables.get(k).getIdV()))
				{
					csp.listeVariables.get(k).setValeur((int) mapentry.getValue());
				}
	        } 

			
		}
	}

	
}
