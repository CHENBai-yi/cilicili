package site.cilicili.frontend.chart.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.chart.domain
 * Date:2024/5/22 1:33
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder(toBuilder = true)
public class CiliDataBoardResponse {
    private List<Main1> main1;
    private List<Main4> main4;
    private Main3 main3;
    private Main5 main5;
    private Main6 main6;

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Main1 {
        private String name;
        private Long value;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Main4 {
        private String tradeNo;
        private String method;
        private String username;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder
    public static class Main3 {
        @JsonProperty("xData")
        private List<String> xData;
        @JsonProperty("yData")
        private Object yData;
        private Object title;

        @Data
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Builder
        public static class Title {

            private String name;
            private Long value;
        }

        @Data
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Builder
        public static class YData {
            @JsonProperty("uploadData")
            private List<Value> uploadData;
            @JsonProperty("updateData")
            private List<Value> updateData;
            @JsonProperty("viewData")
            private List<Value> viewData;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            public static class Value {
                private Long count;
            }
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder
    public static class Main5 {
        @JsonProperty("xData")
        private Collection<Object> xData;
        @JsonProperty("yData")
        private Collection<Object> yData;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder
    public static class Main6 {
        @JsonProperty("uploadData")
        private Object uploadData;

        @Data
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Builder
        public static class Value {
            @JsonProperty("areaName")
            private String areaName;
            private Long count;
        }

    }
}
