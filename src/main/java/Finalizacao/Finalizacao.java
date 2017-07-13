package Finalizacao;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.string.StringValue;

import communication.Pack;
import enums.Actions;
import intefaces.IPack;
import model.Resposta;
import perg4.Perg4;
import util.Return;
import viewController.ViewController;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class Finalizacao extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(Finalizacao.class, "Finalizacao.js");
	private static final CssResourceReference MYPAGE_CSS = new CssResourceReference(Finalizacao.class, "Finalizacao.css");
	private static final CssResourceReference BOOTSTRAP = new CssResourceReference(Finalizacao.class, "/webapp/boots");
	

	public Finalizacao(final PageParameters parameters) {
		super(parameters);
		Resposta resposta = preparaEntidade(parameters);
		Return retorno = new Return();
		
		Pack pacote = new Pack();
		pacote.setModelObject(resposta);
		pacote.setAction(Actions.INSERT);
		pacote.setReturn(retorno);
		
		ViewController.executeAction(pacote);
		
		

    }
	
	private Resposta preparaEntidade(PageParameters parameters){
		StringValue resp1 = parameters.get("perg1");
		StringValue resp2 = parameters.get("perg2");
		StringValue resp3 = parameters.get("perg3");
		StringValue resp4 = parameters.get("perg4");
		StringValue resp5 = parameters.get("perg5");
		StringValue resp6 = parameters.get("perg6");
		
		Resposta resposta = new Resposta();
		resposta.setRespPerg1(resp1.toString());
		resposta.setRespPerg2(resp2.toString());
		resposta.setRespPerg3(resp3.toString());
		resposta.setRespPerg4(resp4.toString());
		resposta.setRespPerg5(resp5.toString());
		resposta.setRespPerg6(resp6.toString());
		
		return resposta;
	}
	
	public void verify(IModel<String> selected){
		System.out.println();
	}
	
	
	@Override
	public void renderHead(IHeaderResponse response) {
	  response.render(JavaScriptReferenceHeaderItem.forReference(MYPAGE_JS));
	  response.render(CssReferenceHeaderItem.forReference(MYPAGE_CSS));
	}

}
