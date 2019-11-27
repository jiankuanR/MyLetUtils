package FastJsonSerializeFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: TestValueFilter
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class TestValueFilter {

	public static void main(String[] args) {
		User user = new User();
		user.setId(9L);
		user.setName("渐宽");

		// 序列化的时候传入filter
		String jsonString = JSON.toJSONString(user);
		System.out.println("普通序列化：" + jsonString + "\n");

		ValueFilter filter = (object, name, value) -> {
			System.out.println("----------------object=" + object);
			System.out.println("----------------name=" + name);
			System.out.println("----------------value=" + value);
			System.out.println("");
			// 属性是id时修改id的值
			if ("id".equals(name)) {
				long id = (Long) value;
				return id + "$";
			}
			return value;
		};

		// 序列化的时候传入filter
		jsonString = JSON.toJSONString(user, filter);
		System.out.println("ValueFilter序列化：" + jsonString + "\n");
	}
}
