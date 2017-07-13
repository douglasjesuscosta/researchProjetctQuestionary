/*package tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.core.ConnectionFactory;
import org.postgresql.core.Logger;
import org.postgresql.core.ProtocolConnection;

import DAO.GenericDao;
import conection.ConnectionBd;

import junit.framework.TestCase;
import model.Classificacao;
import model.Table;
import model.Textos;
import util.PropertiesFactory;

public class Tests{ //extends TestCase{
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		try {
			//testDao();
			testListAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testListAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Textos umTextos = new Textos();
		ArrayList<Table<?>> listaTextos = GenericDao.getGenericDao().listAll(umTextos);
	}

	public static void testDao() throws SQLException, ClassNotFoundException{
		Textos umTexto = new Textos();
		Classificacao umaClassificacao = new Classificacao();
		umaClassificacao .setId("1");
		
		umTexto.setAutor("Douglas");
		umTexto.setClasse("Textos");
		umTexto.setClassificacao(umaClassificacao);
		umTexto.setConteudo("aa");
		umTexto.setConteudoPrivado("AA");
		umTexto.setData("2016-06-05");
		umTexto.setId("11");
		umTexto.setMeioDigital(true);
		umTexto.setMeioFisico(false);
		umTexto.setTipo("ttt");
		umTexto.setTitulo("o bezouro suco");
		
		GenericDao dao = GenericDao.getGenericDao();
		dao.insert(umTexto);
	}
	
	
	
}*/
