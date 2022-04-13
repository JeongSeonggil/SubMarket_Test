package com.submarket.userservice.mapper;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.jpa.UserRepository;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.vo.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    // @Mapping(source = "nick", target = "nickname") 파라미터 객체 변수 이름, 리턴 객체 변수 이름
    //    @Mapping(target = "Age", ignore = true)
    UserEntity userDTOToEntity(UserDTO userDTO);

    UserDTO userEntityToDTO(UserEntity userEntity);

    ResponseUser userDTOToResponseUser(UserDTO userDTO);

}
