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

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
  /*  @Autowired
    private MemberRepository memberRepository;*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }


    @Override
    public List<UserDTO> getAllUser() {
        return List.of();
    }

    @Override
    public List<UserDTO> getUsers() {
        return List.of();
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        return 0;
    }

    @Override
    public int verifyUser(String email, String code) {
        return 0;
    }

    @Override
    public UserDTO searchUser(String username) {
        return null;
    }
};

