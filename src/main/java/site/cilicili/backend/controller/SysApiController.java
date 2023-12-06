package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysApi;
import site.cilicili.backend.service.SysApiService;
import site.cilicili.common.util.R;

import java.io.*;

/**
 * (SysApi)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysApi")
@Tag(name = "(SysApi)表控制层")
public class SysApiController {
    /**
     * 服务对象
     */

    private final SysApiService sysApiService;

    /**
     * 全查询
     *
     * @param sysApi 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysApi 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysApi sysApi) {
        return this.sysApiService.queryAll(sysApi);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @GetMapping("{id}")
    public R queryById(final @PathVariable("id") Long id) {
        return this.sysApiService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysApi 实体
     * @return 新增结果
     */
    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysApi 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysApi sysApi) {
        return this.sysApiService.insert(sysApi);
    }

    /**
     * 编辑数据
     *
     * @param sysApi 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysApi 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysApi sysApi) {
        return this.sysApiService.update(sysApi);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Operation(summary = "删除数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @DeleteMapping
    public R deleteById(final Long id) {
        return this.sysApiService.deleteById(id);
    }

    @GetMapping("test")
    public R test(final Long id) throws IOException {
        String filePath = new ClassPathResource("application.properties").getPath();

        final FileReader fileReader = new FileReader(filePath);
        final char[] chars = new char[1024];
        fileReader.read(chars);
        fileReader.close();
        System.out.println(String.copyValueOf(chars));
        BufferedWriter sensitiveWordsWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath)));
        sensitiveWordsWriter.write("aaaa");
        sensitiveWordsWriter.flush();
        sensitiveWordsWriter.close();
        return R.ok();
    }

}



