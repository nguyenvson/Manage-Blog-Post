package lab11.blogs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lab11.blogs.validator.UniqueTitleCheck;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @UniqueTitleCheck(message = "Title already exist !!!")
    @NotBlank(message = "can not blank !!!")
    @Column(unique = true)
    private String title;

    @NotBlank(message = "can not blank !!!")
    @Column(columnDefinition="TEXT")
    private String content;

    @CreationTimestamp
    private LocalDate dateCreate;

    @UpdateTimestamp
    private LocalDate dateUpdate;

    private String status;

    @ManyToMany
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnoreProperties(value = "posts")
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties(value = "posts")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post() {
    }

    public Post(String title, String content, LocalDate dateCreate, LocalDate dateUpdate, String status, List<Tag> tags, User user) {
        this.title = title;
        this.content = content;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.status = status;
        this.tags = tags;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                ", status=" + status +
                ", user=" + user.getUserName() +
                '}';
    }
}
