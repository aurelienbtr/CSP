package uphf.CSP;

public class Contraintes {

	// une contraintes est un groupe de valeur entre 2 sommets autorises
	
	public int sommet1;
	public int sommet2;

	// il y a une contraintes seulement si il y a un lien entre 2 sommets
	
	// Les contraintes sont les groupe de valeurs entre 2 sommets autorisé 
	
	// CONTRAINTES CEST DES ARCS
	
	public int getSommet1() {
		return sommet1;
	}
	
	public void setSommet1(int sommet1) {
		this.sommet1 = sommet1;
	}
	public int getSommet2() {
		return sommet2;
	}
	public void setSommet2(int sommet2) {
		this.sommet2 = sommet2;
	}

	
	public Contraintes(int sommet1, int sommet2) {
		super();
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
	}

	@Override
	public String toString() {
		return "Contraintes entre [sommet1=" + sommet1 + ", sommet2=" + sommet2 + "]";
	}
	
	
	
}
