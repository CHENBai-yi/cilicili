package site.cilicili.common.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.cilicili.common.service.SseEmitterService;
import site.cilicili.common.util.R;
import site.cilicili.common.util.SseEmitterUtil;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.service.impl
 * Date:2024/5/2 13:42
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SseEmitterServiceImpl implements SseEmitterService {
    private final ApplicationEventPublisher publisher;
    private final SseEmitterUtil sseEmitterUtil;

    @Override
    public R flushCarouselList(HttpStatus httpStatus) {
        publisher.publishEvent(httpStatus);
        return R.yes("Success.");
    }

    @Override
    public SseEmitter subscribeCarousel() {
        return sseEmitterUtil.subscribe(IdUtil.nanoId());
    }
}
