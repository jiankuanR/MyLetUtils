package sensitive;

import java.lang.annotation.*;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: SensitiveInfo
 * @create 2019/11/27
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveInfo {

	public sensitiveType Type();

}
