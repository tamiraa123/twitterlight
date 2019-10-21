package mpp.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="Post")
@Table (name = "Post")
public class Post  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private int postID;

    @Column(name="tweet_type")
    private String tweetype ;

    @Column(name="post_text")
    private String postText;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name="user_name")
    private String userName;

    @CreationTimestamp
    @Column(name="post_date")
    private Date postDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "posts")
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @JoinTable(name = "Comment", joinColumns = {
            @JoinColumn(name = "post_owner_id", referencedColumnName = "post_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "comment_post_id", referencedColumnName = "post_id", nullable = false)})
    @ManyToMany
    private List<Post>Comments = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "postFavorite")
    private List<User> userFavorite = new ArrayList<>();

    public Post() {
    }

    public Post(String tweetype, String postText, Date postDate, List<User> users, List<Post> comments, List<User> userFavorite) {
        this.tweetype = tweetype;
        this.postText = postText;
        this.postDate = postDate;
        this.users = users;
        Comments = comments;
        this.userFavorite = userFavorite;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTweetype() {
        return tweetype;
    }

    public void setTweetype(String tweetype) {
        this.tweetype = tweetype;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getComments() {
        return Comments;
    }

    public void setComments(List<Post> comments) {
        Comments = comments;
    }

    public List<User> getUserFavorite() {
        return userFavorite;
    }

    public void setUserFavorite(List<User> userFavorite) {
        this.userFavorite = userFavorite;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", tweetype=" + tweetype +
                ", postText='" + postText + '\'' +
                ", postDate=" + postDate +
                ", users=" + users +
                ", Comments=" + Comments +
                ", userFavorite=" + userFavorite +
                '}';
    }
}
