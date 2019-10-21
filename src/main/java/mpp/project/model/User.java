package mpp.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name="User")
@Table(name="User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userid;

    @Column(name="user_name")
    private String userName;
    @JsonIgnore
    @Column(name="user_password")
    private String userPassword;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Column (name = "birth_day")
    private Date birthDay;

    @Column (name = "location")
    private String location;

    @Column(name="email")
    private String email;

    @Column(name="is_active")
    private int isActive;

    @CreationTimestamp
    @Column(name="create_date")
    private Date createdDate;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Follow",
            joinColumns = @JoinColumn(name = "FOLLOWED_ID"),
            inverseJoinColumns = @JoinColumn(name = "FOLLOWER_ID"))
    private List<User> followers;

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    private List<User> following;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts = new ArrayList<>();



    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "Post_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id")
    )
    private List<Post> postFavorite;

    private int followingCount;
    private int followerCount;

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public User() {
    }

    public User(String userName, String userpassword, String firstName, String lastName, String email, int isActive, Date createdDate, List<User> followers, List<User> following) {

        this.userName = userName;
        this.userPassword = userpassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
        this.createdDate = createdDate;
        this.followers = followers;
        this.following = following;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserpassword() {
        return userPassword;
    }

    public void setUserpassword(String userpassword) {
        this.userPassword = userpassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addFollower(User follower) {

        followers.add(follower);
        follower.following.add(this);

        this.setFollowerCount( followers.size());
        this.setFollowingCount(this.getFollowing().size());

    }

    public void deleteFollower(User unFollower) {

        unFollower.following.remove(this);
        followers.remove(unFollower);

        this.setFollowerCount( followers.size());
        this.setFollowingCount(this.getFollowing().size());

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserid() == user.getUserid() &&
                getIsActive() == user.getIsActive() &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getBirthDay(), user.getBirthDay()) &&
                Objects.equals(getLocation(), user.getLocation()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getCreatedDate(), user.getCreatedDate()) &&
                Objects.equals(getFollowers(), user.getFollowers()) &&
                Objects.equals(getFollowing(), user.getFollowing());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserid(), getUserName(), userPassword, getFirstName(), getLastName(), getBirthDay(), getLocation(), getEmail(), getIsActive(), getCreatedDate(), getFollowers(), getFollowing());
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPostFavorite() {
        return postFavorite;
    }

    public void setPostFavorite(List<Post> postFavorite) {
        this.postFavorite = postFavorite;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", createdDate=" + createdDate +
                ", followers=" + followers +
                ", following=" + following +
                ", posts=" + posts +
                ", postFavorite=" + postFavorite +
                '}';
    }
}