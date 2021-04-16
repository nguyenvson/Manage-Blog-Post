package lab11.blogs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JsonIgnoreProperties(value = "posts")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    @JsonIgnoreProperties(value = {"comments", "tags"})
    private Post post;

    private String status;

    public Comment() {
    }

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
        this.status = "approve";
    }

    public Comment(String content, User author, Post post, String status) {
        this.content = content;
        this.author = author;
        this.post = post;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", post=" + post +
                '}';
    }
}
