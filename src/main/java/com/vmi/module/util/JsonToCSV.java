package com.vmi.module.util;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import javax.sql.rowset.serial.SerialBlob;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;
import org.json.CDL;

public class JsonToCSV
{
  public static String getCSVFile(org.json.JSONArray strRSarr)
    throws IOException, JSONException
  {
    return "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64," + new String(Base64.getEncoder().encode(CDL.toString(strRSarr).getBytes()));
  }
  
  public static HashMap<String, Object> jsontocsvconvter(org.codehaus.jettison.json.JSONArray strRSarr)
    throws IOException, JSONException
  {
    byte[] byFileName = toString(strRSarr).getBytes();
    Blob blob = null;
    try
    {
      blob = new SerialBlob(byFileName);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    String strMimeType = "application/vnd.ms-excel";
    String strFileName = "LMSReport.csv";
    HashMap<String, Object> hshRequestValues = new HashMap();
    hshRequestValues.put("FILE_STREAM_NAME", strFileName);
    hshRequestValues.put("FILE_STREAM_TYPE", strMimeType);
    try
    {
      hshRequestValues.put("FILE_STREAM", new String(Base64.getEncoder().encode(blob.getBytes(0L, (int)blob.length()))));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    hshRequestValues.put("FILE_STREAM_DISPOSITION_TYPE", "inline");
    return hshRequestValues;
  }
  
  private static String getValue(JSONTokener x)
    throws JSONException, JSONException
  {
    char c;
    do
    {
      c = x.next();
    } while ((c == ' ') || (c == '\t'));
    switch (c)
    {
    case '\000': 
      return null;
    case '"': 
    case '\'': 
      char q = c;
      StringBuffer sb = new StringBuffer();
      for (;;)
      {
        c = x.next();
        if (c == q) {
          break;
        }
        if ((c == 0) || (c == '\n') || (c == '\r')) {
          throw x.syntaxError("Missing close quote '" + q + "'.");
        }
        sb.append(c);
      }
      return sb.toString();
    case ',': 
      x.back();
      return "";
    }
    x.back();
    return x.nextTo(',');
  }
  
  public static org.codehaus.jettison.json.JSONArray rowToJSONArray(JSONTokener x)
    throws JSONException, JSONException
  {
    org.codehaus.jettison.json.JSONArray ja = new org.codehaus.jettison.json.JSONArray();
    for (;;)
    {
      String value = getValue(x);
      char c = x.next();
      if ((value == null) || (
        (ja.length() == 0) && (value.length() == 0) && (c != ','))) {
        return null;
      }
      ja.put(value);
      while (c != ',')
      {
        if (c != ' ')
        {
          if ((c == '\n') || (c == '\r') || (c == 0)) {
            return ja;
          }
          throw x.syntaxError("Bad character '" + c + "' (" + c + ").");
        }
        c = x.next();
      }
    }
  }
  
  public static JSONObject rowToJSONObject(org.codehaus.jettison.json.JSONArray names, JSONTokener x)
    throws JSONException, JSONException
  {
    org.codehaus.jettison.json.JSONArray ja = rowToJSONArray(x);
    return ja != null ? ja.toJSONObject(names) : null;
  }
  
  public static String rowToString(org.codehaus.jettison.json.JSONArray ja)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < ja.length(); i++)
    {
      if (i > 0) {
        sb.append(',');
      }
      Object object = ja.opt(i);
      if (object != null)
      {
        String string = object.toString();
        if ((string.length() > 0) && ((string.indexOf(',') >= 0) || 
          (string.indexOf('\n') >= 0) || (string.indexOf('\r') >= 0) || 
          (string.indexOf(0) >= 0) || (string.charAt(0) == '"')))
        {
          sb.append('"');
          int length = string.length();
          for (int j = 0; j < length; j++)
          {
            char c = string.charAt(j);
            if ((c >= ' ') && (c != '"')) {
              sb.append(c);
            }
          }
          sb.append('"');
        }
        else
        {
          sb.append(string);
        }
      }
    }
    sb.append('\n');
    return sb.toString();
  }
  
  public static org.codehaus.jettison.json.JSONArray toJSONArray(String string)
    throws JSONException, JSONException
  {
    return toJSONArray(new JSONTokener(string));
  }
  
  public static org.codehaus.jettison.json.JSONArray toJSONArray(JSONTokener x)
    throws JSONException, JSONException
  {
    return toJSONArray(rowToJSONArray(x), x);
  }
  
  public static org.codehaus.jettison.json.JSONArray toJSONArray(org.codehaus.jettison.json.JSONArray names, String string)
    throws JSONException, JSONException
  {
    return toJSONArray(names, new JSONTokener(string));
  }
  
  public static org.codehaus.jettison.json.JSONArray toJSONArray(org.codehaus.jettison.json.JSONArray names, JSONTokener x)
    throws JSONException, JSONException
  {
    if ((names == null) || (names.length() == 0)) {
      return null;
    }
    org.codehaus.jettison.json.JSONArray ja = new org.codehaus.jettison.json.JSONArray();
    for (;;)
    {
      JSONObject jo = rowToJSONObject(names, x);
      if (jo == null) {
        break;
      }
      ja.put(jo);
    }
    if (ja.length() == 0) {
      return null;
    }
    return ja;
  }
  
  public static String toString(org.codehaus.jettison.json.JSONArray ja)
    throws JSONException, JSONException
  {
    JSONObject jo = ja.optJSONObject(0);
    if (jo != null)
    {
      org.codehaus.jettison.json.JSONArray names = jo.names();
      if (names != null) {
        return rowToString(names) + toString(names, ja);
      }
    }
    return null;
  }
  
  public static String toString(org.codehaus.jettison.json.JSONArray names, org.codehaus.jettison.json.JSONArray ja)
    throws JSONException, JSONException
  {
    if ((names == null) || (names.length() == 0)) {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < ja.length(); i++)
    {
      JSONObject jo = ja.optJSONObject(i);
      if (jo != null) {
        sb.append(rowToString(jo.toJSONArray(names)));
      }
    }
    return sb.toString();
  }
}
