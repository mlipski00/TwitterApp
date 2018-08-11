package pl.springproject.twitter_app.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotEmpty
    @Column(name = "name")
    private String username;

    @NotEmpty
    @Size(min = 6, max = 20)
    @Column(name = "password")
    private String password;

    private boolean active;

    @NotEmpty
    @Email
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_tweets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private Set<Tweet> tweets;

    public User(User user) {
        this.active = user.isActive();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.id = user.getId();
        this.password = user.getPassword();
    }

    public User(@NotEmpty String username, @NotEmpty @Size(min = 6, max = 20) String password, boolean active, @NotEmpty @Email String email) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.email = email;
    }
}
