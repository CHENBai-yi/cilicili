package site.cilicili.common.config.dynamicDb.annotation;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    @Pointcut(
            "execution( * site.cilicili.backend..service..*(..))||execution( * site.cilicili.authentication.user.service..*(..))")
    public void pointCutBackend() {
    }

    @Pointcut(
            "execution( * site.cilicili.common.db.service..*(..)) || execution( * site.cilicili.common.config.dynamicDb.dataSource..*(..))")
    public void common() {
    }

    @Pointcut("within(site.cilicili.frontend..*)")
    public void pointCutFrontend() {
    }

    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        try {
            final Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            if (method.isAnnotationPresent(DBUSE.class)) {
                final String value = method.getDeclaredAnnotation(DBUSE.class).value();
                DbThreadLocalContextHolder.setDbUse(value);
            } else {
                if (StrUtil.isNotBlank(dbChangeConf.getBackend())) {
                    DbThreadLocalContextHolder.setDbUse(dbChangeConf.getBackend());
                } else if (StrUtil.isNotBlank(dbChangeConf.getBackendInner())) {
                    DbThreadLocalContextHolder.setDbUse(dbChangeConf.getBackendInner());
                }
            }
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            log.error(e.getMessage());
        } finally {
            DbThreadLocalContextHolder.poll();
        }
        return pjp.getTarget();
    }

    @Before("pointCutBackend()||common()")
    public void aroundAdviceBackend() {
        if (StrUtil.isNotBlank(dbChangeConf.getBackend())) {
            DbThreadLocalContextHolder.setDbUse(dbChangeConf.getBackend());
        } else if (StrUtil.isNotBlank(dbChangeConf.getBackendInner())) {
            DbThreadLocalContextHolder.setDbUse(dbChangeConf.getBackendInner());
        }
    }

    @After("pointCutBackend()||common()")
    public void aroundAdviceBackendAfter() {
        DbThreadLocalContextHolder.poll();
    }

    // @Around("pointCutBackend()||common()")
    // public Object aroundAdviceFrontend(final ProceedingJoinPoint pjp) {
    //     try {
    //         return pjp.proceed(pjp.getArgs());
    //     } catch (Throwable e) {
    //         log.error(e.getMessage());
    //     } finally {
    //         DbThreadLocalContextHolder.poll();
    //     }
    //     return pjp.getTarget();
    // }

    /**
     * 前端数据库
     */
    @Before("pointCutFrontend()")
    public void beforeFrontend() {
        if (StrUtil.isNotBlank(dbChangeConf.getFrontend())) {
            DbThreadLocalContextHolder.setDbUse(dbChangeConf.getFrontend());
        } else if (StrUtil.isNotBlank(dbChangeConf.getFrontendInner())) {
            DbThreadLocalContextHolder.setDbUse(dbChangeConf.getFrontendInner());
        }
    }

    @After("pointCutFrontend()")
    public void afterFrontend() {
        DbThreadLocalContextHolder.poll();
    }
}
