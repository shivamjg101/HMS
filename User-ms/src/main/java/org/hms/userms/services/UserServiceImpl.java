package org.hms.userms.services;

import org.hms.userms.clients.ProfileClient;
import org.hms.userms.dto.MonthlyRoleCountDTO;
import org.hms.userms.dto.RegistrationCountsDTO;
import org.hms.userms.dto.Roles;
import org.hms.userms.dto.UserDTO;
import org.hms.userms.entity.Users;
import org.hms.userms.exception.HmsException;
import org.hms.userms.mappers.UserMapper;
import org.hms.userms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private final  ProfileClient profileClient;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ProfileClient profileClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileClient = profileClient;
    }


    @Override
    public void registerUser(UserDTO userDTO) throws HmsException {
        Optional<Users> opt = userRepository.findByEmail(userDTO.getEmail());
        if(opt.isPresent()){
            throw new HmsException("User_Already_Exists");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Long profileId = null;
        if (userDTO.getRole().equals(Roles.DOCTOR)) {
            profileId = profileClient.addDoctor(userDTO);
        } else if (userDTO.getRole().equals(Roles.PATIENT)) {
            profileId = profileClient.addPatient(userDTO);
        }
        userDTO.setProfileId(profileId);
        Users user = UserMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HmsException {
        Users users = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(()-> new HmsException("User_Not_Found"));

        if(!passwordEncoder.matches(userDTO.getPassword(),users.getPassword())){
            throw new HmsException("Wrong_Password");
        }
        users.setPassword(null);
        return UserMapper.toDTO(users);
    }

    @Override
    public UserDTO getUserById(Long id) throws HmsException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new HmsException("User_Not_Found"));

        return UserMapper.toDTO(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public UserDTO getUser(String email) throws HmsException {
        Users user = userRepository.findByEmail(email).orElseThrow(()->new HmsException("User_Not_Found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public Long getProfile(Long id) throws HmsException {
        Users user = userRepository.findById(id).orElseThrow(() -> new HmsException("USER_NOT_FOUND"));
        if (user.getRole().equals(Roles.DOCTOR)) {
            return profileClient.getDoctor(user.getProfileId());
        } else if (user.getRole().equals(Roles.PATIENT)) {
            return profileClient.getPatient(user.getProfileId());
        }
        throw new HmsException("INVALID_USER_ROLE");
    }

    @Override
    public RegistrationCountsDTO getMonthlyRegistrationCounts() {
        List<MonthlyRoleCountDTO> doctorCounts = userRepository.countRegistrationsByRoleGroupedByMonth(Roles.DOCTOR);
        List<MonthlyRoleCountDTO> patientCounts = userRepository.countRegistrationsByRoleGroupedByMonth(Roles.PATIENT);
        return new RegistrationCountsDTO(doctorCounts, patientCounts);
    }
}
