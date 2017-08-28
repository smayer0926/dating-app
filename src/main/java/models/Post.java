package models;

import java.sql.Timestamp;

public class Post {
    private int postId;
    private int userId;
    private String name;
    private String content;

    public Post(int userId, String name, String content){
        this.userId = userId;
        this.name = name;
        this.content = content;
    }

    public Post(int userId, String content){
        this.userId = userId;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (postId != post.postId) return false;
        if (userId != post.userId) return false;
        if (!name.equals(post.name)) return false;
        return content != null ? content.equals(post.content) : post.content == null;
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + userId;
        result = 31 * result + name.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
