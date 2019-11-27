package FastJsonSerializeFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.BeforeFilter;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: TestBeforeFilter
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class TestBeforeFilter {

	public static void main(String[] args) {
		User user = new User();
		user.setId(9L);
		user.setName("渐宽");

		// 序列化的时候传入filter
		String jsonString = JSON.toJSONString(user);
		System.out.println("普通序列化：" + jsonString + "\n");

		BeforeFilter filter = new BeforeFilter() {

			@Override
			public void writeBefore(Object object) {

				System.out.println("----------------object=" + object);

				User user = (User) object;
				System.out.println("----------------User.id=" + user.getId() + " " + "User.name=" + user.getName() + "\n");
				user.setName(user.getName() + "$$$");
			}
		};

		// 序列化的时候传入filter
		jsonString = JSON.toJSONString(user, filter);
		System.out.println("BeforeFilter序列化：" + jsonString + "\n");
	}
}
