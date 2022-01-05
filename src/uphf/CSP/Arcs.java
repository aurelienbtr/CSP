package uphf.CSP;

import java.util.ArrayList;

public class Arcs {

	public Variables v1;
	public Variables v2;

	public String toStringArc() {
		return "Arcs [" + v1.getIdV() + "," + v2.getIdV() + "]";
	}


	public ArrayList<Contraintes> listeContraintes;


	public Variables getV1() {
		return v1;
	}


	public void setV1(Variables v1) {
		this.v1 = v1;
	}


	public Variables getV2() {
		return v2;
	}


	public void setV2(Variables v2) {
		this.v2 = v2;
	}


	public ArrayList<Contraintes> getListeContraintes() {
		return listeContraintes;
	}


	public void setListeContraintes(ArrayList<Contraintes> listeContraintes) {
		this.listeContraintes = listeContraintes;
	}


	/**public void genererContraintes() {
		for(int i = 0; i < v1.getDomaine().listeValeurs.size(); i++) {
			for(int j = 0; j < v2.getDomaine().listeValeurs.size(); j++) {
			//Contraintes c = new Contraintes(v1.getDomaine().listeValeurs.get(i), v2.getDomaine().listeValeurs.get(j));
				Contraintes c = new Contraintes(v1.getIdV(), v2.getIdV());
				listeContraintes.add(c);
			}
		}
	}
**/
	
	public void genererContraintes2() {
		Contraintes c = new Contraintes(v1.getIdV(), v2.getIdV());
		listeContraintes.add(c); 
	}



	public void toStringContraintes() {

		for(int i = 0; i < listeContraintes.size(); i++) {
			System.out.println(listeContraintes.get(i).toString() + ", ");
		}

	}

	public Arcs(Variables v1, Variables v2, ArrayList<Contraintes> listeContraintes) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.listeContraintes = listeContraintes;
	}

	public Arcs(Variables v1, Variables v2) {
		super();
		this.listeContraintes = new ArrayList<Contraintes>();
		this.v1 = v1;
		this.v2 = v2;

	}

}


