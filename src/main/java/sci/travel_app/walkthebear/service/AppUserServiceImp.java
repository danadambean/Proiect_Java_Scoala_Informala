package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.data_utils.AppUserDetails;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.repository.AppUserRepository;

import java.util.Date;
import java.util.List;

@Service
public class AppUserServiceImp implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Saves a new user.
     *
     * @param user- user object
     * @return saved user
     */
    @Override
    public AppUser save(AppUser user) {
        user.setCreated(new Date());
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        return appUserRepository.save(user);
    }

    /**
     * Save a new password for a specific user
     *
     * @param user - user object
     * @return saved user with the new password
     */
    public AppUser saveNewPassoword(AppUser user) {
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        return appUserRepository.save(user);
    }

    /**
     * Updates the user information
     *
     * @param user - specific user
     * @param id   - user ID
     */
    @Override
    public void update(AppUser user, long id) {
        AppUser updatedUser = new AppUser();
        updatedUser.setId(id);
        updatedUser.setUserName(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setCreated(appUserRepository.findById(id).getCreated());
        updatedUser.setRole(appUserRepository.findById(id).getRole());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));

        appUserRepository.save(updatedUser);
    }

    /**
     * Finds the user by email
     *
     * @param s - email address
     * @return - user specific to an email address
     * @throws UsernameNotFoundException
     */
    @Override
    public AppUser findByEmail(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(s);
        if (appUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return appUser;
    }

    /**
     * FInds the user by ID
     *
     * @param id - user ID
     * @return - the found user
     */
    @Override
    public AppUser findById(long id) {
        return appUserRepository.findById(id);
    }

    /**
     * FInd all users added
     *
     * @return all the users in the database
     */
    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    /**
     * Finds users by keyword
     *
     * @param keyword- keyword used to search for users
     * @return - all the users that have the keyword in their name
     */
    @Override
    public List<AppUser> findUsersByKeyword(String keyword) {
        return appUserRepository.findUsersByKeyword(keyword);
    }

    /**
     * Deletes user
     * @param user- user that is deleted
     */
    @Override
    public void deleteUser(AppUser user) {
        appUserRepository.delete(user);
    }

    /**
     * Finds user by user name.
     * @param username- username used to find the user
     * @return - the user specific for a user name
     */

    public AppUser findByUserName(String username) {
        return appUserRepository.findByUserName(username);
    }

    /**
     * Creates a new user if the user name user is not already in use
     * @param username- username used to find the user
     * @return new user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AppUserDetails(user);

    }


}