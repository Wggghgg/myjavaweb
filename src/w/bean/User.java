package w.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String  username;	//唯一的用户名
	private String  nickusername;	//昵称不唯一
    private String  password;
    private String  email;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email=email;
    }
}
