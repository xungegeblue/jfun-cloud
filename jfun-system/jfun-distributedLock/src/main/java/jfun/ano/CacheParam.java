package jfun.ano;

import java.lang.annotation.*;

/**
 * @Auther: miv
 * @Date: 2019-05-06 17:51
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 锁的参数
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    //字段名称
    String name() default "";
}
