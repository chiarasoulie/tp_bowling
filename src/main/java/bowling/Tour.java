package bowling;

import java.util.ArrayList;

public class Tour {
	private ArrayList<Lancer> lesLancers;
	private int numero;
	
	public Tour (ArrayList<Lancer> lesLancers, int numero){
		this.lesLancers = lesLancers;
		this.numero = numero;
	}
	public int scoreTour (){
		int score=0;
		for (Lancer l:lesLancers){
			score+=l.getnbQuillesTombees();
		}
		return score;
	}
	public Boolean estUnSpare(){
		Boolean res = false;
		if (scoreTour()==10 && lesLancers.size()==2)
			res= true;
		return res;
	}

	public Boolean estUnStrike(){
		Boolean res = false;
		if (scoreTour()==10 && lesLancers.size()==1)
			res= true;
		return res;
	}
	public ArrayList<Lancer> getLancer() {
		return lesLancers;
	}
	
}
