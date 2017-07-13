package perg1;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.HttpSessionStore;
import org.apache.wicket.session.ISessionStore;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;


/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see projetopesquisa.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Perg1.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();	
		
		this.getResourceSettings().setResourcePollFrequency(null);
		configureBootstrap();

		// add your configuration here
	}
	
	private void configureBootstrap() { 
        
        final IBootstrapSettings settings = new BootstrapSettings(); 
        settings.useCdnResources(true); 
         
        //final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.BOOTSTRAP_THEME_MARKUP_ID); 
        
        //settings.setThemeProvider(themeProvider); 
         
        Bootstrap.install(this, settings); 
        //BootstrapLess.install(this);
    } 
	
	
	 protected ISessionStore newSessionStore()
	    {   
	        return new HttpSessionStore();
//	      return new SecondLevelCacheSessionStore(this, new InMemoryPageStore());
	    }
}
