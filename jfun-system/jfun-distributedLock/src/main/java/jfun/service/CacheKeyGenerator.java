package jfun.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Auther: miv
 * @Date: 2019-05-06 17:53
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: Key 生成器
 */
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);

}
