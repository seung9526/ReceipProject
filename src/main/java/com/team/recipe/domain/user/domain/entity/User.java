package com.team.recipe.domain.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Persister;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@DynamicInsert
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "users_id")
    private String userId;

    @ColumnDefault("'N'")
    private String deleteUserYn;

    @NotBlank(message = "password must not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    private String userName;

    @NotBlank(message = "E-mail address must not be empty")
    @Email(message = "User must have valid email address")
    @NaturalId
    @Size(max = 40)
    @Column(name = "user_email")
    private String userEmail;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<RecipePost> posts;

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @Builder
    public User(String userId, String password, String userName, String userEmail, String deleteUserYn){
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userEmail = userEmail;
        this.deleteUserYn = deleteUserYn;
    }

}
