package hanlp.model;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

import hanlp.route.RouteData;
import hanlp.format.Format;
import hanlp.exception.*;

public class Model{
	
	public Format runMethod(RouteData json, String method) throws HanlpException {
		
		Method runMethod = null;
		Format data = null;

		Class model = getClass();
		try{

			Class[] argsClass = new Class[1];
			argsClass[0] = JSONObject.class;
			runMethod = model.getMethod(method, argsClass);

		}catch(NoSuchMethodException e){
			throw new MethodErrorException("URI METHOD ERROR");
		}
		
		try{
			Object[] args = new Object[1];	
			args[0] = json.getData();
			data = (Format)runMethod.invoke(model.newInstance(), args);
		}catch(Exception e){
			if(e instanceof InvocationTargetException){  
                throw (HanlpException)(((InvocationTargetException)e).getTargetException());  
            }else{  
				throw new MethodInvokeException(e.getMessage());
            }  
		}
		
		return data;
	}
	
}
