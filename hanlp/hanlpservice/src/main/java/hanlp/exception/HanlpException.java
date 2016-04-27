package hanlp.exception;


public class HanlpException extends Exception {
	
	private String Msg;	

	public HanlpException(String msg){
		super(msg);
		this.Msg = msg;
	}
	
	public String getMessage(){
		return this.Msg;
	}
}
