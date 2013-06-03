package Wrapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Static class help to encapsulate json operations. 
 * All method are atomic. 
 *
 */
public class Parser
{
	public Parser()
	{
		
	}
	
	public static LinkedTreeMap parseIn(String json)
	{
		Gson gson = new Gson();
		LinkedTreeMap rst = (LinkedTreeMap) gson.fromJson(json, Object.class);
		String className = (String) rst.get("class");
		String method = (String) rst.get("method");
		LinkedTreeMap rtnVal = (LinkedTreeMap) rst.get("val");
		return rtnVal;
	}
	
	public static LinkedTreeMap getRtnVal(String json){
		Gson gson = new Gson();
		LinkedTreeMap rst = (LinkedTreeMap) gson.fromJson(json, Object.class);
		String className = (String) rst.get("class");
		String method = (String) rst.get("method");
		LinkedTreeMap rtnVal = (LinkedTreeMap) rst.get("val");
		return rtnVal;
	}
	
	public static LinkedTreeMap getRtnVal(LinkedTreeMap serverWrapper){
		LinkedTreeMap rtnVal = (LinkedTreeMap) serverWrapper.get("val");
		return rtnVal;
	}
	
	public static OutWrapper[] parseOut()
	{
		return null;
	}
	
}