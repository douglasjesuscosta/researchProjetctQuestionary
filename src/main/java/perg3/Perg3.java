package perg3;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

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

public class Perg3 extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(Perg3.class, "Perg3.js");
	private static final CssResourceReference MYPAGE_CSS = new CssResourceReference(Perg3.class, "Perg3.css");
	private static final CssResourceReference BOOTSTRAP = new CssResourceReference(Perg3.class, "/webapp/boots");
	

	public Perg3(final PageParameters parameters) {
		super(parameters);
		final IModel<String> selected;
		RadioGroup group;
		
		selected = new Model<String>();
		group = new RadioGroup("group", selected);
		
		group.add(new Radio("azul", new Model<String>("azul")));
		group.add(new Radio("laranja", new Model<String>("laranja")));
		group.add(new Radio("preto", new Model<String>("preto")));
		group.add(new Radio("verde", new Model<String>("verde")));
		group.add(new Radio("vermelho", new Model<String>("vermelho")));
		
		//DEclaração do formulário
		Form form = new Form("form"){
		    @Override
		    protected void onSubmit() {
		            System.out.println("PASSEI.");
		            System.out.println(selected.getObject());
		            parameters.set("perg3", selected.getObject());
		            setResponsePage(Perg4.class,parameters);
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
