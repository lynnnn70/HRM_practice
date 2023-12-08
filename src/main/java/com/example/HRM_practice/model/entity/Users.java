package com.example.HRM_practice.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;


    //FetchType 加載關聯實體的策略 - EAGER:表示關聯實體在加載主實體時會被立即加載
    //CascadeType.ALL: 對主實體的所有操作(CRUD)都會關連到關聯的實體進行相同的操作
    //name定義中間表的名稱
    //joinColumns: 定義中間表與擁有ManyToMany註解的實體表的連接
    //inverseJoinColumns: 定義中間表與擁有ManyToMany註解的實體表的連接
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    //確保初始化時 roles屬性不為null，而被初始化為一個空的HashSet
    //ManyToMany描述兩個實體的多對多關係，關聯的屬性應該是一個集合，因此要將此屬性用集合Set包裝，才能正確反映多對多的本質
    private Set<Roles> roles = new HashSet<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{userId:").append(userId);
        sb.append(", userName:'").append(userName).append('\'');
        sb.append(", password:'").append(password).append('\'');
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Users user = (Users) object;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password);
    }

    public Users() {
    }

}
