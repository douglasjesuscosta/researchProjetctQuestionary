package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Return {
	private ArrayList<String> mensagens;
	private HashMap<String, String> mensagesFields;
	private boolean sucesso;
	
	public Return(){
		mensagens = new ArrayList<String>();
		mensagesFields = new HashMap<String, String>();
	}
	
	public void setSucesso(){
		sucesso = true;
	}
	
	public void setFracasso(){
		sucesso = false;
	}
	
	public Iterator<String> getMensagens(){
		return mensagens.iterator();
	}
	
	public String getMessage(){
		String message = "";
		for(int i =0; i <mensagens.size(); i++){
			message += mensagens.get(i) + "\n";
		}
		return message;
	}
	
	public HashMap<String, String>getMensagensFiels(){
		return mensagesFields;
	}
	
	public boolean getSucesso(){
		return sucesso;
	}
	
	public void addMensagem(String mensagem){
		mensagens.add(mensagem); 
	}
	
	public void addMensagem(String field, String mensagem){
		mensagesFields.put(field, mensagem);
	}

	public String getAllMessages() {
		String ret = "";
		
		for(int i =0; i < mensagens.size(); i++){
			ret += mensagens.get(i) + "/n";
			
		}
		return ret;
	}
}
