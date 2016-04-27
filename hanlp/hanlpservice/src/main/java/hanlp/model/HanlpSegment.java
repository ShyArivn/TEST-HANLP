package hanlp.model;

import com.hankcs.hanlp.HanLP;

import com.hankcs.hanlp.seg.HMM.HMMSegment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
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

public class HanlpSegment extends Model {

	public Format segment(JSONObject json) throws ParamErrorException {

		String text = null;
		String format = null;
		String cws = null;
		boolean termNature = false;

		List<Term> termList = new ArrayList<Term>();
		List<String> segList = new ArrayList<String>();
		
		try {

			text = json.getString("text");
			format = json.getString("format");
			cws = json.getString("cws");	
			termNature = json.getBoolean("cwsnature");
	
		}catch(JSONException e){
			throw new ParamErrorException("URI PARAM ERROR");
		}
		
		HanLP.Config.ShowTermNature = termNature;
		
		if(cws.equals("hmm")){
			Segment segment = new HMMSegment();
			termList = segment.seg(text);		
		}else if(cws.equals("crf")){
			Segment segment = new CRFSegment().enableCustomDictionary(false);
			termList = segment.seg(text);
		}else if(cws.equals("speed")){
			termList = SpeedTokenizer.segment(text);
		}else if(cws.equals("index")){
			termList = IndexTokenizer.segment(text);
		}else if(cws.equals("nlp")){
			termList = NLPTokenizer.segment(text);
		}else if(cws.equals("nshort")){
			Segment nShortSegment = new NShortSegment().enableCustomDictionary(false);
			termList = nShortSegment.seg(text);
		}else if(cws.equals("short")){
			Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false);
			termList = shortestSegment.seg(text);
		}else{
			throw new ParamErrorException("URI PARAM CWS ERROR");
		}
		
		for(Iterator it=termList.iterator();it.hasNext();){
			Term term = (Term)it.next();
			segList.add(term.toString());
		}

		JSONArray array = new JSONArray(segList);
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(array.toString());

	}
}
