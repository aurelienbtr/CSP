package uphf.CSP;
import uphf.CSP.fn.Backtracking;

public class Main {
	
	static CSP csp = new CSP();
	static Backtracking BT = new Backtracking();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Le but est de generer des CSP (environs 10) avec 5 variables
		
		// Puis de faire la moyenne des temps pour construire une courbes
		
		// on implemente 3 algos
		
			// backtracking chronologique
			
			// back jumping
		
			//forward checking
		
		//int tailleDomaine, int durete(contraintes), int nbVar, int densite (arcs)
		
	
		csp.createCSP(3, 101, 4, 101);
		if (csp.verifCSP())
		{
		csp.afficherCSP();
		}
		else System.out.println("Le CSP est mal cree, il y a des arcs sans contraintes");
		System.out.println("\n --- BACK TRACKING --- \n");
		//BT.BackT(csp);
	}

}
