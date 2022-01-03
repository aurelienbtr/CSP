package uphf.CSP;

import java.util.ArrayList;

public class CSP {

	public int durete; // on supprime un pourcentage (durete%) des contraintes
	public ArrayList<Arcs> listeArcs = new ArrayList<Arcs>();
	public int densite;
	//public int nbVar;
	public int nbContraintes;
	//public int tailleDomaine;
	public ArrayList<Variables> listeVariables = new ArrayList<Variables>();
	public ArrayList<Domaines> listeDomaine;

	public ArrayList<Integer> arcEntree;
	public ArrayList<Integer> arcSortie;
	
	

	//public ArrayList<Integer> listeValeurs; // la liste des valeurs du domaine

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



	public ArrayList<Integer> getArcEntree() {
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


		int densiteMtn = 1; // la densite est de base egal à 1
		int dureteMtn = 1; // la durete est de base egal a 1
		int nbArcs = 0;
		int nbMaxArcs= 0;

		int nbContraintes =0;

		ArrayList<Integer> listeValeurs = new ArrayList<Integer>();
		//ArrayList<Variables> listeVariables = new ArrayList<Variables>();

		/**
		 * ON CREE ALEATOIREMENT DES VARIABLES ET DES DOMAINES (pour chaque variable)
		 */

		for(int i=0; i<nbVar; i++)
		{
			for(int j=0; j<tailleDomaine; j++)
			{
				listeValeurs.add(1); // 		FAIRE UN TRUC ALEATOIRES
			}
			Domaines d = new Domaines(listeValeurs);	// on cree des domaines pour chaque noeud


			Variables var = new Variables(i,0,d);
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

		for(int i = 0; i < this.listeArcs.size(); i++) 
		{
			this.listeArcs.get(i).genererContraintes2();
		
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

		while(densiteMtn > densite)
		{
			densiteMtn = nbArcs/nbMaxArcs;
			this.listeArcs.remove(nbArcs); // A FAIRE POUR RENDRE LE TRUC AU HASARD
			nbArcs= nbArcs-1;
			// on supprime des arcs au hasard
		}




		/**
		 * ON SUPPRIME UN NB DE CONTRAINTES SELON LA DURETE QUE L'ON SOUHAITE
		 */

		//int nbContraintesMax = this.listeArcs.get(0).getListeContraintes().size();
		int nbContraintesMax = this.listeArcs.size();
		for(int i=0; i< this.listeArcs.size(); i++)
		{
			nbContraintes= this.listeArcs.get(i).getListeContraintes().size();

			while (dureteMtn > durete)
			{
				this.listeArcs.get(i).getListeContraintes().remove(i); // A FAIRE LE TRUC AU HASARD
				nbContraintes= this.listeArcs.get(i).getListeContraintes().size();
				dureteMtn = nbContraintes / nbContraintesMax;
			}
		}


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
		for (int i = 0; i < this.listeArcs.size(); i++) {
			this.listeArcs.get(i).toStringContraintes();
		}


	}



	public void verifCSP() {
		// ici on doit verifier les couples
		// pour 2 variables il doit y avoir ce couple dans les contraintes pour que ce soit valide sinon les couples hors contraintes sont rejete

		// si arc entre 2 variables : alors contraintes sinon
		// on supprime
		for(int i=0; i< this.listeArcs.size(); i++)
		{
			for(int j=0; j<this.listeVariables.size(); j++)
			{
				if(this.arcEntree.contains(i) && this.arcSortie.contains(j))
				{

				}
			}
		}
	}


	/** 
	 * SI NOUS TROUVONS UNE SOLUTION AU CSP, ALORS ON AFFICHE LES VARIABLES(NOEUDS) AVEC LES VALEURS QUI PERMETTENT DE LE RESOUDRE
	 */
	public void afficheSolution() {
		System.out.println("La solution du CSP est : \n");

		for (int i = 0; i < this.listeVariables.size(); i++)
			System.out.println(" La valeur du noeud " + i + " : " + this.listeVariables.get(i).getValeur() + "\n");
	}





	/*
	 public void AC1() {
	


	}

	public boolean revise()
	{
		boolean delete = false;
		for(int i=0; i< listeVariables.size(); i++)
		{

		}
		return delete;
	}

 */
}	