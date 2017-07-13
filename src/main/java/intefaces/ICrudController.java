package intefaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import communication.Pack;
import model.Model;
import util.Return;

public interface ICrudController {

	public Return executeAction() throws SQLException, ClassNotFoundException;
	public IPack executeAction(Pack pack);
	public List<IModel> listAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
	public void setVariables(HashMap<String, String> variables);
	public void setAction(String action);
}
