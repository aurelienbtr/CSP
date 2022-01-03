package uphf.CSP;

public class Variables {

	// LES VARIABLES CEST DES NOEUDS

	public int idV; // le nom/id  de la variable
	public int valeur;
	

	public Domaines domaine; // le domaine lié à la variable
	


	public Variables(int idV, int valeur) {
		super();
		this.idV = idV;
		this.valeur = valeur;
	}
	public int getIdV() {
		return idV;
	}
	public void setIdV(int idV) {
		this.idV = idV;
	}
	public Domaines getDomaine() {
		return domaine;
	}
	public void setDomaine(Domaines domaine) {
		this.domaine = domaine;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public Variables(int idV, int valeur, Domaines domaine) {
		super();
		this.idV = idV;
		this.valeur = valeur;
		this.domaine = domaine;
	}
	

	
	@Override
	public String toString() {
		return "[idV=" + idV +  ", domaine=";
	}
	
	public void afficherDomaines(){
        for(int i = 0; i < domaine.listeValeurs.size(); i++){
            System.out.print(domaine.listeValeurs.get(i) + ", ");
        }
        System.out.println("]");
    }
	


}
