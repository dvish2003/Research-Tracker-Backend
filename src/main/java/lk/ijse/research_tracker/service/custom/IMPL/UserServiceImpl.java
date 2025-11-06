package lk.ijse.research_tracker.service.custom.IMPL;

import lk.ijse.research_tracker.Entity.User;
import lk.ijse.research_tracker.dto.UserDTO;
import lk.ijse.research_tracker.repo.UserRepository;
import lk.ijse.research_tracker.service.custom.UserService;
import lk.ijse.research_tracker.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Loading user by email: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found with email: " + email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthority(user)
        );
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        System.out.println("userDTO in service: " + userDTO);
        if (userRepository.existsByEmail(userDTO.getEmail().toLowerCase())) {
            return VarList.Not_Acceptable; // 406
        } else {
            System.out.println("userDTO before mapping: " + userDTO);
            User user = modelMapper.map(userDTO, User.class);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(userDTO.getPassword()));
            System.out.println("user entity to be saved: " + user);
            userRepository.save(user);
            return VarList.Created; // 201
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO searchUser(String keyword) {
        User user = userRepository.findByEmail(keyword);
        if (user == null) {
            user = userRepository.findByName(keyword);
        }
        if (user == null) return null;
        return modelMapper.map(user, UserDTO.class);
    }




    @Override
    public List<UserDTO> getUsers() {
        User user =  userRepository.findAll().stream().findFirst().orElse(null);
        if (user == null) return new ArrayList<>();
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
                
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return modelMapper.map(user, UserDTO.class);
    }
}
