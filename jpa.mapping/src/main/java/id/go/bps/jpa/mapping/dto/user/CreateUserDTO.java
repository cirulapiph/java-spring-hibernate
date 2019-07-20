package id.go.bps.jpa.mapping.dto.user;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO extends UserDTO{
	private String password;
	private List<RoleUserDTO> roleId;
}
