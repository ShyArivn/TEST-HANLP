package hanlp.format;

public class JsonFormat implements Format {
	
	private String format;
	private Object ob;

	public Format setData(String format){
		this.format = format;
		return this;
	}
	
	public String formatData(){
		return format;
	}
}
