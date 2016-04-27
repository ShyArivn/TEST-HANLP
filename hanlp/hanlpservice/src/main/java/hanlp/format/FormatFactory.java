package hanlp.format;
import hanlp.exception.ParamErrorException;

public class FormatFactory {

	public static Format create(String type) throws ParamErrorException {
		if(type.equals("json")){
			return new JsonFormat();
		}else if(type.equals("xml")){
			return new XmlFormat();
		}else if(type.equals("plain")){
			return new PlainFormat();
		}else{
			throw new ParamErrorException("FORMAT PARAM ERROR");
		}
	}
}
