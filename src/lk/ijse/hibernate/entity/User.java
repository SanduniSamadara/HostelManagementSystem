package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Getter
public class User {
    @Id
    private String userId;
    private String name;
    private String telNo;
    private String email;
    private String userName;
    private String password;
}
