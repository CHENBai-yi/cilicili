package site.cilicili.common.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.cilicili.common.util.R;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.service
 * Date:2024/5/2 13:42
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public interface SseEmitterService {
    R flushCarouselList(HttpStatus httpStatus);

    SseEmitter subscribeCarousel();
}
