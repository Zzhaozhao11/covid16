package com.tust.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User login(@Param("username") String username , @Param("password") String password);

    IPage selectAllUser(Page<User> page);

    User selectByUsername(String username);
}
