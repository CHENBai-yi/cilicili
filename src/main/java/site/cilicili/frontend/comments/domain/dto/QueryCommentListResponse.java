package site.cilicili.frontend.comments.domain.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.comments.domain.dto
 * Date:2024/4/16 22:35
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class QueryCommentListResponse {
    private Object records;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    @Builder
    public QueryCommentListResponse(final Object records, final Integer page, final Integer pageSize, final Integer total) {
        this.records = records;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NoArgsConstructor
    public static class Records {
        private String id;
        @JsonProperty("parentId")
        private Integer parentId;
        private String uid;
        private String address;
        private String content;
        private Integer likes;
        @JsonProperty("contentImg")
        private String contentImg;
        @JsonProperty("createTime")
        private String createTime;
        private User user;
        private Replay reply;

        @Builder
        public Records(final String id, final Integer parentId, final String uid, final String address, final String content, final Integer likes, final String contentImg, final String createTime, final User user) {
            this.id = id;
            this.parentId = parentId;
            this.uid = uid;
            this.address = address;
            this.content = content;
            this.likes = likes;
            this.contentImg = contentImg;
            this.createTime = createTime;
            this.user = user;
        }

        @JsonGetter("reply")
        public Replay getReplyDetail() {
            return Optional.ofNullable(reply).filter(replay -> replay.total != 0).orElse(null);
        }

    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NoArgsConstructor
    public static class User {
        private String username;
        private String avatar;
        private Integer level;
        @JsonProperty("homeLink")
        private String homeLink;

        @Builder
        public User(final String username, final String avatar, final Integer level, final String homeLink) {
            this.username = username;
            this.avatar = avatar;
            this.level = level;
            this.homeLink = homeLink;
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NoArgsConstructor
    public static class Replay {
        private Integer total;
        private List<User> list;

        @Builder
        public Replay(final Integer total, final List<User> list) {
            this.total = total;
            this.list = list;
        }
    }
}
