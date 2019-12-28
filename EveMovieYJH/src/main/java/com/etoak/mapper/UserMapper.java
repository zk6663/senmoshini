package com.etoak.mapper;

import com.etoak.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 杨炯昊 on 2019/11/1.
 */
public interface UserMapper {

    User queryByNameAndPass(@Param("name")String name,@Param("password")String password);
}
