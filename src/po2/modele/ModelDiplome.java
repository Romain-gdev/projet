package po2.modele;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class ModelDiplome extends AbstractListModel<Diplome> {
	List<Diplome> donnees;
	
	public ModelDiplome(List<Diplome> liste) {
		donnees = liste;
	}
	
	public ModelDiplome(){
		this(new ArrayList<>());
	}

	public List<Diplome> getDonnees(){
		return donnees;
	}
	
	public void addElement(Diplome dp){
		donnees.add(dp);
		fireContentsChanged(dp, getSize() - 1, getSize() - 1);
	}
	
	public void removeElement(Diplome dp){
		int index = donnees.indexOf(dp);
		donnees.remove(dp);
		fireContentsChanged(dp, index, index);
	}
	
	@Override
	public Diplome getElementAt(int index){
		return donnees.get(index);
	}
	
	@Override
	public int getSize(){
		return donnees.size();
	}
}