import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
import java.util.Collections;

public class Solveur 
{

	/* Attribut de la classe Solveur */

	private ArrayList<Noeud> liste_noeuds_ouverts=new ArrayList<Noeud>();
	private ArrayList<Noeud> liste_noeuds_fermes=new ArrayList<Noeud>();;


	/* function chargerFichier() : Permet de lire un puzzle `a partir d’un fichier
		passe  en  parametre  et  retourne  la  grille  initial  du  jeu. */

		public static Grille chargerFichier(String nomFichier){
    	
		int [][] grille = null;
		try {
			
			File f = new File(nomFichier);
			Scanner scanner = new Scanner(f);
			Scanner sc = new Scanner(scanner.nextLine());
			int N= sc.nextInt();
			grille = new int [N][N];
			int i=0;
			sc.close();
			while(scanner.hasNext()) {
				sc = new Scanner(scanner.nextLine());
				int j=0;
				while(sc.hasNext()) {
					grille[i][j]= sc.nextInt();
					j++;
				}
				i++;
			}	
			scanner.close();
			sc.close();
			} catch (FileNotFoundException exception) {
					System.out.println("File not found");
		}
		Grille etatInitial = new Grille(grille);
		return etatInitial;
	}
	
	/* function algoAstar() : implemente l’algorithme A∗ pour r ́esoudre la grille
	passee en parametre et retourne le noeud correspondant a la grille resolue sinon
	elle retourne null*/

	public Noeud algoAstar(Grille initial){

		int j = 1;

		/* 1. On  initialise  un  noeud  avec  la  grille  initiale  et  on  le  met  dans  la  liste ouverte liste ouverte.*/
		this.liste_noeuds_ouverts.add(new Noeud(initial,null,0));

		/*2. On cherche le meilleur noeud de la liste ouverte. Il s’agit du noeud qui a la valeurf=g+h minimale. On le note noeudCourant.*/
		while(this.liste_noeuds_ouverts.isEmpty()==false)
		{
			Noeud noeudCourant = meilleurNoeud(this.liste_noeuds_ouverts);

			if(noeudCourant.estUnEtatFinal()==true) {
					return noeudCourant;
			}
			/* 3. On ajoute noeudCourant a la liste fermee et on le retire de la liste ouverte. */
			this.liste_noeuds_fermes.add(noeudCourant);
			this.liste_noeuds_ouverts.remove(noeudCourant);

			/*4. On genere tous les noeuds successeurs du noeud Courant en deplacant la case vide.*/
			ArrayList<Grille> Successeurs = noeudCourant.Successeurs();
			for( Grille s : Successeurs) {
				Noeud n = new Noeud(s,noeudCourant,noeudCourant.g()+1);
				if(existeDansListe(this.liste_noeuds_fermes,s)) {continue;}
				/* Si s n’est pas dans la liste ouverte, on le rajoute a cette liste. */
				if(existeDansListe(liste_noeuds_ouverts,s)==false)
					this.liste_noeuds_ouverts.add(n);

				/* Sinon, s’il existe dans la liste ouverte un noeud n avec une grille identique `a 
				celle des, alors on remplace n par s dans la liste ouverte si et seulement si l’evaluation du noeuds est meilleure que celle den:s.f()< n.f()*/
				else{
						Noeud i=Element(this.liste_noeuds_ouverts,s);
						if(i.f()>n.f()) {this.liste_noeuds_ouverts.set(this.liste_noeuds_ouverts.indexOf(i),n);}
				}
			}
		}
		return null;
	}
	
	public Noeud Element(ArrayList<Noeud> liste,Grille s)
	{
		for( Noeud noeudtemp : liste)
		{
			if(s.equals(noeudtemp.getGrille())==true)
				return noeudtemp;
		}
		return null;
	}


	/* function existeDansListe(ArrayList<Noeud> liste,Grille g) : Permet de savoir si une grille existe dans la liste mit en parametre */

	public boolean existeDansListe(ArrayList<Noeud> liste,Grille g)
	{
		for( Noeud noeudtemp : liste)
		{
			if(g.equals(noeudtemp.getGrille())==true)
				return true;
		}
		return false;
	}
	
	/* function meilleurNoeud(ArrayList<Noeud> liste) : Retourne le meilleur noeud de la liste mit en parametre */

	public Noeud meilleurNoeud(ArrayList<Noeud> liste_noeuds) {
		int f=0;
		Noeud Meilleurnoeud = null;
		for( Noeud noeudtemp : liste_noeuds)
		{
			if(Meilleurnoeud == null)
			{
				Meilleurnoeud = noeudtemp;
				f = noeudtemp.f();
			}
			else {
				if(noeudtemp.f()<f) {
					f=noeudtemp.f();
					Meilleurnoeud=noeudtemp;
				}
			}
		}
		return Meilleurnoeud;
	}

	/* function cheminComplet(Noeud noeudFinal) : Retourne les etapes de la résoution de la grille, j'ai du utiliser la librarie collections pour inverser la liste */
	public ArrayList<Grille> cheminComplet(Noeud noeudFinal)
	{
		ArrayList<Grille> liste_grilles=new ArrayList<Grille>();
		while(noeudFinal.getPere()!=null)
		{
			Grille g=noeudFinal.getGrille();
			noeudFinal=noeudFinal.getPere();
			liste_grilles.add(g);
		}
		Collections.reverse(liste_grilles);
		return liste_grilles;
	}

	
}

				