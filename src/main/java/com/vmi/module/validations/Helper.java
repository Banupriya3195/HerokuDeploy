package com.vmi.module.validations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class Helper {

	  private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
	public static Boolean checkEmptyStr(String strNull) {
		if (strNull == "" || strNull.equals("")) {
			return true;
		}
		return false;
	}

	public static Boolean checkNotEmptyStr(String strNull) {
		if (strNull == "" || strNull.equals("")) {
			return false;
		}
		return true;
	}

	public static Boolean checkNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	public static Boolean checkNotNull(Object strNull) {
		if (strNull == null || strNull.equals("")) {
			return false;
		}
		return true;
	}

	public static String correctNull(String strNull) {
		if (strNull == null) {
			strNull = "";
		}
		return strNull.trim();
	}

	/** This Method for making null values to double values* */

	public static String correctDouble(String strNull) {
		if (strNull == null || strNull.equals("")) {
			strNull = "0.00";
		}
		return strNull;
	}

	public static String correctInt(String strNull) {
		if (strNull == null || strNull.equals("")) {
			strNull = "0";
		}
		return strNull;
	}

	public static Long correctLong(Long strNull) {
		if (strNull == null || strNull.equals(0)) {
			strNull = 0l;
		}
		return strNull;
	}

	public static String correctLongStr(String strNull) {
		if (strNull == null || strNull.equals("")) {
			strNull = "0";
		}
		return strNull;
	}

	public static String correctComma(String strNull) {
		strNull = correctNull(strNull);
		strNull = strNull.replace(",", "");
		return strNull;
	}

	public static String roundVal(String str) {
		String rVal = "";
		if (str != null && !str.equals("")) {
			double dVal = Double.parseDouble(str);
			rVal = String.valueOf(Math.round(dVal)) + ".00";

		}
		return rVal;
	}

	/** This Method for making CorrectSingle Quote * */

	public static String correctDoubleQuote(String str) throws Exception {
		String strLStr = "";
		str = correctNull(str);
		try {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '\"') {
					strLStr = strLStr + "\\" + str.charAt(i);
				} else {
					strLStr = strLStr + str.charAt(i);
				}
			}
		} catch (Exception e) {
			throw new Exception("Error in correctDoubleQuote:" + e);
		}
		return strLStr;
	}

	/** ***** This method for getting current date **** */
	public static String getCurrentDateTime() throws Exception {
		Calendar cal = Calendar.getInstance();
		String strSysDate = "";
		try {
			String strSysDay = String.valueOf(cal.get(Calendar.DATE));
			if (Integer.parseInt(strSysDay) < 10) {
				strSysDay = "0" + strSysDay;
			}

			String strSysMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			if (Integer.parseInt(strSysMonth) < 10) {
				strSysMonth = "0" + strSysMonth;
			}

			String strSysYear = String.valueOf(cal.get(Calendar.YEAR));

			strSysDate = strSysDay + "/" + strSysMonth + "/" + strSysYear;

		} catch (Exception e) {
			throw new Exception("*Exception in getCurrentDateTime **" + e);
		}
		return strSysDate;
	}

	public static String getCurrentTime() throws Exception {
		Calendar cal = Calendar.getInstance();
		String strSysTime = "";
		try {
			String strSysHour = String.valueOf(cal.get(Calendar.HOUR));
			if (Integer.parseInt(strSysHour) < 10) {
				strSysHour = "0" + strSysHour;
			}
			String strSysMints = String.valueOf(cal.get(Calendar.MINUTE));
			if (Integer.parseInt(strSysMints) < 10) {
				strSysMints = "0" + strSysMints;
			}
			String strSyssecs = String.valueOf(cal.get(Calendar.SECOND));
			if (Integer.parseInt(strSyssecs) < 10) {
				strSyssecs = "0" + strSyssecs;
			}
			int amOrpm = cal.get(Calendar.AM_PM);
			String str = "";
			if (amOrpm == 1) {
				str = "PM";
			} else {
				str = "AM";
			}
			strSysTime = strSysHour + ":" + strSysMints + ":" + strSyssecs + " " + str;

		} catch (Exception e) {
			throw new Exception("*Exception in getCurrentDateTime **" + e);
		}
		return strSysTime;
	}

	public static String addMonthWithDate(Date date, int intMonths) throws Exception {
		String strDate = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, intMonths);
			date = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			strDate = sdf.format(date);
		} catch (Exception e) {
			throw new Exception("Error in addMonthWithDate:" + e);
		}
		return strDate;
	}

	public static Object correctObject(Object obj1, Object obj2) {
		if (obj1 == null)
			return obj2;
		return obj1;
	}

	public static String formatComment(String strValue) {
		StringBuffer sbfValue = new StringBuffer();
		int intIndex = 0;
		if (strValue != null || !strValue.equals("")) {
			sbfValue.append(strValue);
			for (int i = 1; i < strValue.length(); i++) {
				intIndex = sbfValue.toString().indexOf("~");
				if (intIndex != -1) {
					sbfValue.deleteCharAt(intIndex);
					sbfValue.insert(intIndex, "\n");
				}
			}
		}

		return sbfValue.toString();
	}

	public static String correctDate(String strNull) {
		try {
			if (strNull == null) {
				strNull = getCurrentDateTime();
				strNull = strNull.substring(8, 10) + "/" + strNull.substring(5, 7) + "/" + strNull.substring(0, 4);
			}

		} catch (Exception e) {
			System.out.println("Error in correctDate:" + e);
		}
		return strNull.trim();
	}

	public static String correctDoubleQuotesHtml(String str) {

		if (str == null) {
			return "";
		} else {
			StringBuffer sbf = new StringBuffer(str);
			for (int i = 0; i < sbf.length(); i++) {
				if (sbf.charAt(i) == '"') {
					sbf.replace(i, i + 1, "&quot;");
				}
			}
			return sbf.toString();
		}
	}

	public static String correctDoubleQuotesScript(String str) {

		if (str == null) {
			return "";
		} else {
			StringBuffer sbf = new StringBuffer(str);
			for (int i = 0; i < sbf.length(); i++) {
				if (sbf.charAt(i) == '"') {
					sbf.replace(i, i + 1, "'");
				}
			}
			return sbf.toString();
		}
	}

	public static String checkDecimal(String strDec) {
		String str = strDec;
		int intLocal = 0;
		if (str.indexOf(".") != -1) {
			int i = str.indexOf(".");
			for (int k = i; k < str.length(); k++) {
				intLocal++;

			}
			switch (intLocal) {
			case 2:
				str = str.concat("0");
				break;
			case 1:
				str = str.concat("00");
				break;
			}
			if (intLocal >= 3) {
				str = str.substring(0, str.indexOf(".") + 3);

			}
		} else {
			str = str.concat(".00");
		}
		return str;

	}

	public static String breakText(String sent, int param) {
		String let = new String();
		int nowords = 0;
		int len = 0;
		int strlen = 0;
		String outstring = new String();
		String totalstring = new String();
		outstring = "";
		StringTokenizer st = new StringTokenizer(sent);
		while (st.hasMoreTokens()) {
			let = st.nextToken();
			let = let + " ";
			nowords = nowords + 1;
			len = let.length();
			strlen = outstring.length();
			if (strlen + len <= param) {
				outstring = outstring + let;
				strlen = 0;
			} else {
				totalstring = totalstring + outstring + "\n";
				outstring = let;
			}
		}
		totalstring = totalstring + outstring;
		return totalstring;
	}

	public static String figtoWords(String str1) {

		String one[] = { "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine " };
		String ele[] = { "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
				"Eighteen ", "Nineteen " };
		String ten[] = { "Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety " };

		String str = "";
		String strdecimals = "";
		int i = 0;
		for (; i < str1.length(); i++) {
			if (str1.charAt(i) == '.') {
				str = str1.substring(0, i);
				strdecimals = str1.substring(i, str1.length());
				break;
			}
		}
		if (i == str1.length()) {
			str = str1;
			strdecimals = "00";
		}

		int len, k;
		len = str.length();
		k = 10 - len;
		if (len < 10) {
			switch ((k)) {
			case 1:
				str = "0" + str;
				break;
			case 2:
				str = "00" + str;
				break;
			case 3:
				str = "000" + str;
				break;
			case 4:
				str = "0000" + str;
				break;
			case 5:
				str = "00000" + str;
				break;
			case 6:
				str = "000000" + str;
				break;
			case 7:
				str = "0000000" + str;
				break;
			case 8:
				str = "00000000" + str;
				break;
			case 9:
				str = "000000000" + str;
				break;
			case 10:
				str = "0000000000" + str;
				break;
			}
		}

		int d, d1, d2;
		String word = new String();
		String chdigit = new String();
		chdigit = str.substring(0, 1);
		d = Integer.parseInt(chdigit);
		chdigit = str.substring(1, 2);
		d1 = Integer.parseInt(chdigit);
		chdigit = str.substring(2, 3);
		d2 = Integer.parseInt(chdigit);
		if (d != 0) {
			word = word.concat(one[d - 1]);
			word = word.concat("Hundred ");
		}
		if (d1 == 0 && d2 != 0) {
			word = word.concat(one[d2 - 1]);
			word = word.concat("Crores ");
		}
		if (d1 == 1 && d2 != 0) {
			word = word.concat(ele[d2 - 1]);
			word = word.concat("Crores ");
		}
		if (d1 != 0 && d2 == 0) {
			word = word.concat(ten[d1 - 1]);
			word = word.concat("Crores ");
		}
		if (d1 > 1 && d2 != 0) {
			word = word.concat(ten[d1 - 1]);
			word = word.concat(one[d2 - 1]);
			word = word.concat("Crores ");
		}
		if (d != 0 && d1 == 0 && d2 == 0) {
			word = word.concat("Crores ");
		}
		chdigit = str.substring(3, 4);
		d = Integer.parseInt(chdigit);
		chdigit = str.substring(4, 5);
		d1 = Integer.parseInt(chdigit);
		if (d == 0 && d1 != 0) {
			word = word.concat(one[d1 - 1]);
			word = word.concat("Lakhs ");
		}
		if (d == 1 && d1 != 0) {
			word = word.concat(ele[d1 - 1]);
			word = word.concat("Lakhs ");
		}

		if (d != 0 && d1 == 0) {
			word = word.concat(ten[d - 1]);
			word = word.concat("Lakhs ");
		}
		if (d > 1 && d1 != 0) {
			word = word.concat(ten[d - 1]);
			word = word.concat(one[d1 - 1]);
			word = word.concat("Lakhs ");
		}

		chdigit = str.substring(5, 6);
		d = Integer.parseInt(chdigit);
		chdigit = str.substring(6, 7);
		d1 = Integer.parseInt(chdigit);
		if (d == 0 && d1 != 0) {
			word = word.concat(one[d1 - 1]);
			word = word.concat("Thousand ");
		}
		if (d == 1 && d1 != 0) {
			word = word.concat(ele[d1 - 1]);
			word = word.concat("Thousand ");
		}
		if (d != 0 && d1 == 0) {
			word = word.concat(ten[d - 1]);
			word = word.concat("Thousand ");
		}
		if (d > 1 && d1 != 0) {
			word = word.concat(ten[d - 1]);
			word = word.concat(one[d1 - 1]);
			word = word.concat("Thousand ");
		}
		chdigit = str.substring(7, 8);
		d = Integer.parseInt(chdigit);
		chdigit = str.substring(8, 9);
		d1 = Integer.parseInt(chdigit);
		chdigit = str.substring(9, 10);
		d2 = Integer.parseInt(chdigit);
		if (d != 0) {
			word = word.concat(one[d - 1]);
			word = word.concat("Hundred ");
		}
		if (d1 == 0 && d2 != 0) {
			word = word.concat(one[d2 - 1]);
		}
		if (d1 == 1 && d2 != 0) {
			word = word.concat(ele[d2 - 1]);
		}
		if (d1 != 0 && d2 == 0) {
			word = word.concat(ten[d1 - 1]);
		}
		if (d1 > 1 && d2 != 0) {
			word = word.concat(ten[d1 - 1]);
			word = word.concat(one[d2 - 1]);
		}
		if (str.equals("0000000000")) {
			word = "Zero Only";
		} else {
			word = word.concat("only");

		}

		return word;
	}

	public static String replaceAll(String str, String pattern, String replace, boolean isCaseSensitive) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		if (isCaseSensitive) {
			while ((e = str.indexOf(pattern, s)) >= 0) {
				result.append(str.substring(s, e));
				result.append(replace);
				s = e + pattern.length();
			}
		} else {
			while ((e = str.toUpperCase().indexOf(pattern.toUpperCase(), s)) >= 0) {
				result.append(str.substring(s, e));
				result.append(replace);
				s = e + pattern.length();
			}
		}

		result.append(str.substring(s));
		return result.toString();
	}

	public static String formatDoubleValue(double dblvalue) {
		return new java.text.DecimalFormat("##0.00").format(dblvalue);
	}

	public static String getMonths(int intMonth) {
		String strMonths[] = { "", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		return strMonths[intMonth];
	}

	public static String CLOBToString(Clob cl) {

		String strClob = "";
		try {
			if (cl == null)
				return "";
			StringBuffer strOut = new StringBuffer();
			String aux;
			// We access to stream, as this way we don't have to use the CLOB.length() which
			// is slower...
			BufferedReader br = new BufferedReader(cl.getCharacterStream());
			while ((aux = br.readLine()) != null)
				strOut.append(aux).append("\n");
			strClob = strOut.toString();
		} catch (SQLException ie) {
			System.out.print(ie);
		} catch (IOException e) {
			System.out.print(e);
		}
		return strClob;
	}

	/**
	 * Method correctSingleQuote.
	 * 
	 * @param str String
	 * @return String
	 * @throws Exception
	 */
	public static String correctSingleQuote(String str) throws Exception {
		StringBuffer strLStr = new StringBuffer();
		str = correctNull(str);
		try {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '\'') {
					strLStr.append(str.charAt(i));
					strLStr.append("'");
				} else {
					strLStr.append(str.charAt(i));

				}
			}
		} catch (Exception e) {
			throw new Exception("Error in FwHelper: correctSingleQuote" + e);
		}
		return strLStr.toString();
	}

	/**
	 * Method correctSingleQuote.
	 * 
	 * @param str String
	 * @return String
	 * @throws Exception
	 */
	public static String correctDot(String str) {

		return str.replaceAll("\\.", " ");
	}

	/*
	 * @author R.PAULRAJ
	 * 
	 * @param this function is mainly written to remove double quotes in string and
	 * add single quotes and enter marks in java script return string
	 */

	/*
	 * public static String replaceForJavaScriptString(String str) { return
	 * correctNull(str).replaceAll("\"","\\\\\"").replaceAll("\r\n","\\\\n").
	 * replaceAll("'","\\\\'"); }
	 */

	public static String replaceForJavaScriptString(String str) {
		str = Helper.correctNull(str).replaceAll("\"", "\\\\\"").replaceAll("\r\n", "\\\\n").replaceAll("'", "\\\\'")
				.replaceAll("\n", " ");
		String strnew = correctNull(str).replace("\\n", " ");
		strnew = correctDoubleQuotesScript(strnew);
		return strnew;
	}

	/*
	 * @Author R.Paulraj
	 */

	public static String converToLakhs(String str) {
		double lakhs = Double.parseDouble(Helper.correctDouble(str));

		lakhs = lakhs / 100000;

		return new java.text.DecimalFormat("##0.00").format(lakhs);
	}

	public static String converToCores(String str) {
		double cores = Double.parseDouble(Helper.correctDouble(str));

		cores = cores / 10000000;

		return new java.text.DecimalFormat("##0.00").format(cores);
	}

	public static String formatTextAreaData(String str) {

		if (str == null) {
			return "";
		} else {
			StringBuffer sbf = new StringBuffer(str);
			for (int i = 0; i < sbf.length(); i++) {
				if (sbf.charAt(i) == '\n') {
					sbf.replace(i, i + 1, "<br>");
				}
				if (sbf.charAt(i) == ' ' && (i + 1) < sbf.length() && sbf.charAt(i + 1) == ' ') {
					sbf.replace(i, i + 1, "&nbsp;&nbsp;");
				}

			}
			return sbf.toString();
		}
	}

	public static String encodeAmpersand(String str) {

		if (str == null) {
			return "";
		} else {
			StringBuffer sbf = new StringBuffer(str);
			for (int i = 0; i < sbf.length(); i++) {
				if (sbf.charAt(i) == '&') {
					sbf.replace(i, i + 1, "AMPERSAND");
				}
			}
			return sbf.toString();
		}
	}

	public static String decodeAmpersand(String str) {

		if (str == null) {
			return "";
		} else {
			str = str.replaceAll("AMPERSAND", "&");
			return str;
		}
	}

	public static boolean isNumeric(String strValue) {
		for (int i = 0; i < strValue.length(); i++) {
			// If we find a non-digit character we return false.
			if (!Character.isDigit(strValue.charAt(i)) && strValue.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}

	public static String converAmount(String stramt, String strformat) {
		double dblamt = Double.parseDouble(Helper.correctDouble(stramt));

		if (strformat.equalsIgnoreCase("T")) {
			dblamt = dblamt / 1000;
		} else if (strformat.equalsIgnoreCase("L")) {
			dblamt = dblamt / 100000;
		} else if (strformat.equalsIgnoreCase("C")) {
			dblamt = dblamt / 10000000;
		}

		return new java.text.DecimalFormat("##0.00").format(dblamt);
	}

	/**
	 * Method parseInt.
	 * 
	 * @param s String
	 * @return int
	 */
	public static int parseInt(String s) {
		return parseInt(s, 0);
	}

	/**
	 * Method parseInt.
	 * 
	 * @param s String
	 * @param i int
	 * @return int
	 */
	public static int parseInt(String s, int i) {
		int j = i;
		try {
			s = correctNull(s);
			if (s.trim().equals("") || s == null) {
				j = i;
			} else {
				j = Integer.parseInt(s);
			}

		} catch (NumberFormatException numberformatexception) {
			return i;
		}
		return j;
	}

	/**
	 * Method to remove HTML Tags from a String
	 * 
	 * @param strComments
	 * @return StringBuffer
	 */
	public static StringBuffer removeHTMLTags(String strComments) throws Exception {
		StringBuffer strWithoutTag = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < strComments.length(); i++) {
			if ((strComments.charAt(i) == '<') || flag == true) {
				flag = true;
				if (strComments.charAt(i) == '>') {
					flag = false;
				}
			}
			if (flag == false) {
				if ((strComments.charAt(i) != '>') && (strComments.charAt(i) != '<')) {
					strWithoutTag.append(strComments.charAt(i));
				}
			}
		}
		return strWithoutTag;
	}

	public static String replaceJavaScriptString(String str) {
		return correctNull(str).replaceAll("\"", "\\\"").replaceAll("\n", "\\\n").replaceAll("\r\n", "\\\\r\\\\n")
				.replaceAll("\'", "\\\'");
	}

	public static String replaceSingleQuoteString(String str) {
		return correctNull(str).replaceAll("\'", "~");
	}

	public static String CLOBToStringForPrint(Clob cl) {
		StringBuffer strbuff = new StringBuffer();
		String str = "";
		String strClob = "";
		try {
			if (cl == null)
				return "";

			// We access to stream, as this way we don't have to use the CLOB.length()which
			// is slower...
			Reader i = cl.getCharacterStream();
			BufferedReader br = new BufferedReader(i);
			while ((str = br.readLine()) != null) {
				strbuff.append(str).append("<br>");
			}
			strClob = strbuff.toString();
		} catch (IOException e) {
			LOGGER.info("error occured" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strClob;
	}

	public static String figtoWordsanddecimal(String str1) {

		String str = "";
		String word = new String();
		String word1 = new String();

		String strNum[] = str1.trim().split("\\.");
		String str6 = ".";
		int numSize = strNum.length;
		String len1 = "";

		String one[] = { "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine " };
		String ele[] = { "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
				"Eighteen ", "Nineteen " };
		String ten[] = { "Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety " };

		for (int i = 1; i <= numSize; i++) {

			int len, k;
			len = strNum[i - 1].trim().length();
			str = (String) strNum[i - 1];
			if (i > 1) { // Dot changes to And
				word = word.concat(" and ");
			}
			k = 10 - len;
			if (len < 10) {
				switch ((k)) {
				case 1:
					str = "0" + str;
					break;
				case 2:
					str = "00" + str;
					break;
				case 3:
					str = "000" + str;
					break;
				case 4:
					str = "0000" + str;
					break;
				case 5:
					str = "00000" + str;
					break;
				case 6:
					str = "000000" + str;
					break;
				case 7:
					str = "0000000" + str;
					break;
				case 8:
					str = "00000000" + str;
					break;
				case 9:
					str = "000000000" + str;
					break;
				case 10:
					str = "0000000000" + str;
					break;
				}
			}

			int d, d1, d2;

			String chdigit = new String();
			chdigit = str.substring(0, 1);
			d = Integer.parseInt(chdigit);
			chdigit = str.substring(1, 2);
			d1 = Integer.parseInt(chdigit);
			chdigit = str.substring(2, 3);
			d2 = Integer.parseInt(chdigit);
			if (d != 0) {
				word = word.concat(one[(d - 1)]);
				word = word.concat("Hundred ");
			}
			if (d1 == 0 && d2 != 0) {
				word = word.concat(one[(d2 - 1)]);
				word = word.concat("Crores ");
			}
			if (d1 == 1 && d2 != 0) {
				word = word.concat(ele[(d2 - 1)]);
				word = word.concat("Crores ");
			}
			if (d1 != 0 && d2 == 0) {
				word = word.concat(ten[(d1 - 1)]);
				word = word.concat("Crores ");
			}
			if (d1 > 1 && d2 != 0) {
				word = word.concat(ten[(d1 - 1)]);
				word = word.concat(one[(d2 - 1)]);
				word = word.concat("Crores ");
			}
			if (d != 0 && d1 == 0 && d2 == 0) {
				word = word.concat("Crores ");
			}
			chdigit = str.substring(3, 4);
			d = Integer.parseInt(chdigit);
			chdigit = str.substring(4, 5);
			d1 = Integer.parseInt(chdigit);
			if (d == 0 && d1 != 0) {
				word = word.concat(one[(d1 - 1)]);
				word = word.concat("Lakhs ");
			}
			if (d == 1 && d1 != 0) {
				word = word.concat(ele[(d1 - 1)]);
				word = word.concat("Lakhs ");
			}

			if (d != 0 && d1 == 0) {
				word = word.concat(ten[(d - 1)]);
				word = word.concat("Lakhs ");
			}
			if (d > 1 && d1 != 0) {
				word = word.concat(ten[(d - 1)]);
				word = word.concat(one[(d1 - 1)]);
				word = word.concat("Lakhs ");
			}

			chdigit = str.substring(5, 6);
			d = Integer.parseInt(chdigit);
			chdigit = str.substring(6, 7);
			d1 = Integer.parseInt(chdigit);
			if (d == 0 && d1 != 0) {
				word = word.concat(one[(d1 - 1)]);
				word = word.concat("Thousand ");
			}
			if (d == 1 && d1 != 0) {
				word = word.concat(ele[(d1 - 1)]);
				word = word.concat("Thousand ");
			}
			if (d != 0 && d1 == 0) {
				word = word.concat(ten[(d - 1)]);
				word = word.concat("Thousand ");
			}
			if (d > 1 && d1 != 0) {
				word = word.concat(ten[(d - 1)]);
				word = word.concat(one[(d1 - 1)]);
				word = word.concat("Thousand ");
			}
			chdigit = str.substring(7, 8);
			d = Integer.parseInt(chdigit);
			chdigit = str.substring(8, 9);
			d1 = Integer.parseInt(chdigit);
			chdigit = str.substring(9, 10);
			d2 = Integer.parseInt(chdigit);
			if (d != 0) {
				word = word.concat(one[(d - 1)]);
				word = word.concat("Hundred ");
			}
			if (d1 == 0 && d2 != 0) {
				word = word.concat(one[(d2 - 1)]);
			}
			if (d1 == 1 && d2 != 0) {
				word = word.concat(ele[(d2 - 1)]);
			}
			if (d1 != 0 && d2 == 0) {
				word = word.concat(ten[(d1 - 1)]);
			}
			if (d1 > 1 && d2 != 0) {
				word = word.concat(ten[(d1 - 1)]);
				word = word.concat(one[(d2 - 1)]);
			}
//by gk
			// When it has no decimal
			if (numSize == 1) {
				if (str.equals("0000000000")) {
					word = "Zero only";
				} else {
					word = word.concat("only");
				}
			}
			// When it has decimal
			if (i == 2) { // Last paisa
				word = word.concat("Paise Only");
			}

		}
		return word;
	}

	public static String replaceJavaScriptStringNewLine(String str) {
		// return correctNull(str).replaceAll("\n","\\\\n").replaceAll("'","\\\\'");
		String strnew = correctNull(str).replace("\\n", "<br>");
		return strnew;
	}

	public static String replaceJavaScriptAttheRate(String str) {
		// return correctNull(str).replaceAll("\n","\\\\n").replaceAll("'","\\\\'");
		String strnew = correctNull(str).replace("@", "\\\\n");
		return strnew;
	}

	public static String formatCurrencyValue(double dblvalue) {
		String strValue = "0.00";
		String strSign = "";
		if (dblvalue < 0) {
			dblvalue = dblvalue * -1;
			strSign = "-";
		}
		strValue = (strSign
				+ new DecimalFormat("0.00").format(dblvalue).replaceAll("(?!^)(?=(?:\\d{2})*\\d{3}\\.)", ",")).trim();
		return strValue;
	}

	public static String convertSetupToApplicationValues(String strValuesIn, double convertAmount) {
		double dbAmount = 0.00;
		String strtest = "";
		if (strValuesIn.equalsIgnoreCase("R")) {
			dbAmount = convertAmount;
		} else if (strValuesIn.equalsIgnoreCase("L")) {
			dbAmount = convertAmount / 100000;
		} else if (strValuesIn.equalsIgnoreCase("C")) {
			dbAmount = convertAmount / 10000000;
		} else {
			dbAmount = convertAmount;
		}
		strtest = String.valueOf(dbAmount);
		return strtest;
	}

	public static String convertApplicationToSetup(String strValuesIn, double convertAmount) {
		double dbAmount = 0.00;
		String strAmount = "";
		if (strValuesIn.equalsIgnoreCase("R")) {
			dbAmount = convertAmount;
		} else if (strValuesIn.equalsIgnoreCase("L")) {
			dbAmount = convertAmount * 100000;
		} else if (strValuesIn.equalsIgnoreCase("C")) {
			dbAmount = convertAmount * 10000000;
		}
		strAmount = String.valueOf(dbAmount);

		return strAmount;
	}

	public static String changetoTitlecase(String strDec) {
		StringBuilder SB = new StringBuilder();
		String str = strDec;
		if (!((str.equalsIgnoreCase("") || str.equalsIgnoreCase("")))) {
			for (String sbr : str.split(" ")) {
				if (SB.length() > 0) {
					SB.append(" ");
				}
				if (sbr.length() > 1) {
					SB.append(sbr.substring(0, 1).toUpperCase()).append(sbr.substring(1, sbr.length()).toLowerCase());
				} else if (!(sbr.equalsIgnoreCase(" ") || sbr.equalsIgnoreCase(""))) {
					SB.append(sbr.substring(0, 1).toUpperCase());
				}
			}
		} else {
			return "";
		}
		return SB.toString();
	}

	/* Added by Yoga for Cross-site Scripting */

	public static String encodeDataForTag(String aText) {
		aText = correctNull(aText);

		if (aText.equals("&nbsp;")) {
			aText = "&#160";
			return aText;
		}

		final StringBuffer result = new StringBuffer();

		final java.text.StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&#060;");
			} else if (character == '>') {
				result.append("&#062;");
			} else if (character == '&') {
				result.append("&#038;");
			} else if (character == '\"') {
				result.append("&#034;");
			} else if (character == '\t') {
				result.append("&#009;");
			} else if (character == '!') {
				result.append("&#033;");
			} else if (character == '#') {
				result.append("&#035;");
			} else if (character == '$') {
				result.append("&#036;");
			} else if (character == '%') {
				result.append("&#037;");
			} else if (character == '\'') {
				result.append("&#039;");
			} else if (character == '(') {
				result.append("&#040;");
			} else if (character == ')') {
				result.append("&#041;");
			} else if (character == '*') {
				result.append("&#042;");
			} else if (character == '+') {
				result.append("&#043;");
			} else if (character == ',') {
				result.append("&#044;");
			} else if (character == '-') {
				result.append("&#045;");
			} else if (character == '.') {
				result.append("&#046;");
			} else if (character == '/') {
				result.append("&#047;");
			} else if (character == ':') {
				result.append("&#058;");
			} else if (character == ';') {
				result.append("&#059;");
			} else if (character == '=') {
				result.append("&#061;");
			} else if (character == '?') {
				result.append("&#063;");
			} else if (character == '@') {
				result.append("&#064;");
			} else if (character == '[') {
				result.append("&#091;");
			} else if (character == '\\') {
				result.append("&#092;");
			} else if (character == ']') {
				result.append("&#093;");
			} else if (character == '^') {
				result.append("&#094;");
			} else if (character == '_') {
				result.append("&#095;");
			} else if (character == '`') {
				result.append("&#096;");
			} else if (character == '{') {
				result.append("&#123;");
			} else if (character == '|') {
				result.append("&#124");
			} else if (character == '}') {
				result.append("&#125");
			} else if (character == '~') {
				result.append("&#126");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}

			character = iterator.next();
		}

		return Helper.correctNull(result.toString());
	}

	public static double parseDouble(String s) {
		return parseDouble(s, 0.0);
	}

	public static double parseDouble(String s, double d) {
		double d1 = d;
		try {
			s = correctNull(s);
			if (s.trim().equals("")) {
				d1 = d;
			} else {
				d1 = Double.parseDouble(s);
			}

		} catch (NumberFormatException numberformatexception) {
			return d;
		}
		return d1;
	}

//added to avoid cross site scripting

	public static String encodeDataForHTML(String aText) {
		aText = correctNull(aText);

		if (aText.equals("&nbsp;")) {
			aText = "&#160";
			return aText;
		}

		final StringBuffer result = new StringBuffer();

		final java.text.StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("%3C");
			} else if (character == '>') {
				result.append("%3E");
			} else if (character == '&') {
				result.append("%26");
			} else if (character == '\"') {
				result.append("%22");
			} else if (character == '!') {
				result.append("%21");
			} else if (character == '#') {
				result.append("%23");
			} else if (character == '$') {
				result.append("%24");
			} else if (character == '%') {
				result.append("%25");
			} else if (character == '\'') {
				result.append("%27");
			} else if (character == '(') {
				result.append("%28");
			} else if (character == ')') {
				result.append("%29");
			} else if (character == '*') {
				result.append("%2A");
			} else if (character == '+') {
				result.append("%2B");
			} else if (character == ',') {
				result.append("%2C");
			} else if (character == '-') {
				result.append("%2D");
			} else if (character == '.') {
				result.append("%2E");
			} else if (character == '/') {
				result.append("%2F");
			} else if (character == ':') {
				result.append("%3A");
			} else if (character == ';') {
				result.append("%3B");
			} else if (character == '=') {
				result.append("%3D");
			} else if (character == '?') {
				result.append("%3F");
			} else if (character == '@') {
				result.append("%40");
			} else if (character == '[') {
				result.append("%5B");
			} else if (character == '\\') {
				result.append("%5C");
			} else if (character == ']') {
				result.append("%5D");
			} else if (character == '^') {
				result.append("%5E");
			} else if (character == '_') {
				result.append("%5F");
			} else if (character == '`') {
				result.append("%60");
			} else if (character == '{') {
				result.append("%7B");
			} else if (character == '|') {
				result.append("%7C");
			} else if (character == '}') {
				result.append("%7D");
			} else if (character == '~') {
				result.append("%7E");
			} else {
				result.append(character);
			}
			character = iterator.next();
		}

		return Helper.correctNull(result.toString());
	}

	public static String replaceSpecialChar(String strnew) {
		strnew = correctNull(strnew).replace("/", "%");
		strnew = correctNull(strnew).replace("\\", "%");
		strnew = correctNull(strnew).replace("!", "%");
		strnew = correctNull(strnew).replace("@", "%");
		strnew = correctNull(strnew).replace("#", "%");
		strnew = correctNull(strnew).replace("%", "%");
		strnew = correctNull(strnew).replace("^", "%");
		strnew = correctNull(strnew).replace("&", "%");
		strnew = correctNull(strnew).replace("*", "%");
		strnew = correctNull(strnew).replace("(", "%");
		strnew = correctNull(strnew).replace(")", "%");
		strnew = correctNull(strnew).replace("-", "%");
		strnew = correctNull(strnew).replace("_", "%");
		return strnew;
	}

	public static String replaceSpecialCharNew(String strnew) {
		strnew = correctNull(strnew).replace("/", " ");
		strnew = correctNull(strnew).replace("\\", " ");
		strnew = correctNull(strnew).replace("!", " ");
		strnew = correctNull(strnew).replace("#", " ");
		strnew = correctNull(strnew).replace("%", " ");
		strnew = correctNull(strnew).replace("^", " ");
		strnew = correctNull(strnew).replace("(", " ");
		strnew = correctNull(strnew).replace(")", " ");
		strnew = correctNull(strnew).replace("*", " ");
		strnew = correctNull(strnew).replace("[", " ");
		strnew = correctNull(strnew).replace("]", " ");
		strnew = correctNull(strnew).replace(",", " ");
		return strnew;
	}

	public static String replaceSpecialCharCIFNew(String strnew) {
		strnew = correctNull(strnew).replace("\\", "");
		strnew = correctNull(strnew).replace(".", "");
		strnew = correctNull(strnew).replace("-", "");
		strnew = correctNull(strnew).replace("/", "");
		return strnew;
	}

	public static String replaceNewLineJavaScriptString(String str) {
		return correctNull(str).replaceAll("\"", "\\\\\"").replaceAll("\r\n", "\\\\n").replaceAll("'", "\\\\'")
				.replaceAll("\n", "\\\\n");
	}

	public static String getCurrentDateTimeForCIFFormat() throws Exception {
		Calendar cal = Calendar.getInstance();
		String strSysDate = "";
		try {
			String strSysDay = String.valueOf(cal.get(Calendar.DATE));
			if (Integer.parseInt(strSysDay) < 10) {
				strSysDay = "0" + strSysDay;
			}

			String strSysMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			if (Integer.parseInt(strSysMonth) < 10) {
				strSysMonth = "0" + strSysMonth;
			}

			String strSysYear = String.valueOf(cal.get(Calendar.YEAR));

			strSysDate = strSysYear + "-" + strSysMonth + "-" + strSysDay;

			String strSysTime = String.valueOf(
					cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":" + cal.getTime().getSeconds());

			strSysDate = strSysDate + "T" + strSysTime;

			strSysDate = "2015-04-04T05:30:00";

		} catch (Exception e) {
			throw new Exception("*Exception in getCurrentDateTime **" + e);
		}
		return strSysDate;
	}

	public static String correctByteArray(byte[] byteArr) {
		String strConv = "";
		if (byteArr != null && byteArr.length > 0) {
			strConv = new String(byteArr);
		}
		return strConv.trim();
	}

	public static String getNextDateAfterAddingMonths(String strProcdate, int intMonths) {
		String strNexMonth = "", strNextDate = "", strNextDateDay = "";
		int intNextMOnth = 0, intNextMOnthNew = 0;
		int intProcDate = Integer.parseInt(strProcdate.substring(0, 2));
		int intProcMonth = Integer.parseInt(strProcdate.substring(3, 5)) - 1;
		int intProcYear = Integer.parseInt(strProcdate.substring(6, 10));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, intProcDate);
		cal.set(Calendar.MONTH, intProcMonth);
		cal.set(Calendar.YEAR, intProcYear);
		cal.add(Calendar.MONTH, intMonths);
		intNextMOnth = cal.get(Calendar.MONTH);
		intNextMOnthNew = intNextMOnth + 1;
		if (intNextMOnthNew < 10) {
			strNexMonth = "0" + String.valueOf(intNextMOnthNew);
		} else {
			strNexMonth = String.valueOf(intNextMOnthNew);
		}
		if (cal.get(Calendar.DATE) < 10) {
			strNextDateDay = "0" + cal.get(Calendar.DATE);
		} else {
			strNextDateDay = String.valueOf(cal.get(Calendar.DATE));
		}
		strNextDate = strNextDateDay + "/" + strNexMonth + "/" + String.valueOf(cal.get(Calendar.YEAR));
		return strNextDate;
	}

	public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String parsedDate = formatter.format(initDate);

		return parsedDate;
	}

	public static String addMonthWithLastDate(int intMonths) {
		String strDate = "";
		try {
			Date date = null;
			Calendar cal = Calendar.getInstance();
			// cal.setTime(date);
			cal.add(Calendar.MONTH, intMonths);
			cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(cal.DAY_OF_MONTH));
			date = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			strDate = sdf.format(date);
		} catch (Exception e) {
			LOGGER.error("Error in addMonthWithDate:" + e);
		}
		return strDate;
	}

	public static String addMonthWithCurrentDate(int intMonths) throws Exception {
		String strDate = "";
		try {
			Date date = null;
			Calendar cal = Calendar.getInstance();
			// cal.setTime(date);
			cal.add(Calendar.MONTH, intMonths);
			date = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			strDate = sdf.format(date);
		} catch (Exception e) {
			throw new Exception("Error in addMonthWithDate:" + e);
		}
		return strDate;
	}

//Round to Next lower value for specified Places (in 0's)
	public static int roundDown(double dblNumber, int intNoOfPlaces) {

		int intDigit = 10;
		if (intNoOfPlaces > 0) {

			intDigit = (int) Math.pow(intDigit, intNoOfPlaces);
		}

		return (int) (dblNumber - (dblNumber % intDigit));
	}

//Round to Next Higher value for specified Places( if greater than in terms of 5 or 50 or 500 so On...)(in 0's)
	public static int round(double dblNumber, int intNoOfPlaces) {

		int intDigit = 10;
		int intMidvalue = 5;
		if (intNoOfPlaces > 0) {
			intDigit = (int) Math.pow(intDigit, intNoOfPlaces);
			intMidvalue = ((intMidvalue * intDigit) / 10);
		}

		if ((dblNumber % intDigit) >= intMidvalue)
			return (int) (dblNumber - (dblNumber % intDigit) + intDigit);
		else
			return (int) (dblNumber - (dblNumber % intDigit));
	}

	public static String correctError(String error) {
		String strError = "";
		if (!correctNull(error).equals("")) {
			strError = error.replaceAll("\"", "").replaceAll("\'", "").replaceAll(";", ":");
		}
		return strError;
	}

	public static String cibilreportScoringFactor(String strReasonCode) {
		String strScoringFactor = "";
		if (strReasonCode.equalsIgnoreCase("01")) {
			strScoringFactor = "Not enough credit card debt experience";
		} else if (strReasonCode.equalsIgnoreCase("02")) {
			strScoringFactor = "Length of time since most recent account delinquency is too short";
		} else if (strReasonCode.equalsIgnoreCase("03")) {
			strScoringFactor = "Too many two-wheeler accounts";
		} else if (strReasonCode.equalsIgnoreCase("04")) {
			strScoringFactor = "Too many business loans";
		} else if (strReasonCode.equalsIgnoreCase("05")) {
			strScoringFactor = "Credit card account balances too high in proportion to credit limits";
		} else if (strReasonCode.equalsIgnoreCase("06")) {
			strScoringFactor = "Maximum amount on mortgage loan is low";
		} else if (strReasonCode.equalsIgnoreCase("07")) {
			strScoringFactor = "Total amount past due is too high";
		} else if (strReasonCode.equalsIgnoreCase("08")) {
			strScoringFactor = "Not enough mortgage debt experience";
		} else if (strReasonCode.equalsIgnoreCase("09")) {
			strScoringFactor = "Too much change of indebtedness on non-mortgage trades over the past 24 months";
		} else if (strReasonCode.equalsIgnoreCase("10")) {
			strScoringFactor = "Insufficient improvement in delinquency status";
		} else if (strReasonCode.equalsIgnoreCase("11")) {
			strScoringFactor = "Too much increase of indebtedness on non-mortgage trades over the past 12 months";
		} else if (strReasonCode.equalsIgnoreCase("12")) {
			strScoringFactor = "Too many enquiries";
		} else if (strReasonCode.equalsIgnoreCase("13")) {
			strScoringFactor = "Too many accounts with a balance";
		} else if (strReasonCode.equalsIgnoreCase("14")) {
			strScoringFactor = "Length of time accounts have been established is too short";
		} else if (strReasonCode.equalsIgnoreCase("15")) {
			strScoringFactor = "Not enough debt experience";
		} else if (strReasonCode.equalsIgnoreCase("16")) {
			strScoringFactor = "Too many credit card accounts";
		} else if (strReasonCode.equalsIgnoreCase("17")) {
			strScoringFactor = "Too many Personal Loan enquiries";
		} else if (strReasonCode.equalsIgnoreCase("18")) {
			strScoringFactor = "Number of active trades with a balance too high in proportion to total trades";
		} else if (strReasonCode.equalsIgnoreCase("19")) {
			strScoringFactor = "Too much change of indebtedness on credit cards over the past 24 months";
		} else if (strReasonCode.equalsIgnoreCase("20")) {
			strScoringFactor = "Credit card balance too high";
		} else if (strReasonCode.equalsIgnoreCase("21")) {
			strScoringFactor = "Proportion of delinquent trades too high";
		}
		return strScoringFactor;
	}

	public static String correctCBSData(String value, int maxLength) {

		if (!correctNull(value).equals("") && value.length() > maxLength) {
			value = value.substring(0, maxLength - 1);
		}
		return value;
	}

	public static String convertObjectToXml(Object obj) throws Exception {
		Class[] classes = new Class[1];
		classes[0] = obj.getClass();
		JAXBContext ctx = JAXBContext.newInstance(classes);
		Marshaller jaxbMarshaller = ctx.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
//    jaxbMarshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new NamespaceMapper());
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(obj, sw);
		return sw.toString();
	}

	public static Object convertXmlToObject(String xml, Object obj) throws Exception {
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//	return jaxbUnmarshaller.unmarshal(new File("D://failure_response.xml"));
		return jaxbUnmarshaller.unmarshal(is);
	}

	public static String getHttpResponse(String strUrl, String strJsonRequest) throws Exception {
		String jsonResponse = "";
		String charset = "UTF-8";
		System.out.println("ESB_URL :" + strUrl);
		HttpURLConnection connection = (HttpURLConnection) new URL(strUrl).openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
		connection.setRequestMethod("POST");
		OutputStream os = (OutputStream) connection.getOutputStream();
		os.write(strJsonRequest.getBytes(charset));
		os.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sbResponse = new StringBuilder();
		String resLine = "";
		while ((resLine = br.readLine()) != null) {
			sbResponse.append(resLine);
		}
		if (br != null)
			br.close();
		connection.disconnect();
		jsonResponse = sbResponse.toString().trim();
		return jsonResponse;
	}

	/**
	 * @param Double value (eg. 15485.25)
	 * @return Double value(eg. 15486)
	 * 
	 *         This Method gets the double value as input and rount the value to
	 *         next higher value
	 */
	public static String roundToMaxVal(String str) {
		String rVal = "", strValue = "", strDecimalval;
		if (str != null && !str.equals("")) {

			strValue = str.substring(0, str.lastIndexOf("."));
			strDecimalval = str.substring(str.lastIndexOf("."), str.length());

			double dVal = Double.parseDouble(strValue);
			if (dVal > 0) {
				if (Double.parseDouble(strDecimalval) > 0) {
					dVal = Double.parseDouble(strValue) + 1;
				}
			} else {
				if (Double.parseDouble(strDecimalval) > 0) {
					dVal = Double.parseDouble(strValue) - 1;
				}
			}
			rVal = String.valueOf(Math.round(dVal)) + ".00";

		}
		return rVal;
	}

	public static double correctMath(double doubleValue) {

		return ((doubleValue * 10) / 10);
	}

	/**
	 * @param intMinimumFractionDigit
	 * @param intMaximumFractionDigit
	 * @param booleanSetGrouping
	 * @return Number Format Object
	 */
	private static java.text.NumberFormat getNumberFormat(int intMinimumFractionDigit, int intMaximumFractionDigit,
			boolean booleanSetGrouping) {
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setMinimumFractionDigits(intMinimumFractionDigit);
		nf.setMaximumFractionDigits(intMaximumFractionDigit);
		nf.setGroupingUsed(booleanSetGrouping);

		return nf;
	}

	/**
	 * @param stringNumber
	 * @return Formatted Number in String
	 */
	public static String numberFormat(String stringNumber) {
		java.text.NumberFormat nf = getNumberFormat(2, 2, false);
		String stringFormattedNumber = "";
		try {
			double doubleNumber = Double.parseDouble(stringNumber);
			stringFormattedNumber = nf.format(doubleNumber);

		} catch (NumberFormatException numberFormatException) {

			return stringNumber;

		} catch (Exception exception) {
			return stringNumber;
		}
		return stringFormattedNumber;
	}

	/**
	 * @param stringNumber
	 * @return Formatted Number in String
	 */
	public static String numberFormat(double doubleNumber) {
		java.text.NumberFormat nf = getNumberFormat(2, 2, false);
		String stringFormattedNumber = "";
		try {
			stringFormattedNumber = nf.format(doubleNumber);

		} catch (NumberFormatException numberFormatException) {

			return String.valueOf(doubleNumber);

		} catch (Exception exception) {
			return String.valueOf(doubleNumber);
		}
		return stringFormattedNumber;
	}

	/******
	 * comma separation for amount****
	 **/
	public static String rupeeFormat(String value) {
		value = value.replace(",", "");
		char lastDigit = value.charAt(value.length() - 1);
		String result = "";
		int len = value.length() - 1;
		int nDigits = 0;

		for (int i = len - 1; i >= 0; i--) {
			result = value.charAt(i) + result;
			nDigits++;
			if (((nDigits % 2) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		return (result + lastDigit);
	}

	public static Timestamp getSystemDate() {
		return new Timestamp(System.currentTimeMillis());
	}
	public static String getDateTime()
	  {
	    String[] mon = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	    
	    String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	    
	    TimeZone t = TimeZone.getDefault();
	    Calendar calendar = new GregorianCalendar(t);
	    Date trialTime = new Date();
	    StringBuffer sbufFilename = new StringBuffer();
	    
	    calendar.setTime(trialTime);
	    sbufFilename.append(day[(calendar.get(5) - 1)]);
	    sbufFilename.append(mon[calendar.get(2)].toUpperCase());
	    sbufFilename.append(Integer.toString(calendar.get(1)));
	    sbufFilename.append("_" + Integer.valueOf(calendar.get(11)).toString());
	    sbufFilename.append(Integer.valueOf(calendar.get(12)).toString());
	    sbufFilename.append(Integer.valueOf(13).toString());
	    sbufFilename.append(Integer.valueOf(14).toString());
	    
	    return sbufFilename.toString();
	  }

}