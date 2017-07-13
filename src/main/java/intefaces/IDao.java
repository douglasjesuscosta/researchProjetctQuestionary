package intefaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.GenericDao;
import model.Model;

public interface IDao {
	
	public void insert(Model<?> object) throws SQLException;
	public void delete(Model<?> object);
	public Model getObject(Model table);
	public String[] constructStringValues(Model object);
	public String getNextId(Model<?> tableObject) throws SQLException;
	public ArrayList<Model<?>> listAll(Model<?> tableObject) throws SQLException, InstantiationException, IllegalAccessException;
	public Model<?> montarObjeto(ResultSet rs, Model<?> object) throws Exception;
	public boolean exist(Model<?> object);
	public void update(Model<?> object) throws SQLException;
	public ArrayList<Model<?>> search(String search_criteria, Model object);
	
}
