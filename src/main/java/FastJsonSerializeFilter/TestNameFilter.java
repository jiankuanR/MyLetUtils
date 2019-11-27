package FastJsonSerializeFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: TestNameFilter
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class TestNameFilter {

	public static void main(String[] args) {
		User user = new User();
		user.setId(9L);
		user.setName("渐宽");

		// 序列化的时候传入filter
		String jsonString = JSON.toJSONString(user);
		System.out.println("普通序列化：" + jsonString + "\n");

		NameFilter filter = (object, name, value) -> {
			System.out.println("----------------object=" + object);
			System.out.println("----------------name=" + name);
			System.out.println("----------------value=" + value);
			System.out.println("");
			// 属性是id是修改id的名字
			if ("id".equals(name)) {
				return name + "$";
			}
			return name;
		};
		// 序列化的时候传入filter
		jsonString = JSON.toJSONString(user, filter);
		System.out.println("NameFilter序列化：" + jsonString + "\n");

		// fastjson内置一个PascalNameFilter，用于输出将首字符大写的Pascal风格
		// 序列化的时候传入filter
		jsonString = JSON.toJSONString(user, new PascalNameFilter());
		System.out.println("PascalNameFilter序列化：" + jsonString + "\n");
	}
}
