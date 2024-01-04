package site.cilicili.backend.dbInit.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.common.config.dynamicDb.dataSource.DbInitialization;
import site.cilicili.common.db.domain.dto.DatabaseConnectionDto;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;
import site.cilicili.common.util.DbUtils;
import site.cilicili.common.util.R;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("public")
@Tag(name = "数据库初始化表控制层")
public class DbCheckerController {
    private final DbInitialization dbInitialization;

    private final DbChangeConfig dbChangeConf;

    @PostMapping("check-db")
    public R checkDb() {
        return Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner()))
                .map(item -> R.yes("数据库初始化成功！").setData("need_init", false)).orElse(R.yes("数据库未准备好！").setData("need_init", true));
    }

    @PostMapping("init-db")
    public R initDb(@RequestBody final DatabaseConnectionDto databaseConnectionDto) {
        String mess = "数据库初始化失败！";
        try {
            final DatabaseConnection databaseConnections =
                    DbUtils.executeScript(databaseConnectionDto, dbChangeConf.getBackendInner());
            if (databaseConnections != null) {
                dbChangeConf.setBackend(databaseConnections.getScheme());
                if (dbInitialization.setConnections(databaseConnections)) {
                    return R.yes("数据库初始化成功！").setData("need_init", false);
                }
            }
        } catch (SQLException | IOException e) {
            mess = e.getMessage();
        }
        return R.no(mess).setData("need_init", true);
    }
}
