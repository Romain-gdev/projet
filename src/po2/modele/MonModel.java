package po2.modele;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class MonModel extends AbstractListModel<DiplomeSup> {
	List<DiplomeSup> donnees;
	
	public MonModel(){
		donnees = new ArrayList<>();
	}
	
	public List<DiplomeSup> getDonnees(){
		return donnees;
	}
	
	public void addElement(DiplomeSup dpSup){
		donnees.add(dpSup);
		fireContentsChanged(dpSup, getSize() - 1, getSize() - 1);
	}
	
	@Override
	public DiplomeSup getElementAt(int index){
		return donnees.get(index);
	}
	
	@Override
	public int getSize(){
		return donnees.size();
	}
}