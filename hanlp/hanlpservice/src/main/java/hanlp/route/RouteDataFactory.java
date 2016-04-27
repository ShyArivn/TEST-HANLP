package hanlp.route;

public class RouteDataFactory{

	public static RouteData create(String data, String type){
		if(type.equals("json")){
			return new JsonRouteData(data);
		}
		return new JsonRouteData(data);
		
	}
}
