package com.bahd.mapper;

import com.bahd.dto.AccountDTO;
import com.bahd.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;
    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO convertToDTO(Account entity){
        //this method will accept account and convert it to DTO
        return modelMapper.map(entity, AccountDTO.class);
    }

    public Account convertToEntity(AccountDTO dto){
        //this takes Account DTO & converts to entity
        return modelMapper.map(dto, Account.class);
    }




}
