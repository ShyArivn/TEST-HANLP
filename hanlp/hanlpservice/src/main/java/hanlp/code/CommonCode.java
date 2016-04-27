package hanlp.code;

public class CommonCode {
	
	public static String str2HexStr(String str) {  
  
    	char[] chars = "0123456789ABCDEF".toCharArray();  
    	StringBuilder sb = new StringBuilder("");  
    	byte[] bs = str.getBytes();  
    	int bit;  
    	for (int i = 0; i < bs.length; i++) {  
        	bit = (bs[i] & 0x0f0) >> 4;  
        	sb.append(chars[bit]);  
        	bit = bs[i] & 0x0f;  
        	sb.append(chars[bit]);  
    	}  
    	return sb.toString();  
	}  
	public static String bytesToHexString(byte[] src){  

    	StringBuilder stringBuilder = new StringBuilder("");  
    	if (src == null || src.length <= 0) {  
        	return null;  
    	}  
    	for (int i = 0; i < src.length; i++) {  
        	int v = src[i] & 0xFF;  
        	String hv = Integer.toHexString(v);  
        	if (hv.length() < 2) {  
            	stringBuilder.append(0);  
        	}  
       		stringBuilder.append(hv);  
    	}  
   		return stringBuilder.toString();  
	}  
}
