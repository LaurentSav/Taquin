
public class Grille{

	/* Attribut de la classe Grille */
	/**
	—grille : correspond `a la grille du puzzle. Elle est implementee avec une matrice
	carrée d’entiers (nombre de lignes = nombre de colonnes).
	—taille :represente la taille du puzzle. Par exemple, le puzzle de la figure 1 a une taille ́egale a 3.
	—ligne0 :represente l’indice de la ligne ou se trouve la case vide (la case 0).
	—colonne0 :represente l’indice de la colonne ou se trouve la case vide (la case 0).
	**/

  private  int[][] grille;
	private int taille;
	private int ligne0;
	private int colonne0;
    
	/* Constructeur */	
	public Grille(int[][] g){
		this.grille = g;
		this.taille = g.length;

    for(int i=0;i< g.length ; i++){
    		for(int j=0; j< g.length ; j++){
    			if (g[i][j]==0){
    				this.ligne0=i;
    				this.colonne0=j;
    			}
    		}
    	}
	}

	/* Méthodes */

	/* function getGrille() : Retourne la grille */

	public int[][] getGrille(){
		return this.grille;
	}

	/* function getTaille() : Retourne la Taille de la grille */

	public int getTaille(){
		return this.taille;
	}

	/* function getLigne0() : Retourne l'indice de la ligne qui contient 0 */

	public int getLigne0(){
		return this.ligne0;
	}

	/* function getColonne0() : Retourne l'indice de la colonne qui contient 0 */
	public int getColonne0(){
		return this.colonne0;
	}

	/* function getValeur(int i,int j) : Retourne la valeur de la case qui se trouve à la colonne j et ligne i */
	public int getValeur(int i,int j){
		return this.grille[i][j];
	}

	/* function copier() : Retourne une copie de la grille de l’instance */
	public int[][] copier(){
		int [][] NouvGrille = new int [this.taille][this.taille];
        for(int i = 0; i < this.taille; i++) {
            for(int j = 0; j < this.taille; j++) {
                NouvGrille[i][j] = this.grille[i][j];
            }
        }
        return NouvGrille;
	}

	/* function equals(Object y) : Retourne une valeur true si la grille est bien égale à une autre grille y.
		 														 Retourne une valeur false si la grille n'a pas la même taille ou les mêmes valeurs par case que la grille y ou tout simplement si y n'est pas une grille.
																	
	*/
	public boolean equals(Object y){

		if(y instanceof Grille){
			Grille deux = (Grille) y;
			if (deux.getTaille() != this.getTaille()){
				return false;
			}else{
				for (int i = 0; i < this.taille;i++){
					for (int j = 0; j < this.taille;j++){
						if(deux.getValeur(i,j) != this.getValeur(i,j)){
							return false;
						}
					}
				}
				return true;
			}

		}
		return false;
	}
	
	/* function toString() : Affichage */
	public String toString(){
		String res = "\n";
		for (int i = 0; i < this.taille; i++) {
				for (int j = 0; j < this.taille; j++) {
						res = res + this.getValeur(i, j) + "|";
				}
				res = res + "\n";
		}

		return res;
	}
}
