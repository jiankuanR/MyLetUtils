package FastJsonSerializeFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: TestPropertyFilter
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class TestPropertyFilter {

	public static void main(String[] args) {
		PropertyFilter filter = (source, name, value) -> {

			System.out.println("----------------source=" + source);
			System.out.println("----------------name=" + name);
			System.out.println("----------------value=" + value);
			System.out.println("");
			// 属性是id并且大于等于100时进行序列化
			if ("id".equals(name)) {
				long id = (Long) value;
				return id >= 100;
			}
			return false;
		};

		User user = new User();
		user.setId(9L);
		user.setName("渐宽");

		// 序列化的时候传入filter
		String jsonString = JSON.toJSONString(user, filter);
		System.out.println("序列化,id=9：" + jsonString + "\n");

		user.setId(200L);
		// 序列化的时候传入filter
		jsonString = JSON.toJSONString(user, filter);
		System.out.println("序列化,id=200：" + jsonString);
	}
}
