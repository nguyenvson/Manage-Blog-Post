package lab11.blogs.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    private String name;

    private int frequency;

    @ManyToMany(mappedBy = "tags" ,fetch = FetchType.EAGER)
    private List<Post> posts;

    public Tag() {
    }

    public Tag(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public Tag(String name, int frequency, List<Post> posts) {
        this.name = name;
        this.frequency = frequency;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
