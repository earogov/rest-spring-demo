package demo.webService.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @Getter @Setter
    private String username;

    @Column(name = "password", nullable = false)
    @Getter @Setter
    private String password;

    @Column(name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "authorities")
    @Getter @Setter
    private  String authorities;
}