package WebInfo_248.ForumWeb.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private int id;
    private String content;
    private LocalDateTime postedDate;
    private int userId;
    private int discussionId;

}
