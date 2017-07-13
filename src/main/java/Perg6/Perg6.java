package Perg6;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import Finalizacao.Finalizacao;
import model.Resposta;
import perg4.Perg4;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class Perg6 extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(Perg6.class, "Perg6.js");
	private static final CssResourceReference MYPAGE_CSS = new CssResourceReference(Perg6.class, "Perg6.css");
	private static final CssResourceReference BOOTSTRAP = new CssResourceReference(Perg6.class, "/webapp/boots");
	

	public Perg6(final PageParameters parameters) {
		super(parameters);
		final IModel<String> selected;
		RadioGroup group;
		
		selected = new Model<String>();
		group = new RadioGroup("group", selected);
		
		group.add(new Radio("frt_semcomunicacao", new Model<String>("frt_semcomunicacao")));
		group.add(new Radio("frc_semcomunicacao", new Model<String>("frc_semcomunicacao")));
		group.add(new Radio("neutro", new Model<String>("neutro")));
		group.add(new Radio("frc_comunicacao", new Model<String>("frc_comunicacao")));
		group.add(new Radio("frt_comunicacao", new Model<String>("frt_comunicacao")));
		
		//DEclaração do formulário
		Form form = new Form("form"){
		    @Override
		    protected void onSubmit() {
		            System.out.println("PASSEI.");
		            System.out.println(selected.getObject());
		            parameters.set("perg6", selected.getObject());
		            setResponsePage(Finalizacao.class,parameters);
		    }
		};
		
		form.add(group);
		add(form);

		// TODO Add your page's components here

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
