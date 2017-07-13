package desativados;
/*package composer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.callback.ConfirmationCallback;

import org.zkoss.bind.converter.sys.RadiogroupSelectedItemConverter;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.ext.Selectable;
import org.zkoss.zul.impl.MessageboxDlg;

import DAO.GenericDao;
import controller.CrudController;
import controller.ManterClassificacao;
import controller.ManterTexto;
import intefaces.ICrudController;
import model.Classification;
import model.Model;
import model.Texts;
import util.Return;

public class TextComposer extends SelectorComposer<Component>  implements Initiator {
     
	private static final long serialVersionUID = 1L;
	
	
	@Wire
    private Listbox textListBox;
	@Wire
	private Button reloadText;
	@Wire
	private String selectedConteudo;
	@Wire 
	private String selectedTitulo;
	@Wire
	private Texts selectedText;
	@Wire
	private Component selectedTextBlock;
	@Wire
	private Label selectedTextTitulo;
	@Wire
	private Label selectedTextAutor;
	@Wire
	private Label selectedTextTipo;
	@Wire
	private Textbox titulo;
	@Wire
	private Textbox autor;
	@Wire
	private Textbox conteudo;
	@Wire
	private Textbox conteudoPrivado;
	@Wire
	private Datebox data;
	@Wire
	private Checkbox meioFisico;
	@Wire
	private Checkbox meioDigital;
	@Wire
	private Textbox tipo;
	@Wire
	private Button saveText;
	@Wire
	private Button deleteText;
	@Wire 
	private Button searchText;
	@Wire
	private Textbox parameterBox;
	@Wire
	private Combobox classificacao; 
	@Wire
	private ListModel classificacaoModel = new ListModelList(ManterClassificacao.getInstance().listAllClassifications());
	@Wire
	private Classification selectedClassificacao = new Classification();
	@Wire
	private Radiogroup tipoRadiogroup;
	
	Comboitem cbi;
	ICrudController controller;
	
	
	public TextComposer() throws Exception{
	
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if(classificacao != null){
			classificacao.setModel(classificacaoModel);
		}
	}
			
	
   @Listen("onClick=#saveText")
	public void doSaveText() throws SQLException, ClassNotFoundException{
	   Return retorno = new Return();
	   
	   Texts object = buildObject();
	   ManterTexto manterTexto = new ManterTexto();
	   manterTexto.setCompleteObject(object);
	   manterTexto.setAction("insert");
	   retorno = manterTexto.executeAction();
	   if(retorno.getSucesso()){
		   Clients.showNotification("Texto Salvo"); 
	   }else{
		   Clients.showNotification(retorno.getMessage());
	   }
	}
	
    @Listen("onClick=#listAll")
   	public void doListAll() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    	ManterTexto manterTexto = new ManterTexto();
    	textListBox.setModel(new ListModelList<Model>(manterTexto.listAll()));
   	}
    
    @Listen("onClick=#searchText")
    public void doSearch() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    	ManterTexto manterTexto = new ManterTexto();
    	if(parameterBox.getText().equalsIgnoreCase("") || parameterBox == null){
    		doListAll();
    	}else{
    		textListBox.setModel(new ListModelList<Model>(manterTexto.setSearchParameter(parameterBox.getText())));
    	}
    }
	
	public HashMap<String, String> buildVariablesMap(){
		HashMap<String, String> variables = new HashMap<String, String>();
		variables.put("data", data.getText().toString());
		variables.put("autor", autor.getText());
		variables.put("titulo", selectedTitulo);
		variables.put("meio_fisico", String.valueOf(meioFisico.isChecked()));
		variables.put("meio_digital", String.valueOf(meioDigital.isChecked()));
		variables.put("tipo", tipo.getText());
		variables.put("info_privada", conteudoPrivado.getText());
		variables.put("conteudo", selectedConteudo);
		variables.put("classificacao_id", "1");
		
		return variables;
	}
    
    public List getList() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
    	ManterTexto manterTexto = new ManterTexto();
    	
    	return manterTexto.listAll();
    }
    
    @Listen("onClick=#deleteText")
    public void delete() throws ClassNotFoundException, SQLException{
    	
    	Model object = buildObject();
    	
        ManterTexto mt = new ManterTexto();
      
	    mt.setAction("delete");
	    mt.setCompleteObject(object);
	    Return retorno = mt.executeAction();
	    
	    if(retorno.getSucesso()){
	    	Clients.showNotification(retorno.getMessage());
	    }else{
	    	refreshDetailView();
	    	Clients.showNotification("Deletado com sucesso");
	    }
    }
    
    @Listen("onTextView = #textListBox")
    public void view(ForwardEvent evt){
    	
    	Map data = new HashMap();
    	data.put("composer2", this);
    	
    	Window window = (Window)Executions.createComponents(
                 		"frm_cadastro.zul", null, data);
    	setValues();
        window.doModal();
        
    }
    
    public HashMap<String, Integer> getDate(String date){
    	HashMap<String, Integer> dateMap = new HashMap<String, Integer>();
    	
    	String[] dateV = date.split("-");
    	
    	dateMap.put("Y", Integer.parseInt(dateV[0]));
    	dateMap.put("M", Integer.parseInt(dateV[1]));
    	dateMap.put("D", Integer.parseInt(dateV[2]));
    	
    	return dateMap;
    }
  
    
    private void refreshDetailView() {
		//refresh the detail view of selected todo
		if(selectedText==null){
			textListBox.removeChild(textListBox.getSelectedItem());
		}
	}
    
    @Listen("onSelect = #textListBox")
    public void setSelectedText(){
    	
    	Set<Texts> selection = ((Selectable<Texts>)textListBox.getModel()).getSelection();
        if (selection!=null && !selection.isEmpty()){
             Texts selected = selection.iterator().next();
             this.selectedText = selected;    
        }
    }
    
    private void setValues(){
    	
    	this.titulo.setValue(this.selectedText.getTitle());
   
   	 	this.autor.setValue(this.selectedText.getAuthor());
   	 	this.conteudo.setValue(this.selectedText.getContent());
   	 	this.conteudoPrivado.setValue(this.selectedText.getPrivateContent());
   	 	this.data.setValue(this.selectedText.getDate());
   	 	this.meioFisico.setChecked(this.selectedText.getPhysical());
   	 	this.meioDigital.setChecked(this.selectedText.getDigital());
   	 	//this.tipoRadiogroup.setSelectedItem(this.selectedText.getTipo());
   	 	setSelectedClassificacao(this.selectedText.getClassification());
   	 	//this.classificacao.setSelectedIndex(Integer.parseInt(this.selectedText.getClassificacao().getId()));
   	 	//this.classificacao.setSelectedItem(getItemByValue(this.classificacao, selectedText.getClassificacao()));
   	 	//selectedClassificacao = selectedText.getClassificacao();
   	 	//((ListModelList)this.classificacao.getModel()).addToSelection(selectedText.getClassificacao());
   	 	
    }
    
    public Texts buildObject() throws ClassNotFoundException, SQLException{
    	Texts umTexto = new Texts();
    	this.data.setFormat("DD-MM-YYYY");
    	
    	if(classificacao != null){
    		Classification classificacao;
    		classificacao = (Classification) this.classificacao.getSelectedItem().getValue();
    		umTexto.setClassification(classificacao);
    	}else{
    		umTexto.setClassification(new Classification());
    	}
    	
    	if(selectedText == null){
    		umTexto.setId(null);
    	}else{
    		umTexto.setId(selectedText.getId());
    	}
    	umTexto.setAuthor(this.autor.getValue());
    	umTexto.setTitle(this.titulo.getValue());
    	umTexto.setContent(this.conteudo.getValue());
    	umTexto.setPrivateContent(this.conteudoPrivado.getValue());
    	umTexto.setDate(new Date(this.data.getValue().getTime()));
    	umTexto.setDigital(this.meioDigital.isChecked());
    	umTexto.setPhysical(this.meioFisico.isChecked());
    	umTexto.setKind(tipoRadiogroup.getSelectedItem().getValue());
    	
    	return umTexto;
    }
    
	public Comboitem getItemByValue(Combobox box, Model value) throws IllegalArgumentException{
		for(Comboitem item : box.getItems()){
			if(item.getValue().equals(value))
				return item;
		}
			
		throw new IllegalArgumentException(value+" wasn't found in Combobox store");
	}

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		Executions.sendRedirect("login.zul");
		
	}

	public Classification getSelectedClassificacao() {
		return selectedClassificacao;
	}

	public void setSelectedClassificacao(Classification selectedClassificacao) {
		this.selectedClassificacao = selectedClassificacao;
	}
 
}*/