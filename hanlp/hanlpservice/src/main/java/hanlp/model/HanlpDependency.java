package hanlp.model;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dependency.CRFDependencyParser;
import com.hankcs.hanlp.dependency.nnparser.NeuralNetworkDependencyParser;
import com.hankcs.hanlp.dependency.MaxEntDependencyParser;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpDependency extends Model {

	public Format parser(JSONObject json) throws ParamErrorException {

		String text = null;
		String format = null;
		String parser = null;
		int param;

		List<String> rkList = null;
		CoNLLSentence sentence = null;

		try{
			text = json.getString("text");
			format = json.getString("format");
			parser = json.getString("parser");

		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}
		
		if(parser.equals("me")){
			sentence = MaxEntDependencyParser.compute(text);
		}else if(parser.equals("crf")){
			sentence = CRFDependencyParser.compute(text);
		}else if(parser.equals("neural")){
			sentence = NeuralNetworkDependencyParser.compute(text);
		}else{
			throw new ParamErrorException("URI PARAM PATTERN ERROR");
		}

		//JSONArray array = new JSONArray(rkList);
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(sentence.toString());

	}
}
