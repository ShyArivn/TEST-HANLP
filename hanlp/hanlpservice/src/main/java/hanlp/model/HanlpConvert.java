package hanlp.model;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONException;

import com.hankcs.hanlp.HanLP;

import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpConvert extends Model {

	public Format convert(JSONObject json) throws ParamErrorException {

		String text = null;
		String format = null;
		String convert = null;

		String rk = null;

		try{
			text = json.getString("text");
			format = json.getString("format");
			convert = json.getString("convert");
		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}
		
		if(convert.equals("s2t")){
			rk = HanLP.convertToTraditionalChinese(text);		
		}else if(convert.equals("t2s")){
			rk = HanLP.convertToSimplifiedChinese(text);
		}else{
			throw new ParamErrorException("URI PARAM PATTERN ERROR");
		}

		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(rk);

	}
}
