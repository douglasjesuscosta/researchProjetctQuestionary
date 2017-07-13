package controller;

import java.util.List;

import model.Model;
import model.Resposta;

public class ManterRespostas extends CrudController{

	@Override
	public Model getTableObject() {
		return new Resposta();
	}

	@Override
	public List listAllModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
