package com.serkansahin.mapper;

import com.serkansahin.dto.response.UserResponseDto;
import com.serkansahin.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
    UserResponseDto toUserResponseDto(User user);

    User toUser(UserResponseDto userResponseDto);
}
