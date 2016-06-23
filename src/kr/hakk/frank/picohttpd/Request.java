package kr.hakk.frank.picohttpd;

import java.util.HashMap;

public class Request {
	private HashMap<String, String> map = new HashMap<String, String>();
	public Request(String method, String uri){
		map.put("METHOD", method);
		map.put("URI", uri);
	}
	public String getField(String key){
		key = key.toUpperCase();
		if(map.containsKey(key)){
			return map.get(key);
		}
		return null;
	}
	public void setField(String key, String value){
		key = key.toUpperCase();
		map.put(key, value);
	}
	public String getMethod(){
		return map.get("METHOD");
	}
	public String getUri(){
		return map.get("URI");
	}
	public String[] getKeys(){
		return (String[])map.keySet().toArray();
	}
	
}
