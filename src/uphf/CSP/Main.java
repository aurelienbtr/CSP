package uphf.CSP;

public class Main {
	
	static CSP csp = new CSP();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Le but est de generer des CSP (environs 10) avec 5 variables
		
		// Puis de faire la moyenne des temps pour construire une courbes
		
		// on implemente 3 algos
		
			// backtracking chronologique
			
			// back jumping
		
			//forward checking
		
		//int tailleDomaine, int durete(contraintes), int nbVar, int densite (arcs)
		
	
		csp.createCSP(2, 100, 3, 100);
		
		csp.afficherCSP();
	}

}
