package com.mkalaimalai.mapper;

import com.mkalaimalai.domain.Address;
import com.mkalaimalai.domain.User;
import com.mkalaimalai.vo.AddressVO;
import com.mkalaimalai.vo.UserVO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kalaimam on 7/14/17.
 */
@Component
public class UserMapper {


    private final MapperFactory userMapperFactory;
    private final MapperFacade userMapper;
    private final ConfigurableMapper configurableMapper = new ConfigurableMapper();

    public UserMapper() {
        userMapperFactory = new DefaultMapperFactory.Builder().build();
        userMapperFactory.classMap(User.class, UserVO.class)
                .mapNulls(true).mapNullsInReverse(true)
                .byDefault()
                .register();
        userMapperFactory.classMap(Address.class, AddressVO.class)
                .mapNulls(false).mapNullsInReverse(false)
                .byDefault()
                .register();

        userMapper = userMapperFactory.getMapperFacade();

    }

    public UserVO createUserVO(User user) {
        UserVO userVO = userMapper.map(user, UserVO.class);
        if(user.getAddresses() !=null)
        userVO.setAddresses(createAddressVOList(user.getAddresses()));
        return userVO;
    }

    public User createUser(UserVO userVO) {
        User user = userMapper.map(userVO, User.class);
        if(userVO.getAddresses() !=null)
        user.addAddresses(createAddressList(userVO.getAddresses()));
        return user;
    }



    public List<AddressVO> createAddressVOList(List<Address> addresses) {
        return userMapper.mapAsList(addresses, AddressVO.class);

    }

    public List<Address> createAddressList(List<AddressVO> addresses) {
        return userMapper.mapAsList(addresses, Address.class);

    }

}
