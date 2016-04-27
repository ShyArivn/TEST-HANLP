package hanlp.model;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.suggest.Suggester;

import hanlp.common.TextProcess;
import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpSuggester extends Model {

	public Format suggest(JSONObject json) throws ParamErrorException {

		String text = null;
		String format = null;
		String kword = null;
		boolean param = false;

		List<String> rkList = null;

		try{

			text = json.getString("text");
			format = json.getString("format");
			kword = json.getString("kword");
			if(json.has("semantic")){
				param = json.getBoolean("semantic");
			}

		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}

		Suggester suggester = new Suggester();	
		List<String> sentences = TextProcess.spiltSentence(text);
		for (String sentence : sentences){
			suggester.addSentence(sentence);
		}

		rkList = suggester.suggest(kword, param?2:1);
		JSONArray array = new JSONArray(rkList);
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(array.toString());

	}
}
