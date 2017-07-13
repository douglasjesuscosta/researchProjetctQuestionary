package validators;

import java.lang.reflect.Field;

import Reflection.ReflectionClass;
import annotation.Column;
import intefaces.IModel;
import util.Return;

public class GenericValidator {
	
	public static Return validate(IModel object, Return retorno){
		Class<?> metaClass = object.getClass();
		Field[] fields = metaClass.getDeclaredFields();
		retorno.setSucesso();
		
		for(int i =0; i < fields.length; i++){
			Column column = fields[i].getAnnotation(Column.class);
			if(column != null){
				Object value = ReflectionClass.getFieldValueByDatabaseOrModelName(object, fields[i].getName());
				
				if((value == null || value.equals("")) && column.obrigatory()==true){
					retorno.addMensagem("Campo " + column.nameOnView()  + " deve ser preenchido");
					retorno.setFracasso();
				}
			}
		}
		return retorno;
	}
	
}
