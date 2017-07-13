package viewController;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.management.RuntimeErrorException;

import annotation.Table;
import communication.Pack;
import controller.CrudController;
import intefaces.IConnection;
import intefaces.IController;
import intefaces.ICrudController;
import intefaces.IModel;
import intefaces.IPack;
import model.Model;



@WebService(targetNamespace = "http://viewController/", portName = "ViewControllerPort", serviceName = "ViewControllerService")
public class ViewController {
	
	@WebMethod(operationName = "executeAction", action = "urn:ExecuteAction")
	@WebResult(name = "return")
	public static Pack executeAction(Pack pack){
		
		
		Model model = pack.getModelObject();
		Class<?> metaClass = model.getClass();
		Table t = metaClass.getAnnotation(Table.class);
		String controller = "controller." + t.useCase();
		
		try {
			System.out.println(controller);
			CrudController icontrol = (CrudController) Class.forName(controller).newInstance();
			pack = (Pack) icontrol.executeAction(pack);
			
		} catch (Exception e){
			throw new RuntimeException("Failed to find controller: " + controller + e.getMessage());
		}
		return pack;
	}
}
