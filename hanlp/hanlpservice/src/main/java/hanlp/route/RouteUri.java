package hanlp.route;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import hanlp.model.Model;
import hanlp.format.Format;
import hanlp.format.Result;
import hanlp.exception.*;

public class RouteUri
{
	
	public static Result routeUri(String uri, RouteData data){

		Result result = new Result();
		List<String> to = new ArrayList<String>();

		StringBuffer responseBuffer = new StringBuffer();	
		StringBuffer classPath = new StringBuffer("hanlp.model.");

		if(uri==null||uri.equals("")){
			result.setState(400);
			result.setMsg("URI UNCOMPLETE");
			return result;
		}		
		
		String[] temp = uri.split("/");

		for(int i=0;i<temp.length;i++){
			if(temp[i]!=null&&!temp[i].equals("")){
				to.add(temp[i]);
			}
		}

		if(to.size() != 2){
			result.setState(400);
			result.setMsg("URI FORMAT ERROR");
			return result;
		}

		try{	

			Model model = (Model)Class.forName(classPath.append(to.get(0)).toString()).newInstance();					
			Format matData = model.runMethod(data, to.get(1));
			result.setState(200);
			result.setMsg("REQUEST SUCCESS");
			result.setData(matData);

		}catch(ParamErrorException e){
			result.setState(400);
			result.setMsg(e.getMessage());
		}catch(MethodErrorException e){
			result.setState(400);
			result.setMsg("URI METHOD ERROR");
		}catch(ClassNotFoundException e){
			result.setState(400);
			result.setMsg("URI SEGMENT ERROR");
		}catch(Exception e){
			result.setState(500);
			result.setMsg("SERVER INTERNAL ERROR");
		}

		return result;
		
	}
}
