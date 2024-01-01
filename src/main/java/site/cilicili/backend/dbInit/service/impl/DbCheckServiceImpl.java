package site.cilicili.backend.dbInit.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.dbInit.mapper.DbCheckMapper;
import site.cilicili.backend.dbInit.service.DbCheckService;
import site.cilicili.common.util.R;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.service.impl
 * Date:2023/12/5 11:21
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Service
@RequiredArgsConstructor
public class DbCheckServiceImpl implements DbCheckService {
    private final DbCheckMapper dbCheckMapper;

    @Override
    public R dbExists() {
        return null;
        // return Optional.ofNullable(CheckDB.getDbName("master"))
        //         .map(name -> R.yes(null).setData("need_init", dbCheckMapper.dbExists(String.valueOf(name)) ==
        // 0)).orElse(R.yes("数据库未准备好").setData("need_init", true));
    }
}
