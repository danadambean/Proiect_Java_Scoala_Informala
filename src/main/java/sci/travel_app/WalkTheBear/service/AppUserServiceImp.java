package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.misc.AppUserRole;
import sci.travel_app.WalkTheBear.repository.AppUserRepository;
import sci.travel_app.WalkTheBear.service.AppUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserServiceImp implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void save(AppUser user) {
        appUserRepository.save(new AppUser(user.getUserName(), passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getRole()));
    }
    @Override
    public AppUser findByEmail(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(s);
        if (appUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return appUser;
    }
    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(AppUserRole role : AppUserRole.values()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return grantedAuthorities;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(s);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(user.getEmail(), passwordEncoder.encode(user.getPassword()), getAuthorities());
    }
}