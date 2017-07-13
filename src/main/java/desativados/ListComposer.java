package desativados;
/*package composer;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.ext.Selectable;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import controller.ManterTexto;
import model.Model;
import model.Texts;
import util.Return;

public class ListComposer extends SelectorComposer<Component> {
     
	private static final long serialVersionUID = 1L;

	@Wire
    private Listbox textListBox;
	@Wire
	private Button reloadText;
	@Wire
	private String conteudo;
	@Wire 
	private String titulo;
	@Wire
	private Component detailBox;
	@Wire
	private Label tituloLabel;
	@Wire 
	private Label conteudoLabel;
	@Wire
	private Texts selectedItem;
	@Wire
	Component selectedTextBlock;
	@Wire
	Label selectedTextTitulo;
	@Wire
	Label selectedTextAutor;
	@Wire
	Label selectedTextTipo;
	
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    
    	super.doAfterCompose(comp);
    	ManterTexto manterTexto = new ManterTexto();
    	textListBox.setModel(new ListModelList<Model>(manterTexto.listAll()));
    }
    
    public List getList() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
    	ManterTexto manterTexto = new ManterTexto();
    	
    	return manterTexto.listAll();
    }
    
    @Listen("onTextDelete = #textListBox")
    public void delete(ForwardEvent evt){
    		
    	Button btn = (Button)evt.getOrigin().getTarget();
        Listitem litem = (Listitem)btn.getParent().getParent();

        Model object = litem.getValue();

        ManterTexto mt = new ManterTexto();
        
        Messagebox.show("Deseja realizar a exclusão");
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
    
    @Listen("onTextUpdate = #textListBox")
    public void update(ForwardEvent evt){
    	Center center = null;
    		
    	Button btn = (Button)evt.getOrigin().getTarget();
        Listitem litem = (Listitem)btn.getParent().getParent();

        Model<?> object = litem.getValue();
        
        Executions.createComponents("cadastroViewModel.zul",null, object.getVariables());
		
    }
    
    private void refreshDetailView() {
		//refresh the detail view of selected todo
		if(selectedItem==null){
			textListBox.removeChild(textListBox.getSelectedItem());
		}else{
			showDetail();
		}
	}
    
    @Listen("onSelect = #textListBox")
    public void showDetail(){
        detailBox.setVisible(true);
         
        Set<Texts> selection = ((Selectable<Texts>)textListBox.getModel()).getSelection();
        if (selection!=null && !selection.isEmpty()){
            Texts selected = selection.iterator().next();
            tituloLabel.setValue(selected.getTitle());
            conteudoLabel.setValue(selected.getContent());
        }
    }
    
}*/