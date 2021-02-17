package po2.modele;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class ModelCandidat extends AbstractListModel<Candidat> {
	List<Candidat> donnees;
	
	public ModelCandidat(List<Candidat> liste){
		donnees = liste;
	}
	
	public ModelCandidat(){
		this(new ArrayList<>());
	}
	
	public List<Candidat> getDonnees(){
		return donnees;
	}
	
	public void addElement(Candidat cand){
		donnees.add(cand);
		fireContentsChanged(cand, getSize()-1, getSize()-1);
	}
	
	public void removeElement(Candidat cand){
		int index = donnees.indexOf(cand);
		donnees.remove(cand);
		fireContentsChanged(cand, index, index);
	}
	
	public void setDonnees(List<Candidat> liste) {
		donnees = liste;
	}
	
	@Override
	public Candidat getElementAt(int index){
		return donnees.get(index);
	}
	
	@Override
	public int getSize(){
		return donnees.size();
	}
}