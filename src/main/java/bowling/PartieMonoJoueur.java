package bowling;

import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	private ArrayList<Tour> partie;


	public PartieMonoJoueur() {
		this.partie = new ArrayList<Tour>();
		Tour tour1 = new Tour();
		partie.add(tour1);
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon
	 * @throws IllegalStateException si la partie est terminée
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) throw new IllegalStateException("la partie est fini !");
		boolean b = false;
		if (nombreDeQuillesAbattues == 10) {
			b = false;

		} else if (this.numeroProchainLancer() == 2) {
			b = false;

		} else {
			b = true;
		}
		return b;

	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 *
	 * @return Le score du joueur
	 */
	public int score() {
		int score = 0;
		for (Tour t : partie) {
			score = score + t.scoreTour();
		}
		return score;
	}
	// coucouc

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		boolean b = false;
		if (partie.size() == 10) {
			b = true;
		}
		return b;

	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		int i = partie.size();
		if (estTerminee())
			i = 0;
		return i;
	}

	public Tour tourCourant() {
		return partie.get(partie.size() - 1);
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 * est fini
	 */
	public int numeroProchainLancer()
	{
		int numProchainLance = 0;
		if (numeroTourCourant() == 0)
			return 0;

		if (tourCourant().nbLance() == 0)
			numProchainLance = 1;

		if (numeroTourCourant() < 10) 
		{
			if (tourCourant().nbLance() == 1) 
			{

				if (tourCourant().estUnStrike()) 
				{
					numProchainLance = 1;
				} else 
				{
					numProchainLance = 2;
				}
			} else if (tourCourant().nbLance() == 2) 
			{
				numProchainLance = 1;
			}
		}
		return numProchainLance;


	}
	
}


