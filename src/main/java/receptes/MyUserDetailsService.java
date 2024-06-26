//Sis serviss atrod lietotaju datubaze pec username. To izmanto kombinacija ar SecurityConfiguration.java

package receptes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import receptes.model.UserModel;
import receptes.type.UserType;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserModel userModel;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserType userType = userModel.findByUsername(username);
		if(userType != null) {
			System.out.println("loadUserByUsername" + userType.toString());
			return User.builder()
				.username(userType.getLietotajvards())
				.password(userType.getParole())
				.roles(userType.getLoma())
				.build();
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
}
