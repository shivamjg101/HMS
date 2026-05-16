package org.hms.userms.mappers;

import org.hms.userms.dto.UserDTO;
import org.hms.userms.entity.Users;

public class UserMapper {
    public static UserDTO toDTO(Users user) {
        if (user == null) return null;

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getProfileId(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static Users toEntity(UserDTO dto) {
        if (dto == null) return null;

        return new Users(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRole(),
                dto.getProfileId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
    }
}
