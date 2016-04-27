package hanlp.model;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONException;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;

import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpDistance extends Model {

	public Format similiar(JSONObject json) throws ParamErrorException {

		String sword = null;
		String format = null;
		String tword = null;
		boolean similar = false;
		long distance;
		double similarity;

		String ret = null;

		try{
			sword = json.getString("sword");
			format = json.getString("format");
			tword = json.getString("tword");
			similar = json.getBoolean("similar");
			
		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}
		
		if(similar){
			 distance = CoreSynonymDictionary.distance(sword, tword);		
			 ret = Long.toString(distance);
		}else{
			 similarity = CoreSynonymDictionary.similarity(sword, tword);
			 ret = String.valueOf(similarity);
		}
		
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(ret);

	}
}
