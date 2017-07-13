package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.GenericDao;
import communication.Pack;
import enums.Actions;
import intefaces.ICrudController;
import intefaces.IDao;
import intefaces.IModel;
import intefaces.IPack;
import model.Model;
import util.Return;

public abstract class CrudController implements ICrudController{
	
	private HashMap<String, String> variables;
	private String action; 
	private Model filledObject;
	
	public Return executeAction(){
		Return retorno = new Return();
		try{
			if(action.equals("insert")){
				retorno = executeActionInsert();
			}else if(action.equals("delete")){
				retorno = executeActionDelete();
			}
		}catch(Exception e){
			retorno.addMensagem(e.getMessage());
		}
		return retorno;
	}
	
	public Pack executeAction(Pack pack){
		Return retorno = new Return();
		try{
			if(pack.getAction() == Actions.INSERT){
				retorno = executeActionInsert(pack.getModelObject());
			}else if(pack.getAction() == Actions.DELETE){
				retorno = executeActionDelete(pack.getModelObject());
			}else if(pack.getAction() == Actions.SELECT){
				pack = (Pack) executeActionSelect(pack);
			}
		}catch (Exception e) {
			retorno.setFracasso();
			retorno.addMensagem("Erro: " + e.getMessage());
		}
		
		pack.setReturn(retorno);
		return pack;
	}
	
	public Return executeActionInsert(IModel model){
		Return retorno = new Return();
		try{
			GenericDao dao = GenericDao.getGenericDao();
			
			if(model != null){
				if(model.getPk() == null){
					model.setPk(dao.getNextId(model));
					retorno = dao.insert(model);
				}else{
					retorno = dao.update(model);
				}
			}
		}catch(Exception e){
			retorno.setFracasso();
			retorno.addMensagem(e.getMessage());
		}
		return retorno;
	}
	

	public Return executeActionInsert(){
		
			Return retorno = new Return();
		try{
			GenericDao dao = GenericDao.getGenericDao();
			
			if(filledObject != null){
				if(filledObject.getPk() == null){
					filledObject.setPk(dao.getNextId(filledObject));
					dao.insert(filledObject);
					retorno.setSucesso();
				}else{
					dao.update(filledObject);
					retorno.setSucesso();
				}
			}
		}catch(Exception e){
			retorno.setFracasso();
			retorno.addMensagem(e.getMessage());
		}
		return retorno;
	}
	
	public Return executeActionDelete() throws SQLException, ClassNotFoundException{
		Return retorno = new Return();
		if(!(filledObject == null)){
			GenericDao.getGenericDao().delete(filledObject);
		}else{
			retorno.setFracasso();
			retorno.addMensagem("ERRO NO PREENCHIMENTO DO OBJETO");
		}
		return retorno;
	}
	
	public Return executeActionDelete(IModel object) throws SQLException, ClassNotFoundException{
		Return retorno = new Return();
		if(!(object == null)){
			GenericDao.getGenericDao().delete(object);
			retorno.setSucesso();
		}else{
			retorno.setFracasso();
			retorno.addMensagem("ERRO NO PREENCHIMENTO DO OBJETO");
		}
		return retorno;
	}
	
	public IPack executeActionSelect(IPack pack){
		Return retorno = new Return();
		IModel object = pack.getModelObject();
		HashMap searchParamethers = pack.getSearchParamethers();
		List<Model> listModel = pack.getListModel();
		
		try {
			if(searchParamethers == null){
				listModel = this.listAllModel();
				retorno.setSucesso();
			}else{
				listModel = (List<Model>)(List<?>) GenericDao.getGenericDao().search(searchParamethers, object);
			}
		} catch (Exception e) {
			retorno.setFracasso();
			retorno.addMensagem("<EXECUTEACTIONSELET> Erro ao buscar lista da entidade: "+ e.getMessage());
		}
		pack.setReturn(retorno);
		pack.setListModel(listModel);
		return pack;
	}

	public void setAction(String action){
		this.action = action;
	}
	
	public void setVariables(HashMap<String, String> variables){
		this.variables = variables;
	}
	
	public abstract Model getTableObject();
	

	public List<IModel> listAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return (List<IModel>) GenericDao.getGenericDao().listAll(getTableObject());
	}
	
	public void setCompleteObject(Model filledObject){
		this.filledObject = filledObject;
	}
	
	public abstract List listAllModel();
}
