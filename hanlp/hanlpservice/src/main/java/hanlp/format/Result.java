package hanlp.format;
import org.json.JSONObject;

public class Result {
	
	private int state; // http code
	private String msg; // msg data
	private Format data;
	private String dataformat; // data format
	
	public Result(){
	}
	
	public void setState(int state){
		this.state = state;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public void setData(Format data){
		this.data = data;
	}
	
	public void setDataFormat(String dataformat){
		this.dataformat = dataformat;
	}
	
	public int getStatus(){
		return state;
	}
	
	public JSONObject toJson(){

		JSONObject json = new JSONObject();
		json.put("msg",msg);
		if(data != null){
			json.put("data",data.formatData());
		}
		return json;
	}
	
	public String toString(){
		
		JSONObject json = new JSONObject();
		json.put("msg",msg);
		if(data != null){
			json.put("data",data.formatData());
		}
		return json.toString();
	}
	
} 
