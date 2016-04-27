package hanlp.model;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import hanlp.format.Format;
import hanlp.format.FormatFactory;
import hanlp.exception.ParamErrorException;

public class HanlpPinyin extends Model {

	public Format translate(JSONObject json) throws ParamErrorException {
		
		String text = null;
		String format = null;
		String mark = null;
		
		try{

			text = json.getString("text");
			format = json.getString("format");
			mark = json.getString("mark");

		}catch(JSONException e){

			throw new ParamErrorException("URI PARAM ERROR");	
		}

		List<Pinyin> rkList = HanLP.convertToPinyinList(text);
		List<String> pyList = new ArrayList<String>();
		
		if(mark.equals("pynotone")){
			for(Pinyin pinyin : rkList)	{
				pyList.add(pinyin.getPinyinWithoutTone());
			}	
		}else if(mark.equals("pytone")){
			for(Pinyin pinyin : rkList)	{
				pyList.add(pinyin.getPinyinWithToneMark());
			}	
		}else if(mark.equals("tone")){
			for(Pinyin pinyin : rkList)	{
				pyList.add(String.valueOf(pinyin.getTone()));
			}	
		}else if(mark.equals("smother")){
			for(Pinyin pinyin : rkList)	{
				pyList.add(pinyin.getShengmu().toString());
			}	
		}else if(mark.equals("vowels")){
			for(Pinyin pinyin : rkList)	{
				pyList.add(pinyin.getYunmu().toString());
			}	
		}else if(mark.equals("head")){
			for(Pinyin pinyin : rkList)	{
				pyList.add(pinyin.getHead().toString());
			}	
		}else{
			throw new ParamErrorException("URI PARAM MARK ERROR");
		}
		
		JSONArray array = new JSONArray(pyList);
		Format formatdata = FormatFactory.create(format);
		return formatdata.setData(array.toString());
	}
}
