package bowling;

import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	public static final int nbreTours = 10;
	public static final int nbreQuilles = 10;
	private int numTour = 1;
	private ArrayList<Tour> laPartieMono = new ArrayList<>();

	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		for (int i = 1; i <= nbreTours; i++) {
			laPartieMono.add(new Tour(i));
		}
	}



	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) throw new IllegalStateException("la partie est terminée !");

		Lancer lancer = new Lancer(nombreDeQuillesAbattues);
		boolean continuerTour = laPartieMono.get(numTour - 1).enregistreLancer(lancer);

		if (!continuerTour) {
			if (numTour < nbreTours) numTour++;
		}

		return continuerTour;
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		int scoreTotal = 0;

		for (int i = 0; i < nbreTours - 1; i++) {
			Tour tour = laPartieMono.get(i);
			scoreTotal += tour.getScore();

			if (tour.estUnSpare()) {
				scoreTotal += laPartieMono.get(i + 1).getScoreLancer(1);
			} else if (tour.estUnStrike()) {
				if (i + 1 == nbreTours - 1 || !laPartieMono.get(i + 1).estUnStrike()) {
					scoreTotal += laPartieMono.get(i + 1).getScore();
				} else {
					scoreTotal += laPartieMono.get(i + 1).getScoreLancer(1) + laPartieMono.get(i + 2).getScoreLancer(1);
				}
			}
		}



		Tour dernierTour = laPartieMono.get(nbreTours - 1);
		scoreTotal += dernierTour.getScore();

		if (scoreTotal>300){

			scoreTotal=300;
		}
		return scoreTotal;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		boolean b=false;
		if(laPartieMono.get(nbreTours-1).estFini()) {
			b = true;
		}
		return b;


	}

	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if (estTerminee()) {
			numTour = 0;
		}
		return numTour;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if (estTerminee()) {
			return 0;
		} else if (numTour == nbreTours) {
			return laPartieMono.get(nbreTours - 1).getProchainNumCoup();
		} else {
			return laPartieMono.get(numTour).getProchainNumCoup();
		}
	}

}