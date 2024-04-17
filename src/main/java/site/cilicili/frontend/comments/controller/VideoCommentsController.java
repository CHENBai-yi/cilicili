package site.cilicili.frontend.comments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;
import site.cilicili.frontend.comments.service.VideoCommentsService;

/**
 * (VideoComments) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-04-16 22:09:14
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
@Tag(name = "(VideoComments) 表控制层")
public class VideoCommentsController {
    /**
     * 服务对象
     */

    private final VideoCommentsService videoCommentsService;

    /**
     * 全查询
     *
     * @param videoComments 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "videoComments 筛选条件")
    })
    @GetMapping
    public R queryAll(final VideoCommentsEntity videoComments) {
        return this.videoCommentsService.queryAll(videoComments);
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
    public R queryById(final @PathVariable("id") Integer id) {
        return this.videoCommentsService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param videoComments 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "videoComments 实体")
    })
    @PostMapping
    public R add(final @RequestBody VideoCommentsEntity videoComments) {
        return this.videoCommentsService.insert(videoComments);
    }

    /**
     * 编辑数据
     *
     * @param videoComments 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "videoCommentsEntity 实体")
    })
    @PutMapping
    public R edit(final @RequestBody VideoCommentsEntity videoComments) {
        return this.videoCommentsService.update(videoComments);
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
    public R deleteById(final Integer id) {
        return this.videoCommentsService.deleteById(id);
    }

    @Operation(summary = "查询评论数据", parameters = {
            @Parameter(description = "QueryCommentListRequest 实体")
    })
    @PreAuthorize("isAnonymous()||isAuthenticated()")
    @PostMapping("list_comments")
    public R commentsList(final @RequestBody QueryCommentListRequest queryCommentListRequest) {
        return this.videoCommentsService.commentsList2(queryCommentListRequest);
    }
}



