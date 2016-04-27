package hanlp.model;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpRecognition extends Model {

	public Format recognition(JSONObject json) throws ParamErrorException {
		
		String text = null;
		String format = null;
		Segment segment = HanLP.newSegment();

		try{

			text = json.getString("text");
			format = json.getString("format");

			if(json.has("ecn")){
				segment.enableNameRecognize(json.getBoolean("ecn"));
			}
			if(json.has("etn")){
				segment.enableTranslatedNameRecognize(json.getBoolean("etn"));
			}
			if(json.has("ejn")){
				segment.enableJapaneseNameRecognize(json.getBoolean("ejn"));
			}
			if(json.has("epn")){
				segment.enablePlaceRecognize(json.getBoolean("epn"));
			}
			if(json.has("eon")){
				segment.enableOrganizationRecognize(json.getBoolean("eon"));
			}
			
		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}

		List<Term> termList = segment.seg(text);
		List<String> segList = new ArrayList<String>();
		
		for(Iterator it=termList.iterator();it.hasNext();){
			Term term = (Term)it.next();
			segList.add(term.toString());
		}
		
		JSONArray array = new JSONArray(segList);
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(array.toString());
	}
}
