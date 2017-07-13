/*package desativados;

import java.awt.Button;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.naming.ContextAccessController;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;

import controller.ManterTexto;
import intefaces.ICrudController;


public class TextFormComposer extends SelectorComposer<Component> {
	// wire components
	@Wire
	Textbox titulo;
	@Wire
	Textbox autor;
	@Wire
	Textbox conteudo;
	@Wire
	Textbox conteudoPrivado;
	@Wire
	Datebox data;
	@Wire
	Checkbox meioFisico;
	@Wire
	Checkbox meioDigital;
	@Wire
	Textbox tipo;
	@Wire
	Button saveText;
	@Wire
	Button reloadText;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	//data.setFormat("yyyy-MM-dd");
	}
	
	@Listen("onClick=#saveText")
	public void doSaveText() throws SQLException, ClassNotFoundException{
		   ManterTexto manterTexto = new ManterTexto();
		   manterTexto.setAction("insert");
		   manterTexto.setVariables(buildVariablesMap());
		   manterTexto.executeAction();
		   Clients.showNotification("Texto Salvo");
		   
	}
	
	public HashMap<String, String> buildVariablesMap(){
		HashMap<String, String> variables = new HashMap<String, String>();
		variables.put("data", data.getText().toString());
		variables.put("autor", autor.getText());
		variables.put("titulo", titulo.getText());
		variables.put("meio_fisico", String.valueOf(meioFisico.isChecked()));
		variables.put("meio_digital", String.valueOf(meioDigital.isChecked()));
		variables.put("tipo", tipo.getText());
		variables.put("info_privada", conteudoPrivado.getText());
		variables.put("conteudo", conteudo.getText());
		variables.put("classificacao_id", "1");
		
		return variables;
	}
	
	public void teste(){
	}
}*/
