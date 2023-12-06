package site.cilicili.common.config.dynamicDb.annotation;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import site.cilicili.common.config.dynamicDb.DbThreadLocalContextHolder;

import java.lang.reflect.Method;

/**
 * PROJECT:chart
 * PACkAGE:com.example.config.dynamicDb.annotation
 * Date:2023/11/27 17:34
 * EMAIL:
 *
 * @author BaiYiChen
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class DynamicAspect {
    private final DbChangeConfig dbChangeConf;

    @Pointcut("@annotation(site.cilicili.common.config.dynamicDb.annotation.DBUSE)")
    public void pointCut() {
    }

    @Pointcut("execution( * site.cilicili.backend.controller..*(..))")
    public void pointCutBackend() {
    }

    @Pointcut("execution( * site.cilicili.common.db.service..*(..)) || execution( * site.cilicili.common.config.dynamicDb.dataSource..*(..))")
    public void common() {
    }

    // @Pointcut("within(site.cilicili.frontend..*)")
    // public void pointCutFrontend() {
    // }

    @Around("pointCut()")
    public Object aroundAdvice(final ProceedingJoinPoint pjp) {
        try {
            final Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            if (method.isAnnotationPresent(DBUSE.class)) {
                final String value = method.getDeclaredAnnotation(DBUSE.class).value();
                DbThreadLocalContextHolder.setDbUse(value);
                // log.info(method.getName() + "方法：切换到{}数据库", value);
            }
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            log.error(e.getMessage());
        } finally {
            DbThreadLocalContextHolder.poll();
        }
        return pjp.getTarget();
    }

    @Around("pointCutBackend()||common()")
    public Object aroundAdviceBackend(final ProceedingJoinPoint pjp) {
        try {
            if (StrUtil.isNotBlank(dbChangeConf.getBackend())) {
                DbThreadLocalContextHolder.setDbUse(dbChangeConf.getBackend());
            } else if (StrUtil.isNotBlank(dbChangeConf.getBackendInner())) {
                DbThreadLocalContextHolder.setDbUse(dbChangeConf.getBackendInner());
            }
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            log.error(e.getMessage());
        } finally {
            DbThreadLocalContextHolder.poll();
        }
        return pjp.getTarget();
    }

    // @Around("pointCutFrontend()")
    public Object aroundAdviceFrontend(final ProceedingJoinPoint pjp) {
        try {
            DbThreadLocalContextHolder.setDbUse("frontend");
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            log.error(e.getMessage());
        } finally {
            DbThreadLocalContextHolder.poll();
        }
        return pjp.getTarget();
    }
}
