package perg2;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import model.Resposta;
import perg3.Perg3;

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

public class Perg2 extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(Perg2.class, "Perg2.js");
	private static final CssResourceReference MYPAGE_CSS = new CssResourceReference(Perg2.class, "Perg2.css");
	

	public Perg2(final PageParameters parameters) {
		super(parameters);
		final IModel<String> selected;
		RadioGroup group;
		
		selected = new Model<String>();
		group = new RadioGroup("group", selected);
		
		group.add(new Radio("frt_textual", new Model<String>("frt_textual")));
		group.add(new Radio("frc_textual", new Model<String>("frc_textual")));
		group.add(new Radio("neutro", new Model<String>("neutro")));
		group.add(new Radio("frc_ludico", new Model<String>("frc_ludico")));
		group.add(new Radio("frt_ludico", new Model<String>("frt_ludico")));
		
		//DEclaração do formulário
		Form form = new Form("form"){
		    @Override
		    protected void onSubmit() {
		            System.out.println("PASSEI.");
		            System.out.println(selected.getObject());
		            parameters.set("perg2", selected.getObject());
		            setResponsePage(Perg3.class,parameters);
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
