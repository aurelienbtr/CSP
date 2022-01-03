package uphf.CSP.fn;
import uphf.CSP.CSP;
import uphf.CSP.Contraintes;
import uphf.CSP.Domaines;
import uphf.CSP.Variables;
import java.util.ArrayList;

public class Backtracking
{
	public ArrayList<Variables> BackT(CSP csp)
	{
		int i = 0, j, assignation;
		boolean validation;
		ArrayList<Variables> listeSolutions = new ArrayList<>();
		ArrayList<Domaines> listeDomaines = new ArrayList<>();

		for(j = 0; j < csp.getListeVariables().size(); j++)
		{
			listeSolutions.add(new Variables(j, csp.getListeVariables().get(j).getValeur())); // on considere de base que toutes les variables sont des solutions = sont bien assigne
			listeDomaines.add(new Domaines(csp.getListeVariables().get(j).getDomaine().getListeValeurs())); // j'ajoute a ma liste de domaine, les domaines respectifs de mes variables
		}
		while( (i >= 0) && (i < csp.getListeVariables().size() ) )
		{
			validation = false;
			while( (!validation) && (listeDomaines.get(i).getListeValeurs().size() != 0) ) // tant qu'une variable n'a pas de solution
			{
				assignation = listeDomaines.get(i).getListeValeurs().get(0);
				listeDomaines.get(i).getListeValeurs().remove(0);
				listeSolutions.get(i).setValeur(assignation); // on assigne la valeur de ma variable
				if(assignationCoherente(csp, listeSolutions) )
				{
					validation = true; // si ma valeur est coherente alors la valide
				}
			}
			if(!validation)
			{
				listeDomaines.get(i).getListeValeurs().clear();
				for(j = 0; j <= csp.getListeVariables().size(); j++)
				{
					listeDomaines.get(i).getListeValeurs().add(j);
				}
				listeSolutions.get(i).setValeur(-1); // la valeur de ma variable n'est pas coherente, on la met a 0
				i--;
			}
			else
			{
				i++;
			}
		}
		if(i == -1)
        {
            return null;
        }
        else
        {
            return listeSolutions;
        }
	}


	public boolean assignationCoherente(CSP csp, ArrayList<Variables> listeSolutions)
	{
		boolean validation = false;
		int nbArcs = csp.getListeArcs().size();
		Variables s1 = null, s2 = null;

		for(int i=0; i< nbArcs; i++)
		{
			for(Variables solution : listeSolutions)
			{
				if(csp.getListeArcs().get(i).getV1().equals(solution.getValeur() ) )
				{
					s1.setValeur(solution.getValeur());
				}
				if(csp.getListeArcs().get(i).getV2().equals(solution.getValeur() ) )
				{
					s2.setValeur(solution.getValeur());
				}
			}
			for(Contraintes c : csp.getListeArcs().get(i).getListeContraintes())
			{
				if(c.getSommet1() == s1.getValeur() && c.getSommet2() == s2.getValeur())
				{
					validation = true;
				}
			}
		}
		return validation;
	}
}