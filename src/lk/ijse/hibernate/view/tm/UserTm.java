package lk.ijse.hibernate.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTm {
    private String userId;
    private String name;
    private String telNo;
    private String email;
    private String userName;
    private String password;
}
