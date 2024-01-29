package crudmybatis.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Role {
    private Integer id;
    private ERole name;
    private Set<User> users = new HashSet<>();

    public String getRoleName() {
        return name.name();
    }
}