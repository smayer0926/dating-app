//package models;
//
//import java.sql.Timestamp;
//
//public class Post {
//    private int id;
//    private int userId;
//    private String name;
//    private String postContent;
//
//    public Post(int userId, String name, String postContent){
//        this.userId = userId;
//        this.name = name;
//        this.postContent = postContent;
//    }
//
//    public Post(int userId, String postContent){
//        this.userId = userId;
//        this.postContent = postContent;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Post post = (Post) o;
//
//        if (id != post.id) return false;
//        if (userId != post.userId) return false;
//        if (!name.equals(post.name)) return false;
//        return postContent.equals(post.postContent);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + userId;
//        result = 31 * result + name.hashCode();
//        result = 31 * result + postContent.hashCode();
//        return result;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPostContent() {
//        return postContent;
//    }
//
//    public void setContent(String postContent) {
//        this.postContent = postContent;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//}
