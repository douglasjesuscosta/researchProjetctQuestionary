package perg1;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar;
import model.Resposta;
import perg2.Perg2;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class Perg1 extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(Perg1.class, "Perg1.js");
	//private static final JavaScriptResourceReference JQUERY_JS = new JavaScriptResourceReference(Perg1.class, "jquery.js");
	private static final CssResourceReference MYPAGE_CSS = new CssResourceReference(Perg1.class, "Perg1.css");

	public Perg1(final PageParameters parameters) {
		super(parameters);
		final IModel<String> selected;
		RadioGroup group;
		
		selected = new Model<String>();
		group = new RadioGroup("group", selected);
		
		group.add(new Radio("pred_branca", new Model<String>("pred_branca")));
		group.add(new Radio("pred_amarela", new Model<String>("pred_amarela")));
		group.add(new Radio("pred_laranja", new Model<String>("pred_laranja")));
		group.add(new Radio("pred_verde", new Model<String>("pred_verde")));
		group.add(new Radio("pred_azul", new Model<String>("pred_azul")));
		
		//DEclaração do formulário
		Form form = new Form("form"){
		    @Override
		    protected void onSubmit() {
		            System.out.println("PASSEI.");
		            
		            if(selected.getObject() == null){
		            	
		            }else{
		            	System.out.println(selected.getObject());
			            
			            PageParameters param = new PageParameters();
			            param.add("perg1", selected.getObject());
			            
			            setResponsePage(Perg2.class, param);
		            }
		            
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
	//  response.render(JavaScriptReferenceHeaderItem.forReference(JQUERY_JS));
	}

}
