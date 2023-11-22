package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.dao.RoleDAO;
import com.zigmunds.rieksts.springdemo.dao.UserDAO;
import com.zigmunds.rieksts.springdemo.entity.Role;
import com.zigmunds.rieksts.springdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

    Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    private UserDAO userDao;

    private RoleDAO roleDao;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDao, RoleDAO roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public Boolean userExistByUserName(String userName) {
        return userDao.userExistByUserName(userName);
    }

    @Override
    public void save(User user) {

        user.setEnabled(true);

        // give user default role of "employee"
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList(roleDao.findRoleByName(ROLE_EMPLOYEE)));
        }

        //check if password encoded already for update
        if (!BCRYPT_PATTERN.matcher(user.getPassword()).matches()) {
            //encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAll(String search) {
        return userDao.findAll(search);
    }

    @Override
    public User findById(int theId) {
        return userDao.findById(theId);
    }

    @Override
    public void deleteById(int theId) {
        userDao.deleteById(theId);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
