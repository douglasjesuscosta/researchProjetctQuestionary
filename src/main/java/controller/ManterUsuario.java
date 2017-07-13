package controller;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOUsuario;
import communication.Pack;
import enums.Actions;
import intefaces.IPack;
import model.Model;
import util.Return;

public class ManterUsuario extends CrudController{
	
	@Override
	public Pack executeAction(Pack pack){
		pack = super.executeAction(pack);
		Return retorno = pack.getReturn();
		
		if(pack.getAction() == Actions.AUTENTIFY){
			DAOUsuario a_dao;
			try {
				a_dao = new DAOUsuario();
				retorno = a_dao.autentificar(pack.getModelObject(), pack);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		pack.setReturn(retorno);
		return pack;

}

	@Override
	public Model getTableObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listAllModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
