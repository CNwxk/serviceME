package com.team6.serviceme.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Set User Attributes
 *
 */
@Data   //Auto add Getter and Setter
@Entity
@ApiModel(description="User Entity")
@NoArgsConstructor
public class User implements UserDetails {
    @ApiModelProperty(value = "User Id", position = 1)
    @Id @GeneratedValue
    private Long id;

    @ApiModelProperty(value = "User Name", position = 2)
    @NonNull @Size(min = 2, max = 15)
    private String userName;

    @ApiModelProperty(value = "User Password", position = 3)
    @NonNull
    private String passWord;

    @ApiModelProperty(value = "User Phone", position = 4)
    @NonNull @Size(min = 2, max = 20)
    private String phone;

    @ApiModelProperty(value = "User Email", position = 5)
    @NonNull @Email
    private String email;

    @ApiModelProperty(value = "createTime", position = 6)
    private Date creatTime;

    @ApiModelProperty(value = "updateTime", position = 7)
    private Date updateTime;

    @ApiModelProperty(value = "role", position = 8)
    @NonNull
    private String role;

    @ApiModelProperty(value = "question", position = 9)
    @NonNull
    private String question;

    @ApiModelProperty(value = "answer", position = 10)
    @NonNull
    private String answer;

    @ApiModelProperty(value = "User Address", position = 11)
    @NonNull
    private String address;

    @ApiModelProperty(value = "User Type", position = 12)
    private String type;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

}

