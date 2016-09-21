package com.troop66matawan.tm.exporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSON {
	
	public class JSONArray {
		private ArrayList<JSON> jsonArray;
		public JSONArray() {
			jsonArray = new ArrayList<JSON>();
		}
		public void addJson(JSON json) {
			jsonArray.add(json);
		}
		public Iterator<JSON> getArray() {
			return jsonArray.iterator();
		}
	}
	private Map<String,String> members;
	private Map<String, JSONArray> arrayMembers;
	private Map<String, JSON> jsonMembers;

	public JSON() {
		members = new HashMap<String,String>();		
		arrayMembers = new HashMap<String, JSONArray>();
		jsonMembers = new HashMap<String, JSON>();
	}
	
	public  void addKeyValuePair(String key, String value) {
		String sVal = "";
		if (value != null)
			sVal = value;
		members.put(key, sVal);
	}
	public  void addKeyValuePair( String key, Integer value) {
		String intVal = "";
		if (value != null)
			intVal = value.toString();
		members.put(key,intVal);
	}
	public void addKeyValuePair(String key, Boolean value) {
		members.put(key, value.toString());
	}
	public void addKeyValuePair( String key, Date value) {
		String dob = "";
		if (value != null) {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			dob =  df.format(value);
		}
		members.put(key, dob);
	}
	public void addKeyValuePair(String key, JSONArray value) {
		arrayMembers.put(key, value);
	}
	public void addKeyValuePair(String key, JSON value) {
		jsonMembers.put(key, value);
	}
	
	private void addKey(StringBuilder b,String key) {
		b.append("\"");
		b.append(key);
		b.append("\"");
		b.append(":");
	}
	
	private void addKeyValue(StringBuilder b,String key, String value) {
		addKey(b,key);
		b.append("\"");
		b.append(value);
		b.append("\"");
	}
	private void addKeyValue(StringBuilder b, String key,JSONArray value) {
		addKey(b,key);
		addJSONArray(b, value);
	}
	private void addKeyValue(StringBuilder b, String key, JSON value) {
		addKey(b,key);
		b.append(value.toString());
	}
	private void addJSONArray(StringBuilder b, JSONArray arValue) {
		Iterator<JSON> jsons = arValue.getArray();
		b.append("[");
		if (jsons.hasNext()) {
			JSON json = jsons.next();

			b.append(json.toString());
			while (jsons.hasNext()) {
				b.append(",");
				json = jsons.next();
				b.append(json.toString());
			}
		}
		b.append("]");
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("{");
		
		Set<String> keys = members.keySet();
		Iterator<String> it = keys.iterator();
		String key = it.next();
		String value = members.get(key);
		addKeyValue(b,key,value);			
		while (it.hasNext()) {
			b.append(",");
			key = it.next();
			value = members.get(key);
			addKeyValue(b,key,value);			
		}
		if (arrayMembers.size() > 0) {
			b.append(",");
			Set<String> arrayKeys = arrayMembers.keySet();
			Iterator<String> arIt = arrayKeys.iterator();
			String arrayKey = arIt.next();
			JSONArray arValue = arrayMembers.get(arrayKey);
			addKeyValue(b,arrayKey,arValue);
			while (arIt.hasNext()) {
				b.append(",");
				arrayKey = arIt.next();
				arValue = arrayMembers.get(arrayKey);
				addKeyValue(b,arrayKey,arValue);
			}
		}
		if (jsonMembers.size() > 0) {
			b.append(",");
			Set<String> jsonKeys = jsonMembers.keySet();
			Iterator<String> jIt = jsonKeys.iterator();
			String jKey = jIt.next();
			JSON jValue = jsonMembers.get(jKey);
			addKeyValue(b,jKey,jValue);
			while (jIt.hasNext()) {
				b.append(",");
				jKey = jIt.next();
				jValue = jsonMembers.get(jKey);
				addKeyValue(b,jKey,jValue);
			}
		}
		b.append("}");
		return b.toString();
	}

}
