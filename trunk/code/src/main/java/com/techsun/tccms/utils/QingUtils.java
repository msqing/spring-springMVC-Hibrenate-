package com.techsun.tccms.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class QingUtils {
	
	public static JSONObject userAgentConfig;

	/**
	 * 获得目前的年份和月份的组合
	 * @return
	 */
	public static String getYearMonth(){
		return getYearMonth(0);
	}
	
	/**
	 * 获得年份和月份的组合，可用n调整月份
	 * @param n
	 * @return
	 */
	public static String getYearMonth(int n){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, n);
		String yearMonth = calendar.get(Calendar.YEAR) + "年" + formatNum(calendar.get(Calendar.MONTH) + 1) + "月";
		return yearMonth;
	}
	
	public static String getCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "";
	}
	
	public static String getYearFromPhaseNum(String phaseNum){
		Assert.notNull(phaseNum,"参数不能为空");
		return phaseNum.replaceAll("年.*", "");
	}
	
	public static Long nullToZero(Long num){
		Long rv = 0L;
		if(num != null){
			rv = num;
		}
		return rv;
	}
	/**
	 * 将10以下的数字补0
	 * @return
	 */
	public static String formatNum(int n){
		return n < 10 ? "0" + n : n + "";
	}
	

	/**
	 * 输入流转String
	 * @param in
	 * @return
	 */
	public static String inputStream2String(InputStream in, String charsetName) throws IOException{
		StringBuffer out = new StringBuffer();
		try {
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n, charsetName));
			}
		} finally {
			if(in != null){
				in.close();
			}
		}
		return out.toString();
	}
	
	public static String inputStream2String(InputStream in) throws IOException{
		return inputStream2String(in, "UTF-8");
	}
	/**
	 * 获取扩展名
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		if (null == fileName) {
			return "";
		}
		return StringUtils.lowerCase(StringUtils.trimToEmpty(StringUtils
				.substring(fileName, StringUtils.lastIndexOf(fileName, "."))));
	}
	
	/**
	 * 生成随机号
	 * 
	 * @param sLen
	 * @return
	 */
	public static String randomKey(int sLen) {
		String base;
		String temp;
		int i;
		int p;

		base = "1234567890abcdefghijklmnopqrstuvwxyz";
		temp = "";
		for (i = 0; i < sLen; i++) {
			p = (int) (Math.random() * 37);
			if (p > 35)
				p = 35;
			temp += base.substring(p, p + 1);
		}
		return temp;
	}
	
	
	/**
	 * http请求
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String httpGet(String path, String charsetName) throws IOException{
		String rv = null;
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream input = null;
		try {
			url = new URL(path);
			httpConnection = (HttpURLConnection) url.openConnection();
			input = httpConnection.getInputStream();
			rv = QingUtils.inputStream2String(input, charsetName);
		}  finally {
			if(input != null){
				input.close();
			}
		}
		return rv;
	}
	public static String httpGet(String path) throws IOException{
		return httpGet(path, "UTF-8");
	}
	

	/**
	 * 发送短消息
	 * @param mobile
	 * @param userName
	 * @param message
	 * @throws IOException 
	 */
	public static String sendSms(String mobile, String userName, String message) throws IOException{
		StringBuffer path = new StringBuffer("http://message.changning.sh.cn/index.jsp?sender=" );
		path.append(URLEncoder.encode("长宁电子地图", "UTF-8") + "&");
		if(StringUtils.isNotEmpty(mobile)){
			path.append("mobile=" + mobile + "&");
		}
		if(StringUtils.isNotEmpty(userName)){
			path.append("userName=" + userName + "&");
		}
		path.append("message=" + URLEncoder.encode(message, "UTF-8"));
		return QingUtils.httpGet(path.toString());
	}
	
	

	/**
	 * 转换日期
	 * @param strDate
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2date(String strDate, String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(strDate);
	}
	
	/**
	 * 转换日期
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2date(String strDate) throws ParseException{
		return string2date(strDate, "yyyy-MM-dd");
	}
	
	/**
	 * 第一个字母大写
	 * @param str
	 * @return
	 */
	public static String upperFirstLetter(String str){
		if(StringUtils.isEmpty(str)){
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String nullToSpace(Object obj){
		return obj == null ? "" : obj.toString();
	}
	
	/**
	 * 将字符串转换成xml文档
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
	
	public static String monthFirstDay(){
		return monthFirstDay(0);
	}
	
	public static String monthFirstDay(int n){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, n);
		return calendar.get(Calendar.YEAR) + "-" + formatNum(calendar.get(Calendar.MONTH) + 1) + "-01";
	}
	
	public static String addPercent(String key){
		if(StringUtils.isNotEmpty(key)){
			return "%" + key + "%";
		} else {
			return key;
		}
	}
	
	public static boolean isMobile(String mobile){
		return patternTest("^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}$", mobile);
	}
	
	public static boolean patternTest(String reg, String input) {
		return Pattern.compile(reg).matcher(input).matches();
	}
	public static JSONObject requestHeadersToJSON(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Enumeration<?> names = request.getHeaderNames();
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();
			json.put(name, request.getHeader(name));
		}
		return json;
	}
	public static JSONObject requestToJSON(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("headers", requestHeadersToJSON(request));
		json.put("characterEncoding", request.getCharacterEncoding());
		json.put("contentLength", request.getContentLength());
		json.put("contentType", request.getContentType());
		json.put("localAddr", request.getLocalAddr());
		json.put("locale", request.getLocale());
		json.put("localName", request.getLocalName());
		json.put("localPort", request.getLocalPort());
		json.put("parameterMap", request.getParameterMap());
		json.put("protocol", request.getProtocol());
		json.put("remoteAddr", request.getRemoteAddr());
		json.put("remoteHost", request.getRemoteHost());
		json.put("remotePort", request.getRemotePort());
		json.put("scheme", request.getScheme());
		json.put("serverName", request.getServerName());
		json.put("serverPort", request.getServerPort());
		json.put("ecure", request.isSecure());
		json.put("authType", request.getAuthType());
		json.put("contextPath", request.getContextPath());
		json.put("cookies", request.getCookies());
		json.put("method", request.getMethod());
		json.put("pathInfo", request.getPathInfo());
		json.put("pathTranslated", request.getPathTranslated());
		json.put("queryString", request.getQueryString());
		json.put("remoteUser", request.getRemoteUser());
		json.put("requestedSessionId", request.getRequestedSessionId());
		json.put("requestURI", request.getRequestURI());
		json.put("requestURL", request.getRequestURL().toString());
		json.put("servletPath", request.getServletPath());
		json.put("userPrincipal", request.getUserPrincipal());
		return json;
	}
	public static JSONObject userAgentFromString(String userAgent) throws IOException{
		if(userAgentConfig == null){
			userAgentConfig = JSONObject.fromObject(QingUtils.inputStream2String(QingUtils.class.getResourceAsStream("/useragent.json")));
		}
		JSONObject json = new JSONObject();
		JSONArray browser = userAgentConfig.getJSONArray("browser");
		json.put("browser", parseJSONArray(browser, userAgent));
		JSONArray os = userAgentConfig.getJSONArray("os");
		json.put("os", parseJSONArray(os, userAgent));
		return json;
	}
	
	private static String parseJSONArray(JSONArray ja, String userAgent){
		for (int i = 0; i < ja.size(); i++) {
			JSONObject item = ja.getJSONObject(i);
			JSONArray regs = item.getJSONArray("regs");
			for (int j = 0; j < regs.size(); j ++) {
				Pattern compile = Pattern.compile(regs.getString(j), Pattern.CASE_INSENSITIVE);
				Matcher matcher = compile.matcher(userAgent);
				if(matcher.find()){
					if(j == regs.size() - 1){
						if(matcher.groupCount() > 0){
							return item.getString("result") + " " + matcher.group(1);
						}
						return item.getString("result");
					}
				} else {
					break;
				}
			}
		}
		return "Unknown";
	}
	public static void downloadUserAgentAndReload(String url) throws IOException{
		String userAgent = httpGet(url);
		userAgentConfig = JSONObject.fromObject(userAgent);
		String path = QingUtils.class.getResource("/useragent.json").getPath();
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			fw.write(userAgent);
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}
	
	public static void copyProperties(Object dest, String[] names, Object src) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		for(String name : names){
			if(BeanUtils.getProperty(src, name) != null){
				BeanUtils.copyProperty(dest, name, BeanUtils.getProperty(src, name));
			}
		}
	}
}
