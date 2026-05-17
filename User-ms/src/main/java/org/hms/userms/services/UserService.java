package org.hms.userms.services;


import org.hms.userms.dto.AdminRegisterDTO;
import org.hms.userms.dto.DoctorRegisterDTO;
import org.hms.userms.dto.RegisterDTO;
import org.hms.userms.dto.RegistrationCountsDTO;
import org.hms.userms.dto.UserDTO;
import org.hms.userms.exception.HmsException;


public  interface UserService {

    public void registerUser(RegisterDTO registerDTO) throws HmsException;

    public void registerAdmin(AdminRegisterDTO adminRegisterDTO) throws HmsException;

    public void registerDoctor(DoctorRegisterDTO doctorRegisterDTO) throws HmsException;

    public UserDTO loginUser(UserDTO  userDTO) throws HmsException;
    public UserDTO getUserById(Long id) throws HmsException;
    public void updateUser(UserDTO  userDTO);
    public UserDTO getUser(String email) throws HmsException;
    public Long getProfile(Long id) throws HmsException;
    RegistrationCountsDTO getMonthlyRegistrationCounts();

}
