package uphf.CSP.fn;

import java.util.ArrayList;

import uphf.CSP.CSP;

/**public class Backjumping {

	 public long backjumping(CSP csp) {
		int i = 0, j = 0, valeur = 0;
		boolean valide;
		
		boolean consistence;
		
		ArrayList<ArrayList<Integer>> domaines;
		domaines = DomaineListe();
		
		ArrayList<Integer> probleme = new ArrayList<>(); // array list des noeuds dit à probleme : ceux qui causent une violation plus tard
		
		
		for (int k = 0 ; k < csp.getListeVariables().size(); k++) {
			probleme.add(k, 0); //on cree une liste de k valeurs = 0
		}
			
		while (i >= 0 && i < csp.getListeVariables().size()) {
			valide = false;
			while(!valide && !csp.getListeDomaine().get(i).isEmpty()){
				valeur = domaines.get(i).remove(0);
				consistence = true;
				j = 0;
				while (j > i && consistence) {
					if (j > probleme.get(i)){
						probleme.add(i, j);
					}
	   				if (checkassignationConsistance(valeur, csp.getListeVariables().get(i).getIdV())){
	   					j++;
	   				} else {
	   					consistence = false;
	   				}
				}
				if (consistence) {
					valide = true;
					csp.getListeVariables().get(i).setValeur(valeur); // si la consistence est bonne on sait que la valeur est valide, alors on l'assigne a ma variable
				} 
			}
			
			if (!valide) {
				csp.getListeDomaine().get(i).delete(); // on supprime la valeur de mon domaine : on sait que ce n'est pas celle la
				for (int z = 0; z < csp.getListeDomaine().get(i).size(); z++)				
					domaines.get(i).add(csp.getListeDomaine().get(i).get(z));
				i = probleme.get(i); // on retourne reverifie la valeur i
			} else {
				i++;
				probleme.add(i, 0);
			}
		}
		
		if (i < 0) {
			System.out.println("BackJumping : UNSAT (pas de solution)");
			
		} else {
			// on affiche les solutions
		}
	}

}
**/