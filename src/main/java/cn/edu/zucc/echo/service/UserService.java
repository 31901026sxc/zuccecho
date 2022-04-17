package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicUserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String adduser(BasicUserDto userDto) throws EchoServiceException;//返回success||failed

    String deleteuser(BasicUserDto userDto) throws EchoServiceException;//同上

    String modifyuser(BasicUserDto userDto) throws EchoServiceException;

    BasicUserDto searchuser(Integer userid) throws EchoServiceException;//这是个样例，为以后可能的控制反转做铺垫
}
