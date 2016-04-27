package hanlp.route;

import org.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import hanlp.code.CommonCode;

public interface RouteData {

	public String getData(String key);
	public Object getData();
}

class JsonRouteData implements RouteData {
	
	private JSONObject data;		

	public JsonRouteData(String str){
		data = new JSONObject(str);
	}
	
	public String getData(String key){

		if(data.has(key)){
			return data.getString(key);
		}
		return new String();
	}
	
	public JSONObject getData(){
		return data;
	}
}

