package bowling;

import java.util.ArrayList;

public class Tour {
	private int numTour;
	private ArrayList<Lancer> lesLancers = new ArrayList<>();
	private int numCoup = 1;
	private boolean estFini = false;

	public Tour(int numTour) {
		this.numTour = numTour;
	}

	public boolean estUnStrike() {
		return lesLancers.size() == 1 && lesLancers.get(0).getNbreDeQuillesAbattues() == PartieMonoJoueur.nbreQuilles;
	}

	public boolean estUnSpare() {
		return numCoup == 2 && getScoreLancer(1) + getScoreLancer(2) == PartieMonoJoueur.nbreQuilles && numTour != PartieMonoJoueur.nbreTours;
	}

	public boolean estFini() {
		return estFini;
	}

	public int getScore() {
		int score = 0;
		for (Lancer lancer : lesLancers) {
			score += lancer.getNbreDeQuillesAbattues();
		}
		return score;
	}

	public int getScoreLancer(int numLancer) {
		if (numLancer >= 1 && numLancer <= lesLancers.size()) {
			return lesLancers.get(numLancer - 1).getNbreDeQuillesAbattues();
		} else {
			return 0;
		}
	}

	public int getProchainNumCoup() {
		return numCoup;
	}

	public boolean enregistreLancer(Lancer lancer) {
		if (numCoup == 1) {
			lesLancers.add(lancer);
			if (getScore() == PartieMonoJoueur.nbreQuilles && numTour != PartieMonoJoueur.nbreTours) {
				estFini = true;
			} else if (getScore() == PartieMonoJoueur.nbreQuilles) {
				numCoup += 1;
			} else {
				numCoup++;
			}
		} else if (numCoup == 2) {
			lesLancers.add(lancer);
			estFini = true;
			if (numTour == PartieMonoJoueur.nbreTours && !(getScore() < PartieMonoJoueur.nbreQuilles)) {
				numCoup++;
				estFini = false;
			}
		} else if (numCoup == 3) {
			lesLancers.add(lancer);
			estFini = true;
		}

		return !estFini;
	}
}	