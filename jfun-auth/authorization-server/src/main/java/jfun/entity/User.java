package jfun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@TableName(value = "sys_user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String salt;//加密密码的盐
    private String email;//邮箱
    private String avatar;//头像
    @TableField(value = "createTime")
    private Timestamp createTime;//创建时间
    private Integer state;//用户状态 --等待验证的用户 , 1:正常状态,2：用户被锁定.

    @TableField(exist = false)
    private List<Role> roles;// 一个用户具有多个角色

    public String getCredentialsSalt() {
        return this.getName() + this.salt;
    }


    public User simpleInfo() {
        User u = new User();
        u.setId(this.getId());
        u.setName(this.getName());
        u.setState(this.getState());
        u.setRoles(this.getRoles());
        return u;
    }
}
