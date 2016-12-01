package pavel.usanli.domain;

import javax.persistence.*;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
@Entity
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Ban() {
    }

    public Ban(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Ban setUser(final User user) {
        this.user = user;
        return this;
    }
}
