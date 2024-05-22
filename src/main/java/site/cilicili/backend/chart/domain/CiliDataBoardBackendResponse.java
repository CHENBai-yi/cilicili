package site.cilicili.backend.chart.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CiliDataBoardBackendResponse {
    private Main2 main2;

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder
    public static class Main2 {
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
    public static class Main6 {
        @JsonProperty("uploadData")
        private Object uploadData;

        @Data
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Builder
        public static class Value {
            private String areaName;
            private Long count;
        }

    }

}
