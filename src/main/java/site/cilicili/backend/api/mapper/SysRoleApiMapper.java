package site.cilicili.backend.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.api.domain.pojo.SysRoleApiEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 15:22:10
 */

/**
 * (SysRoleApi)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:22:10
 */
@Mapper
public interface SysRoleApiMapper extends BaseMapper<SysRoleApiEntity> {
    int insertOrUpdateBatch(@Param("entities") List<SysRoleApiEntity> entities);
}
