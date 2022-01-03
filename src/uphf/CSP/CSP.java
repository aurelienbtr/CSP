package uphf.CSP;

import java.util.ArrayList;
import java.util.Random;

public class CSP {

	public int durete; // on supprime un pourcentage (durete%) des contraintes
	public ArrayList<Arcs> listeArcs = new ArrayList<Arcs>();
	public int densite;
	//public int nbVar;
	public int nbContraintes;
	//public int tailleDomaine;
	public ArrayList<Variables> listeVariables = new ArrayList<Variables>();
	public ArrayList<Domaines> listeDomaine;

	//public ArrayList<Integer> arcEntree;
	//public ArrayList<Integer> arcSortie;

	public ArrayList<Contraintes> listeContraintes = new ArrayList<Contraintes>(); // toutes les contraintes de mon CSP


	//public ArrayList<Integer> listeValeurs; // la liste des valeurs du domaine

	/**
	 * Il nous faut des listes d'arcs en entree
	 * et des listes d'arcs en sortie
	 * 
	 * par exemple l'arc 1:2 et l'arc 2:3 sont definissables par une liste d'arc en entree et sortie
	 * 
	 * On sort de 1 et on entre en 2
	 * On sort en 2 et on entre en 3
	 * 
	 */

	public void createCSP(int tailleDomaine, int durete, int nbVar, int densite)
	{


		int densiteMtn = 10000; // la densite est de base egal à 100
		int dureteMtn = 10000; // la durete est de base egal a 100
		int nbArcs = 0;
		int nbMaxArcs= 0;

		int nbContraintes =0;

		ArrayList<Integer> listeValeursDomaines = new ArrayList<Integer>();
		//ArrayList<Variables> listeVariables = new ArrayList<Variables>();

		
		
		/**
		 * ON CREE ALEATOIREMENT DES VARIABLES ET DES DOMAINES (pour chaque variable)
		 */

		for(int i=0; i<nbVar; i++)
		{
			for(int j=0; j<tailleDomaine; j++)
			{
				listeValeursDomaines.add(new Random().nextInt(9)); // 	on cree des domaines avec des valeurs entre 0 et 9
			}
			Domaines d = new Domaines(listeValeursDomaines);	// on cree des domaines pour chaque noeud


			Variables var = new Variables(i,-1,d); // i= id, -1 pcq il n'y a pas encore de solutions, et d=domaine
			this.listeVariables.add(var);
		}



		/**
		 *  ON CREE DES LIENS ENTRE LES VARIABLES : DES ARCS (on lie toutes les variables entre elles : on les supprimeras plus tard)
		 */

		for(int i = 0; i < this.listeVariables.size(); i++) {
			Variables v = this.listeVariables.get(i);
			for(int j = 0; j < this.listeVariables.size(); j++) {
				Arcs arc = new Arcs (v, this.listeVariables.get(j));
				this.listeArcs.add(arc);
			}
		}





		/**
		 *  ON CREE DES CONTRAINTES             
		 */

		for(int i = 0; i < this.listeVariables.size(); i++) 
		{
			for(int j=0; j<this.listeVariables.size(); j++)
			{
				Contraintes c = new Contraintes(this.listeVariables.get(i).getIdV(), this.listeVariables.get(j).getIdV());
				listeContraintes.add(c);
			}

		}

		/* ON AJOUTE MES CRONTAINTES A MES ARCS QUANDS IL Y A UN LIEN
		 */
		ajoutContraintesArcs();
		
		for(int i=0; i<this.listeArcs.size(); i++)
		{
			System.out.println("il y a " + this.listeArcs.get(i).getListeContraintes().size() + "contraintes pour  l'arc " +i);
		}
		
		
		
		/**
		 * ON SUPPRIME UN NB D'ARCS SELON LA DENSITE QUE L'ON SOUHAITE
		 */

		//je suppose que l'on demarre avec une densite de 1
		// et nous voulons avoir une densite voulu
		// sachant que la densite est le nombre d'arc divise par le nombre d'arc maximum possible

		nbArcs= listeArcs.size();
		System.out.println("Il y a "+ nbArcs +" arcs en tout");
		nbMaxArcs = listeArcs.size();

		while(densiteMtn > densite) //100 > 50
		{
			densiteMtn = nbArcs/nbMaxArcs;
			this.listeArcs.remove(new Random().nextInt(nbArcs)); //on supprime au hasard des arcs entre 0 et nbArcs
			nbArcs= nbArcs-1;

		}




		/**
		 * ON SUPPRIME UN NB DE CONTRAINTES SELON LA DURETE QUE L'ON SOUHAITE
		 */

		//int nbContraintesMax = this.listeArcs.get(0).getListeContraintes().size();

		//System.out.println("Il y  a " + this.listeArcs.get(i).getListeContraintes().size() + "contraintes");
		/**		int nbContraintesMax = this.listeArcs.size();
		for(int i=0; i< this.listeArcs.size(); i++)
		{
			//			System.out.println("Il y  a " + this.listeArcs.get(i).getListeContraintes().size() + "contraintes");
			nbContraintes= this.listeArcs.get(i).getListeContraintes().size();
			System.out.println("nb contraintes = "+ nbContraintes);

			while (dureteMtn > durete)
			{
			//	int auhasard = new Random().nextInt(nbContraintes);
			//	System.out.println("on supprime" + auhasard);
				this.listeArcs.get(i).getListeContraintes().remove(2); //on supprime au hasard des contraintes entre 0 et nbContraintes
				//this.listeArcs.get(i).getListeContraintes().remove(new Random().nextInt(nbContraintes));
				nbContraintes= this.listeArcs.get(i).getListeContraintes().size();
				dureteMtn = nbContraintes / nbContraintesMax;
			}
		}**/
		

		verifCSP();

		// FIN DE LA CREATION DU CSP


	}



	/**
	 * METHODE POUR AFFICHER UN CSP :  
	 * 		SES VARIABLES AVEC LEURS DOMAINES
	 * 		SES ARCS
	 * 		SES CONTRAINTES
	 * 
	 * ON LANCERA CETTE METHODE APRES AVOIR RESPECTE LA DENSITE ET LA DURETE
	 */



	public void afficherCSP () {

		System.out.println("\n Noeuds + Domaines = \n");
		for (int i = 0; i < this.listeVariables.size(); i++) {
			System.out.print(this.listeVariables.get(i).toString() + " : ");
			listeVariables.get(i).afficherDomaines();
		}

		System.out.println("\n Arcs = \n");
		for (int i = 0; i < this.listeArcs.size(); i++) {
			System.out.println(this.listeArcs.get(i).toStringArc() + " , ");
		}


		System.out.println("\n Contraintes = \n");
		for (int i = 0; i < this.listeContraintes.size(); i++) {
			System.out.println("Contraintes "+ i + " = ["+ this.listeContraintes.get(i).getSommet1() + ", " + this.listeContraintes.get(i).getSommet2()+ "]");
		}

	}

	/**
	 *  FONCTION QUI ME PERMET D'AJOUTER MES CONTRAINTES A MES ARCS
	 */
	public void ajoutContraintesArcs()
	{
		for (int i = 0; i < this.listeContraintes.size(); i++) {
			for (int j=0; j< this.listeArcs.size(); j++)
			{
				if(this.listeContraintes.get(i).getSommet1() == this.getListeArcs().get(j).getV1().getIdV() && this.listeContraintes.get(i).getSommet2() == this.getListeArcs().get(j).getV2().getIdV())
				{

					ArrayList<Contraintes> contraintesAAjouter = new ArrayList<Contraintes>();
					contraintesAAjouter.add(this.listeContraintes.get(i));
					this.getListeArcs().get(j).setListeContraintes(contraintesAAjouter);
				}
				
			}
		}
	}


	public void genererContraintes3(Variables v1, Variables v2) {
		for(int i = 0; i < v1.getDomaine().listeValeurs.size(); i++) {
			for(int j = 0; j < v2.getDomaine().listeValeurs.size(); j++) {
				//Contraintes c = new Contraintes(v1.getDomaine().listeValeurs.get(i), v2.getDomaine().listeValeurs.get(j));
				Contraintes c = new Contraintes(v1.getIdV(), v2.getIdV());
				listeContraintes.add(c);
			}
		}
	}

	/** PAS FONCTIONNEL **/
	public boolean verifCSP() { //on retourne vrai si le CSP est valide
		boolean valide=false;
		// ici on doit verifier les couples
		// pour 2 variables il doit y avoir ce couple dans les contraintes pour que ce soit valide sinon les couples hors contraintes sont rejete

		// si arc entre 2 variables : alors contraintes sinon
		// on supprime


		for(Arcs arcs : this.listeArcs)
		{
			for (int i=0; i<arcs.getListeContraintes().size();i++)
			{
				if(arcs.getV1().getIdV()==arcs.getListeContraintes().get(i).getSommet1() && arcs.getV2().getIdV() == arcs.getListeContraintes().get(i).getSommet2())
				{
					valide = true;
				}
				else
				{
					valide = false;
					System.out.println("Il y a un pb, il y a un arcs entre 2 var sans qu'il y ai de contraintes" + arcs.getV2().getIdV() + " et "+ arcs.getListeContraintes().get(i).getSommet2());
					return valide;
				}

			}
		}
		System.out.println("Il n'y a pas de pb, a chaque fois qu'il y a un arc, il y a un/des contraintes");
		return valide;
		
	}
	
	




	/** 
	 * SI NOUS TROUVONS UNE SOLUTION AU CSP, ALORS ON AFFICHE LES VARIABLES(NOEUDS) AVEC LES VALEURS QUI PERMETTENT DE LE RESOUDRE
	 */

	public void afficheSolution() {
		System.out.println("La solution du CSP est : \n");

		for (int i = 0; i < this.listeVariables.size(); i++)
			System.out.println(" La valeur du noeud " + i + " : " + this.listeVariables.get(i).getValeur() + "\n");
	}

	
	

	/**
	 * 			GETTERS ET SETTERS DU CSP (on en a besoin pour les fns)
	 * 
	 */

	public int getDurete() {
		return durete;
	}
	public void setDurete(int durete) {
		this.durete = durete;
	}
	public ArrayList<Arcs> getListeArcs() {
		return listeArcs;
	}
	public void setListeArcs(ArrayList<Arcs> listeArcs) {
		this.listeArcs = listeArcs;
	}
	public int getDensite() {
		return densite;
	}
	public void setDensite(int densite) {
		this.densite = densite;
	}
	public int getNbContraintes() {
		return nbContraintes;
	}
	public void setNbContraintes(int nbContraintes) {
		this.nbContraintes = nbContraintes;
	}
	public ArrayList<Variables> getListeVariables() {
		return listeVariables;
	}
	public void setListeVariables(ArrayList<Variables> listeVariables) {
		this.listeVariables = listeVariables;
	}
	public ArrayList<Domaines> getListeDomaine() {
		return listeDomaine;
	}
	public void setListeDomaine(ArrayList<Domaines> listeDomaine) {
		this.listeDomaine = listeDomaine;
	}

	/**public ArrayList<Integer> getArcEntree() {
		return arcEntree;
	}
	public void setArcEntree(ArrayList<Integer> arcEntree) {
		this.arcEntree = arcEntree;
	}
	public ArrayList<Integer> getArcSortie() {
		return arcSortie;
	}
	public void setArcSortie(ArrayList<Integer> arcSortie) {
		this.arcSortie = arcSortie;
	}
	 **/

}	