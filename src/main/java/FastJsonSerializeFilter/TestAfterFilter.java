package FastJsonSerializeFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: TestAfterFilter
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class TestAfterFilter {

	public static void main(String[] args) {
		User user = new User();
		user.setId(9L);
		user.setName("渐宽");

		// 序列化的时候传入filter
		String jsonString = JSON.toJSONString(user);
		System.out.println("普通序列化：" + jsonString + "\n");

		AfterFilter filter = new AfterFilter() {

			@Override
			public void writeAfter(Object object) {

				User user = (User) object;
				System.out.println("------------User.id=" + user.getId() + " " + "User.name=" + user.getName() + "\n");
				user.setName(user.getName() + "$$$");
			}
		};

		// 序列化的时候传入filter
		jsonString = JSON.toJSONString(user, filter);
		System.out.println("AfterFilter序列化：" + jsonString + "\n");
		System.out.println(user);
	}
}
