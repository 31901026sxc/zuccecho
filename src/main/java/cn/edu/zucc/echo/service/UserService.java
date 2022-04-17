package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicUserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Integer addUser(BasicUserDto userDto) throws EchoServiceException;//返回success||failed

    String deleteUser(BasicUserDto userDto) throws EchoServiceException;//同上

    String modifyUser(BasicUserDto userDto) throws EchoServiceException;

    BasicUserDto searchUser(String userCode) throws EchoServiceException;//这是个样例，为以后可能的控制反转做铺垫
}
