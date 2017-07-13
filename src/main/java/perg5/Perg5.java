package perg5;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import Perg6.Perg6;
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

public class Perg5 extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(Perg5.class, "Perg5.js");
	private static final CssResourceReference MYPAGE_CSS = new CssResourceReference(Perg5.class, "Perg5.css");
	private static final CssResourceReference BOOTSTRAP = new CssResourceReference(Perg5.class, "/webapp/boots");
	

	public Perg5(final PageParameters parameters) {
		super(parameters);
		final IModel<String> selected;
		RadioGroup group;
		
		selected = new Model<String>();
		group = new RadioGroup("group", selected);
		
		group.add(new Radio("org-abas", new Model<String>("org-abas")));
		group.add(new Radio("org-topicos", new Model<String>("org-topicos")));
		group.add(new Radio("org-tempopublicacao", new Model<String>("org-tempopublicacao")));
		group.add(new Radio("org-topicos-e-cores", new Model<String>("org-topicos-e-cores")));
		group.add(new Radio("org-importancia", new Model<String>("org-importancia")));
		
		//DEclaração do formulário
		Form form = new Form("form"){
		    @Override
		    protected void onSubmit() {
		            System.out.println("PASSEI.");
		            System.out.println(selected.getObject());
		            parameters.set("perg5", selected.getObject());
		            setResponsePage(Perg6.class,parameters);
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
