package site.cilicili.backend.dbInit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.mapper
 * Date:2023/12/5 11:15
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Mapper
public interface DbCheckMapper {
    Integer dbExists(@Param("dbName") String dbName);

    Integer dBExists2(String dbName);
}
