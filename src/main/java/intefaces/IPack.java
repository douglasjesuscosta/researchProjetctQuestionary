package intefaces;

import java.util.HashMap;
import java.util.List;

import enums.Actions;
import model.Model;
import util.Return;

public interface IPack {
	
	public void setAction(Actions action);
	public Actions getAction();
	
	public void setModelObject(Model model);
	public IModel getModelObject();
	
	public void setReturn(Return a_return);
	public Return getReturn();
	
	public void setListModel(List<Model> listModel);
	public List<Model> getListModel();
	
	public void setSearchParamethers(HashMap<String, Object> searchParamethers);
	public HashMap<String, Object> getSearchParamethers();
		
}
