package hanlp.model;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.summary.TextRankSentence;

import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpExtract extends Model {

	public Format extract(JSONObject json) throws ParamErrorException {

		String text = null;
		String format = null;
		String pattern = null;
		int param;

		List<String> rkList = null;

		try{
			text = json.getString("text");
			format = json.getString("format");
			pattern = json.getString("pattern");
			param = json.getInt("size");
		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}
		
		if(pattern.equals("kword")){
			rkList = HanLP.extractKeyword(text, param);		
		}else if(pattern.equals("phrase")){
			rkList = HanLP.extractPhrase(text, param);
		}else if(pattern.equals("summary")){
			rkList = HanLP.extractSummary(text, param);
		}else{
			System.out.println("java");
			throw new ParamErrorException("URI PARAM PATTERN ERROR");
		}
		System.out.println("jsldjf");

		JSONArray array = new JSONArray(rkList);
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(array.toString());

	}
}
