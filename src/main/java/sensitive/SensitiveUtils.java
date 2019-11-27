package sensitive;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: SensitiveUtils
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class SensitiveUtils {

	/**
	 * 说明：    用户名：只显示第一个，其他隐藏为星号<例子：李**>
	 * @param ss
	 * @return
	 * 2016年9月8日 下午5:40:29
	 */
	public static String name(String ss){
		if(StringUtils.isBlank(ss)){
			return "";
		}
		int length=StringUtils.length(ss);
		String newStr=StringUtils.left(ss, 1);
		return StringUtils.rightPad(newStr, length, "*");
		//return ss.replaceAll("(.)", "*");
	}

	/**
	 * 说明：    默认全部字符串转换为* <例子：1234 ----》 **** >
	 * @param strDefault
	 * @return
	 * @date  2016年9月9日 下午2:36:55
	 */
	public static String defaultType(String strDefault){
		return strDefault.replaceAll("(.)", "*");

	}
	/**
	 * 说明：     身份证号码：显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
	 * @param id
	 * @return
	 * 2016年9月8日 下午5:39:27
	 */
	public static String idCardNum(String id) {
		if (StringUtils.isBlank(id)) {
			return "";
		}
		String num = StringUtils.right(id, 4);
		return StringUtils.leftPad(num, StringUtils.length(id), "*");
	}

	/**
	 * 说明：    [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
	 * @param num 手机号码
	 * @return
	 * @date  2016年9月8日 下午5:47:06
	 */
	public static String mobilePhone(String num) {
		if (StringUtils.isBlank(num)) {
			return "";
		}
		return StringUtils.left(num,3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num,4), StringUtils.length(num), "*"), "***"));
	}

	/**
	 * 说明：    参数类型是Integer（长度值小于11位）
	 * [固定电话] 后四位，其他隐藏<例子：****1234>
	 * @param num
	 * @return
	 * @date  2016年9月9日 上午10:35:23
	 */
	public static String fixedPhone(String num) {
		if (StringUtils.isBlank(num)) {
			return "";
		}
		int length=StringUtils.length(num);
		//从右边截取制定长度的字符串
		String right = StringUtils.right(num, 4);
		//如果参数1长度小于参数2，那么全部用*号替换
		return StringUtils.leftPad(right, length, "*");
	}

	/**
	 * 说明：    [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
	 * @param cardNum 银行卡号
	 * @return
	 * @date  2016年9月8日 下午5:48:26
	 */
	public static String bankCard(String cardNum) {
		if (StringUtils.isBlank(cardNum)) {
			return "";
		}
		return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
	}

	/**
	 * 说明：     [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
	 * @param code 公司开户银行联号
	 * @return
	 * @date  2016年9月8日 下午5:50:10
	 */
	public static String cnapsCode(String code) {
		if (StringUtils.isBlank(code)) {
			return "";
		}
		return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
	}

	public static String getJavaBean(Object object) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException  {

		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder str= new StringBuilder();
		for (Field field : fields) {
			field.setAccessible(true);
			//获取字段上的注解
			SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
			//获取字段的属性名称
			String fieldName=field.getName();
			//获取字段的值并转换为String类型
			String value = fieldType(object, field, fieldName);
			//判断是否存在注解
			if(null!=annotation){
				//获取注解的具体类型
				sensitiveType Type = annotation.Type();
				//匹配注解类型
				switch(Type){
					case name:{
						//替换为*号，重新输出
						String valueStr = SensitiveUtils.name(value);
						str.append(fieldName).append(":").append(valueStr).append(",");
						break;
					}
					case idCardNum:{
						//替换为*号，重新输出
						String valueStr = SensitiveUtils.idCardNum(value);
						str.append(fieldName).append(":").append(valueStr).append(",");
						break;
					}
					case mobilePhone:{
						//替换为*号，重新输出
						String valueStr = SensitiveUtils.mobilePhone(value);
						str.append(fieldName).append(":").append(valueStr).append(",");
						break;
					}
					case fixedPhone:{
						//替换为*号，重新输出
						String valueStr = SensitiveUtils.fixedPhone(value);
						str.append(fieldName).append(":").append(valueStr).append(",");
						break;
					}
					default:{
						String valueStr = SensitiveUtils.defaultType(value);
						str.append(fieldName).append(":").append(valueStr).append(",");
						break;
					}
				}
			};
			if(null==annotation){
				str.append(fieldName).append(":").append(value).append(",");
			}
		}
		return str.toString();
	}

	private static String fieldType(Object object, Field field, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		//获取字段的类型
		Class<?> fieldClazz = field.getType();
		String value="";
		//通过属性名获取私有属性字段的属性值
		Field f1 = object.getClass().getDeclaredField(fieldName);
		f1.setAccessible(true);
		//判断字段具体类型，最终将字段值全部转换为String类型。
		if(fieldClazz.equals(String.class)){
			value = (String) f1.get(object);
		}
		if(fieldClazz.equals(Integer.class)){
			Integer valueInteger = (Integer) f1.get(object);
			value=valueInteger.toString();
		}
		if(fieldClazz.equals(Long.class)){
			Long valueLong = (Long) f1.get(object);
			value=valueLong.toString();
		}
		if(fieldClazz.equals(int.class)){
			int valueLong = (int) f1.get(object);
			value=String.valueOf(valueLong);
		}
		return value;
	}
}

	enum sensitiveType {

		/**
		 *
		 */
		name,idCardNum,mobilePhone,bankCard,cnapsCode,fixedPhone,defaultType;

	}

