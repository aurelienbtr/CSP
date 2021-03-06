package uphf.CSP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CSP {

	public double durete; // on supprime un pourcentage (durete%) des contraintes
	public ArrayList<Arcs> listeArcs = new ArrayList<Arcs>();
	public int densite; // densite d'arcs voulu par le CSP
	public int nbVar; // nombre de variables du CSP
	public int nbContraintes; // nombre de contraintes du CSP
	public ArrayList<Variables> listeVariables = new ArrayList<Variables>(); // toutes les variables de mon csp
	public ArrayList<Integer> listeDomaine; // liste des domaines
	public ArrayList<Contraintes> listeContraintes = new ArrayList<Contraintes>(); // toutes les contraintes de mon CSP


	public void createCSP(int tailleDomaine, double durete, int nbVar, double densite)
	{

		double densiteMtn = 1.0; // la densite est de base egal à 1
		double dureteMtn = 1.0; // la durete est de base egal a 100



		/**
		 * ON CREE ALEATOIREMENT DES VARIABLES ET DES DOMAINES (pour chaque variable)
		 */


		ArrayList<Integer> domaineNull = new ArrayList<Integer>();
		domaineNull.add(0);

		for(int i=0; i<nbVar; i++)
		{
			//Domaines d = new Domaines(listeValeursDomaines);	// on cree des domaines pour chaque noeud

			Variables var = new Variables(i,-1,domaineNull); // i= id, -1 pcq il n'y a pas encore de solutions, et d=domaine
			this.listeVariables.add(var);
		}
		for(Variables v : listeVariables)
		{
			ArrayList<Integer> domaineDeVar = new ArrayList<Integer>();
			for(int j=0; j<tailleDomaine; j++)
			{

				int auhasard = new Random().nextInt(9);
				domaineDeVar.add(auhasard); // 	on cree des domaines avec des valeurs entre 0 et 9
				//System.out.println(domaineDeVar); // petite verif dans la console
			}

			v.setDomaine(domaineDeVar); //on ajoute les domaines a chaque valeurs

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


		/**
		 * ON SUPPRIME UN NB D'ARCS SELON LA DENSITE QUE L'ON SOUHAITE
		 */
		int nbArcs = 0;
		int nbMaxArcs= 0;

		//je suppose que l'on demarre avec une densite de 100
		// et nous voulons avoir une densite voulu
		// sachant que la densite est le nombre d'arc divise par le nombre d'arc maximum possible

		nbArcs= listeArcs.size();
		nbMaxArcs = listeArcs.size();

		while(densiteMtn > densite) //100 > 50
		{
			densiteMtn = nbArcs * 1.0 /nbMaxArcs;
			this.listeArcs.remove(new Random().nextInt(nbArcs)); //on supprime au hasard des arcs entre 0 et nbArcs
			nbArcs= nbArcs-1;

		}

		// a partir d'ici logiquement il y aura plus de contraintes que d'arcs

		/**
		 * ON SUPPRIME UN NB DE CONTRAINTES SELON LA DURETE QUE L'ON SOUHAITE
		 */

		int nbContraintesMax = this.listeContraintes.size();

		for(int i=0; i < nbContraintesMax; i++)
		{
			nbContraintes = this.listeContraintes.size();
			while (dureteMtn > durete)
			{
				int auhasard = new Random().nextInt(nbContraintes);
				System.out.println("on supprime la numero" + auhasard);
				int s1,s2=0;
				s1=this.listeContraintes.get(auhasard).getSommet1();
				s2=this.listeContraintes.get(auhasard).getSommet2();

				this.listeContraintes.remove(auhasard);
				System.out.println("une contraine a ete supprime" + s1 + " avec "+s2);



				// SI ON SUPPRIME UNE CONTRAINTES ALORS ON SUPPRIME EGALEMENT L'ARC
				for(int j=0; j<this.listeArcs.size(); j++)
				{
					if (this.listeArcs.get(j).getV1().getIdV() == s1 && this.listeArcs.get(j).getV2().getIdV() == s2)
					{
						System.out.println("on supprime l'arc entre " + s1 +" et"+ s2);
						this.listeArcs.remove(j);
					}
				}



				nbContraintes=nbContraintes-1;
				System.out.println(nbContraintes);
				System.out.println(nbContraintesMax);
				dureteMtn = nbContraintes * 1.0 / nbContraintesMax;
				System.out.println(dureteMtn);
			}
		}


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
			System.out.print(this.listeVariables.get(i).toString());
			//listeVariables.get(i).afficherDomaines();
			//listeVariables.get(i).afficherDomaines2();
			System.out.println("");
		}

		System.out.println("\n Arcs = \n");
		System.out.println(this.listeArcs.size());
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



	/** FONCTION QUI PERMET DE VERIFIER LA CREATION DU CSP
	 * EN EFFET, SI IL EXISTE DES ARCS SANS CONTRAINTES ALORS LE CSP NEST PAS VALIDE
	 **/
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
	 * FONCTION QUI PERMET DE SAVOIR SI UNE SOLUTION POUR UNE VARIABLE EST COHERENTE AVEC LES AUTRES, AVEC LE CSP...
	 * 
	 */
	public boolean isCoherent(int i, int valeurSol) {
		boolean ok=false;

		Variables v = listeVariables.get(i); // on recupere la variable a tester
		v.setIdV(valeurSol);
		if(i != 0)
			for (int j = 0; j < i; j ++){
				Variables v2 = listeVariables.get(j);
				//Arcs arc = new Arcs(v, v2);
				Contraintes ctrTemp = contrainteATrouver2(v, v2); // on cherche si il y a une contraintes entre ses 2 variables

				if (ctrTemp != null)// && arc.getListeContraintes().contains(ctrTemp))
					ok = true; // si il y a une contrainte c'est ok, donc valide
				else{
					//System.out.println("la contrainte entre "+v.getIdV()+" et "+v2.getIdV() +" n'as pas était trouve");
					return false;
				}
			}
		else{
			return true;
		}
		return ok;
	}

	/**
	 * FONCTION QUI PERMET DE SAVOIR SI UNE CONTRAINTES EXISTE ENTRE 2 VARIABLES, ET AINSI DE LA RETOURNER
	 */


	private Contraintes contrainteATrouver2(Variables v1, Variables v2)
	{
		for (Arcs a : this.listeArcs)
		{
			if ((a.getListeContraintes().get(0).getSommet1()== v1.getIdV()) && (a.getListeContraintes().get(0).getSommet2()==v2.getIdV()) || ( (a.getListeContraintes().get(0).getSommet1()== (v2.getIdV()) && (a.getListeContraintes().get(0).getSommet2() ==v1.getIdV()))))
			{
				return a.getListeContraintes().get(0);
			}
		}
		return null;
	}



	/**
	 * 			GETTERS ET SETTERS DU CSP (on en a besoin pour les fonctions)
	 * 
	 */

	public double getDurete() {
		return durete;
	}
	public void setDurete(double durete) {
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
	public ArrayList<Integer> getListeDomaine() {
		return listeDomaine;
	}
	public void setListeDomaine(ArrayList<Integer> listeDomaine) {
		this.listeDomaine = listeDomaine;
	}

	public int getNbVar() {
		return nbVar;
	}

	public void setNbVar(int nbVar) {
		this.nbVar = nbVar;
	}

	public ArrayList<Contraintes> getListeContraintes() {
		return listeContraintes;
	}

	public void setListeContraintes(ArrayList<Contraintes> listeContraintes) {
		this.listeContraintes = listeContraintes;
	}




}	