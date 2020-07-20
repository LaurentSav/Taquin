import java.util.Arrays;
import java.util.ArrayList;

public class Noeud{

	/* Attribut de la classe Noeud */
	/**
	—grillerepr ́esente la grille du jeu.
	—pereest la r ́ef ́erence vers le noeud p`ere dans l’arbre de recherche.
	—g est un champs qui sauvegarde le nombre de mouvements effectu ́es depuis laracine de l’arbre
	**/
	private Grille grille;
	private Noeud pere;
	private int g;

  /* Constructeur */

	public Noeud(Grille grille, Noeud p,int g){

      this.grille = grille;
      this.pere = p;
      this.g = g;
  }

  
	/* Méthodes */

	/* function getGrille() : Retourne la grille du noeud */
  public Grille getGrille(){
		return this.grille;
  }

	/* function getPere() : Retourne le noeud pere du noeud */
  public Noeud getPere(){
    return this.pere;
  }
	
	/* function h1 : Méthode h1, retourne le nombre de case mal placées */
	private int h1() {
 
    int h = 0;
    int [][] Grille2 = new int [this.grille.getTaille()][this.grille.getTaille()];
    for(int i=0;i<this.grille.getTaille();i++) {
    	for(int j=0;j<this.grille.getTaille();j++) {
    		Grille2[i][j]=i*this.grille.getTaille()+j+1;
    	}
    }
    Grille2[this.grille.getTaille()-1][this.grille.getTaille()-1]=0;
    for(int i=0;i<this.grille.getTaille();i++) {
    	for(int j=0;j<this.grille.getTaille();j++) {
    		if(Grille2[i][j]!= this.grille.getGrille()[i][j] && this.grille.getGrille()[i][j]!=0) {
    			h=h+1;
    		}
    	}
    }
    return h;
  }

  /* function h2 : Méthode h2, la somme des distances des cases par rapport `a leurs positions cibles. */
	private int h2() {
		int h2 = 0;
		int [][] Grille2 = new int [this.grille.getTaille()][this.grille.getTaille()];
		for(int i=0;i<this.grille.getTaille();i++) {
			for(int j=0;j<this.grille.getTaille();j++) {
				Grille2[i][j]=i*this.grille.getTaille()+j+1;
			}
		}
		Grille2[this.grille.getTaille()-1][this.grille.getTaille()-1]=0;
		int[] tab = new int [this.grille.getTaille()*this.grille.getTaille()];
		for(int i=0;i<this.grille.getTaille();i++) {
			for(int j=0;j<this.grille.getTaille();j++) {
				tab[i+j] =grille.getGrille()[i][j];
			}
		}
		for(int i=0;i<this.grille.getTaille();i++) {
			for(int j=0;j<this.grille.getTaille();j++) {
				if(Grille2[i][j]!= this.grille.getGrille()[i][j] && this.grille.getGrille()[i][j]!=0){
					h2=h2+(Math.abs(((tab[i+j]-1)/this.grille.getTaille())-i))+(Math.abs(((tab[i+j]-1)%this.grille.getTaille())-j));
					}
				}
			}
		return h2;
	}

	/* function h() : mettre h1 ou h2*/
	public int h(){
		return h1();
	}

	/* function h() : Retourne g*/
	public int g(){
		return this.g;
	}

	/* function h() : f = g() + h()*/
	public int f(){
		int f = this.g() + this.h();
		return f;
	}

	/* function estU nEtatFinal() retourne vrai si le noeud contient la configuration finale (noeud but)*/
	public boolean estUnEtatFinal()
 	{
 		int taille = this.grille.getTaille();
 	    boolean etat = true;
 	    int [][] Grille2 = new int [taille][taille];
 	    for(int i=0;i<taille;i++) {
 	    	for(int j=0;j<taille;j++) {
 	    		Grille2[i][j] = i*taille+j+1;
 	    	}
 	    }
 	   Grille2[taille-1][taille-1] = 0;
 	    for(int i=0;i<taille;i++) {
 	    	for(int j=0;j<taille;j++) {
 	    		if(Grille2[i][j]!= this.grille.getGrille()[i][j] && this.grille.getGrille()[i][j]!=0) {
 	    			etat = false;
 	    		}
 	    	}
 	    }
 	    return etat;
 	}

	/* La methode successeurs() retourne une liste de tous les noeuds successeurs
obtenus en d ́epla ̧cant la case vide vers le haut, le bas, la gauche et la droite.
Le nombre de successeurs varie entre 2 et 4 selon la position de la case vide
*/

	public ArrayList<Grille> Successeurs()
	{
			ArrayList<Grille> GrilleSuivante = new ArrayList<Grille>();
			int ligne = grille.getLigne0();
			int colonne = grille.getColonne0();
			int taille=grille.getTaille();
			int ligne0 = 0, ligne1 = 0,colonne1 = 0,colonne0 = 0;
			if(ligne == 0)
			{
					if(colonne == 0)
					{
							colonne1 = 1;
							ligne1 = 1;
					}
					else if(colonne>0 && colonne<taille-1)
					{
							colonne1 = 1;
							colonne0 = 1; 
							ligne1 = 1;
					}
					else if(colonne==taille-1){colonne0=1;ligne1=1;}
			}
			else if(ligne>0 && ligne<taille-1)
			{
					if(colonne==0){colonne1=1;ligne0=1;ligne1=1;}
					else if(colonne>0 && colonne<taille-1){colonne1=1;colonne0=1;ligne1=1;ligne0=1;}
					else if(colonne==taille-1){colonne0=1;ligne1=1;ligne0=1;}
			}
			else if(ligne==taille-1){
					if(colonne==0){colonne1=1;ligne0=1;}
					else if(colonne>0 && colonne<taille-1){colonne1=1;colonne0=1;ligne0=1;}
					else if(colonne==taille-1){colonne0=1;ligne0=1;}
			}
			if(ligne0==1){
					int[][] t=grille.copier();
					t[ligne][colonne]=t[ligne-1][colonne];
					t[ligne-1][colonne]=0;
					GrilleSuivante .add(new Grille(t));
			}
			if(ligne1==1){
					int[][] t=grille.copier();
					t[ligne][colonne]=t[ligne+1][colonne];
					t[ligne+1][colonne]=0;
					GrilleSuivante .add(new Grille(t));
			}
			if(colonne1==1){
					int[][] t=grille.copier();
					t[ligne][colonne]=t[ligne][colonne+1];
					t[ligne][colonne+1]=0;
					GrilleSuivante .add(new Grille(t));
			}
			if(colonne0==1)
			{
					int[][] t=grille.copier();
					t[ligne][colonne]=t[ligne][colonne-1];
					t[ligne][colonne-1]=0;
					GrilleSuivante .add(new Grille(t));
			}
			return GrilleSuivante;
	}

}