package com.wf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {


}
