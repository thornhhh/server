package com.call110.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * jsp界面上用到的 数值转换成对应的文字
 * 
 * @Description
 * @date 2016年5月9日
 * @author zuoshiqi
 * 
 */
public class DictionaryUtil {

	private static DictionaryUtil instance = null;
	private Map<String, String> MUL_LAN = null;
	private Map<String, String> EDU = null;
	// private Map<String, String> MUL_EDU = null;
	private Map<String, String> POSITION_PROPERTY = null;
	private Map<String, String> FOR_THE_CROWD = null;
	private static final String[] COMPANY_NATURE_LABELS = { "国企", "合资", "民营", "外企", "初创", "500强", "政府", "非盈利", "上市", "其他" };
	private static final String[] BENEFITS = { "五险一金", "年终奖金", "员工股份", "带薪年假", "加班补助", "假日福利", "工时灵活", "股票期权", "周末双休", "搬家补贴", "企业年金","Early Joining" };
	private static final String[] LANS = { "中文", "英语", "阿拉伯语", "日语", "法语", "德语", "俄语", "西班牙语", "印地语", "葡萄牙语", "粤语", "其他语" };
	private static final String[] APPLY_INTENTION = { "正常人", "其他困难人群", "盲人", "聋哑人", "下肢残疾", "上肢残疾", "智力障碍", "怀孕", "退休人士" };
	private static final String[] SEX = { "女", "男" };
	private static final String[] JOBTYPE = { "全职", "兼职","实习","志愿" };

	/**
	 * @Description 得到性别
	 */
	public static String getSex(Integer sex) {
		if (sex == null || sex.intValue() > SEX.length) {
			return "未填写";
		} else {
			return SEX[sex.intValue()];
		}
	}
	
	/**
	 * @Description 得到工作类型
	 */
	public static String getJobType(Integer jobType) {
		if (jobType == null || jobType.intValue() > JOBTYPE.length) {
			return "未填写";
		} else {
			return SEX[jobType.intValue()];
		}
	}

	/**
	 * @Description 得到字符串，如果字符串是 空的，返回 无
	 */
	public static String getString(String value) {
		if (value == null || value.endsWith("")) {
			return "无";
		} else {
			return value.toString();
		}
	}

	/**
	 * 将被乘以10的评价，显示回应有的数值
	 * 
	 * @return 返回样例:49 --> 4.9
	 */
	public static String formatPingJia(Integer value) {
		if (value == null || value.intValue() == 0) {
			return "0.0";
		} else if (value > 50) {
			return "5.0";
		} else {
			return value / 10 + "." + value % 10;
		}
	}

	static {
		if (instance == null) {
			instance = new DictionaryUtil();
		}
		instance.initLAN();
		instance.initEDU();
		instance.initPP();
		instance.initForTheCrowd();
	}

	private void initLAN() {
		MUL_LAN = new HashMap<String, String>();
		MUL_LAN.put("zh", "中文,Chinese");
		MUL_LAN.put("en", "英语,English");
		MUL_LAN.put("ar", "阿拉伯语,Arabic");
		MUL_LAN.put("ja", "日语,Japanese");
		MUL_LAN.put("fr", "法语,French");
		MUL_LAN.put("de", "德语,German");
		MUL_LAN.put("ru", "俄语,Russian");
		MUL_LAN.put("es", "西班牙语,Spanish");
		MUL_LAN.put("hi", "印地语,Hindi");
		MUL_LAN.put("pt", "葡萄牙语,Portuguese");
		MUL_LAN.put("yue", "粤语,Cantonese");
		MUL_LAN.put("ko", "韩语,Korean");
		MUL_LAN.put("vi", "越南语,Vietnamese");
		MUL_LAN.put("th", "泰语,Thai");
		MUL_LAN.put("fa", "波斯语,Farsi");
		MUL_LAN.put("ms", "马来语,Malay");
		MUL_LAN.put("jv", "爪哇语,Javanese");
		MUL_LAN.put("tr", "土耳其,Turkish");
		MUL_LAN.put("it", "意大利语,Italian");
		MUL_LAN.put("bn", "孟加拉语,Bengali");
		MUL_LAN.put("mr", "马拉地语,Marathi");
		MUL_LAN.put("ta", "泰米尔语,Tamil");
		MUL_LAN.put("ur", "乌尔都语,Urdu");
		MUL_LAN.put("others", "");
	}

	private void initEDU() {
		EDU = new HashMap<String, String>();
		EDU.put("0", "培训");
		EDU.put("1", "初中及以下");
		EDU.put("2", "高中");
		EDU.put("3", "中技");
		EDU.put("4", "中专");
		EDU.put("5", "大专");
		EDU.put("6", "本科");
		EDU.put("7", "硕士");
		EDU.put("8", "MBA");
		EDU.put("8", "博士");
	}

	private void initPP() {
		// 0-所有、1-全职、2-兼职、3-实习、4-志愿
		POSITION_PROPERTY = new HashMap<String, String>();
		POSITION_PROPERTY.put("0", "全职");
		POSITION_PROPERTY.put("1", "兼职");
		POSITION_PROPERTY.put("2", "实习");
		POSITION_PROPERTY.put("3", "志愿");
	}

	private void initForTheCrowd() {
		// 0-正常人，1-视力障碍，2-听力障碍，3-行走障碍，4-智力障碍，5-肢体困难、6-退休,7-怀孕,8-其他
		FOR_THE_CROWD = new HashMap<String, String>();
		FOR_THE_CROWD.put("0", "正常人");
		FOR_THE_CROWD.put("1", "视力障碍");
		FOR_THE_CROWD.put("2", "听力障碍");
		FOR_THE_CROWD.put("3", "行走障碍");
		FOR_THE_CROWD.put("4", "智力障碍");
		FOR_THE_CROWD.put("5", "肢体困难");
		FOR_THE_CROWD.put("6", "退休");
		FOR_THE_CROWD.put("7", "怀孕");
		FOR_THE_CROWD.put("8", "其他");
	}

	/**
	 * @Description: 获取语言
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getLan(String code) {
		if (StringUtils.isNotBlank(code)) {
			String lan = instance.MUL_LAN.get(code);
			return StringUtils.isBlank(lan) ? "" : lan;
		}
		return "";
	}

	/**
	 * @Description: T获取职位性质
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getPropertyLabel(String code) {
		String[] codes = code.split(",");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < codes.length; i++) {
			String lan = instance.MUL_LAN.get(codes[i]);
			if (StringUtils.isNotBlank(lan)) {
				buf.append(lan).append(",");
			}
		}
		return buf.toString();
	}

	/**
	 * @Description: 获取适合人群
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getForTheCrowdLabel(String code) {
		String[] codes = code.split(",");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < codes.length; i++) {
			String temp = instance.FOR_THE_CROWD.get(codes[i]);
			if (StringUtils.isNotBlank(temp)) {
				buf.append(temp).append(",");
			}
		}
		return buf.toString();
	}

	/**
	 * @Description: 获取学历
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getEdu(String code) {
		if (StringUtils.isNotBlank(code)) {
			String property = instance.EDU.get(code);
			return StringUtils.isBlank(property) ? "" : property;
		}
		return "";
	}

	/**
	 * @Description: 获取企业性质
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getCompanyNatureLabel(String code) {
		StringBuffer buf = new StringBuffer();
		String[] codes = code.split(",");
		for (int i = 0; i < codes.length; i++) {
			if (StringUtils.isNumeric(codes[i])) {
				buf.append(COMPANY_NATURE_LABELS[i]).append(",");
			}
		}
		if (buf.length() > 0) {
			return buf.substring(0, buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * @Description: 获取待遇说明
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getBenefitsLabel(String code) {
		StringBuffer buf = new StringBuffer();
		String[] codes = code.split("");
		for (int i = 0; i < codes.length; i++) {
			if ("1".equals(codes[i])) {
				buf.append(BENEFITS[i]).append(",");
			}
		}
		return buf.toString();
	}

	/**
	 * @Description: 获取语言
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getLanguageLabel(String code) {
		StringBuffer buf = new StringBuffer();
		String[] codes = code.split("");
		for (int i = 0; i < codes.length; i++) {
			if ("1".equals(codes[i])) {
				buf.append(LANS[i]).append(",");
			}
		}
		return buf.toString();
	}

	/**
	 * @Description: 个人意向
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getApplyIntention(String code) {
		StringBuffer buf = new StringBuffer();
		String[] codes = code.split(",");
		for (int i = 0; i < codes.length; i++) {
			if ("1".equals(codes[i])) {
				buf.append(APPLY_INTENTION[i]).append(",");
			}
		}
		return buf.toString();
	}

	/**
	 * @Description: 个人意向
	 * @param code
	 * @return
	 * @return String
	 * @author Zane
	 * @date 2015年12月9日
	 */
	public static String getSexLabel(String code) {
		return SEX[Integer.valueOf(code)];
	}
}
