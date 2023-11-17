package bowling;

import java.util.ArrayList;

public class Tour {
	//coucou
	private ArrayList<Lancer> lesLancers;
	
	private int boulelancee = 0;

	public Tour(ArrayList<Lancer> lesLancers) {
		this.lesLancers = lesLancers;
		
	}
	public Tour() {
		this.lesLancers = new ArrayList<Lancer>();
	}

	public int scoreTour() {
		int score = 0;
		for (Lancer l : lesLancers) {
			score += l.getnbreQuillesTombees();
		}
		return score;
	}

	public Boolean estUnSpare() {
		Boolean res = false;
		if (scoreTour() == 10 && lesLancers.size() == 2)
			res = true;
		return res;
	}

	public Boolean estUnStrike() {
		Boolean res = false;
		if (scoreTour() == 10 && lesLancers.size() == 1)
			res = true;
		return res;
	}

	public ArrayList<Lancer> getLancer() {
		return lesLancers;
	}

	public int nombreBoulesLancees() {
		int b = 0;
		if (this.estUnStrike()) {
			b = 1;
		} else if (lesLancers.size() == 1) {
			b = 1;
		} else {
			b = 2;
		}
		return b;
	}
	public boolean estTerminée(){
		if (estUnStrike() || nombreBoulesLancees() ==2){
			return true;
		}else{
			return false;
		}
	}
	
	public int nbLance(){
		return lesLancers.size();
	}
}
	