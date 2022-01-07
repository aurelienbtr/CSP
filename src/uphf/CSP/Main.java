package uphf.CSP;
import java.util.ArrayList;

import uphf.CSP.fn.Backjumping;
import uphf.CSP.fn.Backtracking;
import uphf.CSP.fn.ForwardChecking;
import uphf.CSP.fn.Solution;

public class Main {

	static CSP csp = new CSP();

	static Backtracking BT = new Backtracking();
	static Backjumping BJ = new Backjumping();
	static ForwardChecking FC = new ForwardChecking();
	static Solution s = new Solution();

	public static void main(String[] args) {

		// Le but est de generer des CSP (environs 10) avec 5 variables

		// Puis de faire la moyenne des temps pour construire une courbes

		// on implemente 3 algos

		// backtracking chronologique

		// back jumping

		// forward checking

		//int tailleDomaine, int durete(contraintes), int nbVar, int densite (arcs)
		


		csp.createCSP(10, 101, 10, 101); // CSP avec des domaines de 3, une durete de 101, 4 variables, et une densite de 101
		if (csp.verifCSP()) // si chaque arcs contient au moins une contrainte
		{
			csp.afficherCSP();

			System.out.println("\n --- BACK TRACKING --- \n");
			long debutExec = System.currentTimeMillis();
			
			
		//	System.out.println("LES SOLUTIOOOOONS");
			BT.BackT(csp);
			System.out.println("Temps de resolution BT = " + (System.currentTimeMillis() - debutExec) + " ms");
			//
			//s.toString();			

		/**	System.out.println("\n --- BACK JUMPING --- \n");

			BJ.BackJ(csp);
			System.out.println("\n --- FORWARD CHECKING --- \n");
			//FC.ForwardC(csp);**/
		}
		else System.out.println("Le CSP est mal cree, il y a des arcs sans contraintes");

	}

}
