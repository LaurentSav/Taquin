import java.util.ArrayList;
import java.util.Scanner; 

public class Main {

	public static void main(String[] args) {

		Scanner scan= new Scanner(System.in);
		System.out.println("\n");
		System.out.println("Veuillez écrire un numero de puzzle à résoudre (attention le numéro doit être sous la forme XX, exemple: 01, 08 ou 15) : ");
		String text= scan.nextLine();
		
	
		Grille etatInitial=Solveur.chargerFichier("puzzles/puzzle"+ text +".txt");
		System.out.println("\nGrille du puzzle " + text + " :");
		System.out.println(etatInitial);
		System.out.println("\n***************************************");
		System.out.println("\nTraitement en Cours...");
		System.out.println("\n***************************************");
		Solveur solver=new Solveur();
		Noeud AstarNoeud=solver.algoAstar(etatInitial);

		int j = 0;
	
		if(AstarNoeud!=null) {
			Grille AstarN = new Grille(AstarNoeud.getGrille().getGrille());
			for(Grille deux : solver.cheminComplet(AstarNoeud)){
				j++;
				System.out.println("\nEtape " + j + " :");
				System.out.println(deux);
			}
			System.out.println("\n***************************************");
			System.out.println("\nFin du Traitement");
			System.out.println("\n***************************************");
			System.out.println("\nNoeud final :  \n" + AstarN);
			System.out.println("Nombre d'étape intermédiaire : " + AstarNoeud.g());
			
		}else{
			System.out.println("\nCette grille n'a pas de solution");
		}
	}
}
