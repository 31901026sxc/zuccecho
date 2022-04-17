package cn.edu.zucc.echo.service.impl;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.entity.TpModelEntity;
import cn.edu.zucc.echo.entity.TpQuestionEntity;
import cn.edu.zucc.echo.entity.TpQuestionOptionEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicUserDto;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.form.TpQuestionDto;
import cn.edu.zucc.echo.form.TpQuestionOptionDto;
import cn.edu.zucc.echo.repository.BasicUserEntityRepository;
import cn.edu.zucc.echo.repository.TpModelEntityRepository;
import cn.edu.zucc.echo.service.QuestionService;
import cn.edu.zucc.echo.service.UserService;
import cn.edu.zucc.echo.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BasicUserEntityRepository userEntityRepository;

    @Override
    public Integer addUser(BasicUserDto userdto) throws EchoServiceException {
        //检查
//        if (userdto.getId() != 0) {
//            throw new EchoServiceException("新建用户不能上传id");
//        }


        //保存到数据库
        BasicUserEntity UserEntity = new BasicUserEntity();
        BeanUtils.copyProperties(userdto, UserEntity);
//        UserEntity.setStatus(Constants.Model_STATUS_NORMAL);
//处理题目
//        for (TpQuestionOptionDto questionOptionDto : questiondto.getOptions()) {
//            TpQuestionOptionEntity questionOptionEntity = new TpQuestionOptionEntity();
//            BeanUtils.copyProperties(questionOptionDto, questionOptionEntity);
//            questionEntity.getTpQuestionOptions().add(questionOptionEntity);
//        }
//
//        this.QuestionEntityRepository.save(questionEntity);
//        return questionEntity.getId();

        //处理数据
        userEntityRepository.save(UserEntity);


        return UserEntity.getsid();
    }

    @Override
    public String deleteUser(BasicUserDto userdto) throws EchoServiceException {
        BasicUserEntity UserEntity = new BasicUserEntity();
        UserEntity = userEntityRepository.findByUserCode(userdto.getUserCode());
        userEntityRepository.delete(UserEntity);
        return "success";
    }

    public String modifyUser(BasicUserDto userDto) throws EchoServiceException{
        BasicUserEntity UserEntity = new BasicUserEntity();
        UserEntity = userEntityRepository.findByUserCode(userDto.getUserCode());
        userEntityRepository.save(UserEntity);
        return "success";
    };

    public BasicUserDto searchUser(String userCode) throws EchoServiceException{
        BasicUserEntity UserEntity = new BasicUserEntity();
        UserEntity = userEntityRepository.findByUserCode(userCode);
        BasicUserDto userDto = null;
        BeanUtils.copyProperties(UserEntity,userDto);
        return userDto;
    };//这是个样例，为以后可能的控制反转做铺垫
}