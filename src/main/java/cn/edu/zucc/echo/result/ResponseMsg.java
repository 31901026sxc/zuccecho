
package cn.edu.zucc.echo.result;
//实现响应的枚举类
public enum ResponseMsg {
	SUCCESS("200", "操作成功"),
	FAILED("999999","操作失败"),
    ParamError("000001", "参数错误！"),
    FileEmpty("000400","上传文件为空"),
    UserIsNotExist("000101","用户不存在"),
    PasswordError("000102","密码错误"),
    PasswordNotMarch("000103","两次输入密码不匹配"),
    TeacherNotFound("000104","老师不存在"),
    LimitPictureSize("000401","图片大小必须小于2M"),
    LimitPictureType("000402","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'"),
    ModelNotFound("000403","该问卷模板不存在" ),
    CourseNotFound("000405","该课程号不存在" ),
    StudentNotFound("000406","该学生不存在" ),
    QuestionireNotFound("000407","该问卷不存在" ),
    Endtimetooearly("000408", "结束时间早于当前时间"),
    Echoreportnotfound("000409","当前反馈信息不存在" ),
    Classrecordnotfound("000410","当前班级不存在" ),
    Questionirehascloesd("000411","当前问卷已关闭" ),
    Answernotfound("000412","无法找到当前答案记录" ),
    TimeError("000413","结束时间晚于开始时间或是现在" ),
    Questionalreadyinthismodel("000414","该问题已经存在于该模板中" ),
    QuestionSheetNotFound("000415","该模板不存在该记录" ),
    NoCourseFound("000416","找不到课程信息" );
    ;
   private ResponseMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;
    
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

    
}

