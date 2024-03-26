package site.cilicili.frontend.course.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/12 15:14
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
public class GetChildrenBarResponse {
    private Integer id;
    private String name;
    private String url;
    private List<Catalog> catalog;

    @Data
    public static class Catalog {
        private Integer id;
        private Integer catalogId;
        private String title;
        private String desc;
        private List<Bar> bar;
        private Boolean selectable = false;
    }

    @Data
    public static class Bar {
        private Integer barId;
        private Integer pId;
        private Integer catalog;
        private String id;
        private String title;
        private String desc;
        private String url;
        private String length;
        private Long size;
    }
}
