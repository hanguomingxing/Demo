package com.me.daydaystudy.interfaces;

public interface ConstantUtils {

    int NO_TIME = 0;
    int NORMAL_TIME = 3 * 24 * 60 * 60 * 1000;
    int MONTH_TIME = 30 * 24 * 60 * 60 * 1000;
    int MAX_TIME = 365 * 24 * 60 * 60 * 1000;

    //基本URL
    String BASE_URL = "http://www.meirixue.com/";
    //课程列表
    String curriculumsTable = "api.php?c=list&a=index";
    //圈子里的话题
    String CircleTopicUrl = "api.php?c=circle&a=getCircleNamesIndexV2";
    //圈子里的热门内容
    String CircleHotContentUrl = "api.php?c=circle&a=getCirclePostListByTid";
    //圈子里的热门标题
    String CircleHotTitleUrl = "api.php?c=circle&a=getRecommendTag";
    // 登录接口
    String signIn = "api.php?c=login&a=index";
    // 注册接口获得短信的验证码
    String signInCode = "api.php?c=register&a=doregister";
    // 短信重发接口
    String resend = "api.php?c=register&a=resendmsg";
    // 注册激活接口
    String register = "api.php?c=register&a=verify";
    // 找回密码接口
    String forgetPassWord = "api.php?c=forget&a=doforget";
    // 手机修改密码
    String modifypassWord = "api.php?c=forget&a=modify";
    // 意见反馈
    String feedback = "api.php?c=feedback&a=index";

    // 消息为已读
    String messageRead = "api.php?c=notice&a=setnotice";

    // 获得消息的个数
    String myMessageCount = "api.php?c=notice&a=noticecount";
    // 消息接 口
    String myMessage = "api.php?c=notice&a=index";
    // 修改用户昵称
    String nickName = "api.php?c=passport&a=savenickname";
    // 修改用户性别
    String userSex = "api.php?c=passport&a=savesex";

    // 分类页面的数据
    String sort = "api.php?c=category&a=getall";
    // 列表页接口
    String courseList = "api.php?c=list&a=index";
    // 三级分类的数据
    String threeCourse = "api.php?c=category&a=getTree";

    // 三方登录的接口
    String threeCheckLog = "api.php?c=login&a=checkuser";
    String threeAddLog = "api.php?c=login&a=otheradd";

    // 分享相关
    String SHARE_ADDRESS = "api.php?c=course&a=getwapinfo&courseid=";

    long CLICKAPART = 3000;
    int SUCCESS = 1000;
    //首页，轮播...
    String USER_MAIN="api.php?a=indexv9&c=index";
    // 课程详情页评论
    String USER_COMMENT = "api.php?c=course&a=getCourseComment";
    // 我的课程，包括正在学和已学完
    String USER_COURSE = "api.php?c=user&a=usercourse";
    // 我的收藏
    String USER_COLLECT = "api.php?c=user&a=usercollect";
    // 删除我的课程
    String COURSE_DELETE = "api.php?c=user&a=delete";
    // 删除我的收藏
    String COLLECT_DELETE = "api.php?c=user&a=deletecollect";
    // 修改用户头像
    String CHANGE_PHOTO = "api.php?c=passport&a=savelog";
    // 上传用户头像
    String UPLOAD_PHOTO = "uploadvideo/upload";
    // 课程详情页
    String COURSE_DETAIL = "api.php?c=course&a=getCourseInfo";
    // 课程详情页 底部课程目录
    String COURSE_CATALOG = "api.php?c=course&a=getCourseStep";
    // 添加收藏
    String COURSE_COLLECT = "api.php?c=user&a=addcollect";
    // 参与课程
    String COURSE_JOIN = "api.php?c=pay&a=createorder";

    // 课程详情
    String COURSE_DETAILS = "api.php?c=course&a=getCourseDesc";
    // 添加评论
    String COMMENT_ADD = "api.php?c=course&a=addcomment";
    // 支付后获取订单信息
    String ORDER_INFO = "api.php?c=pay&a=getorder";
    // 支付后通知服务器更新订单状态 （现在没用到）
    String ORDER_UPDATE = "api.php?c=pay&a=updateorder";
    // 搜索课程
    String COURSE_SEARCH = "api.php?c=search";
    // 热门搜索
    String COURSE_SEARCH_HOT = "api.php?c=search&a=searchhot";
    // 播放视频获取url 和 token
    String COURSE_PLAY = "api.php?c=course&a=getplayurl";
    // 播放视频通知服务器
    String PLAY_VIDEO = "api.php?c=course&a=studystatus";
    // 给服务器发送log
    String SEND_LOG = "http://uplog.meirixue.com/";
    // 检查更新
    String UPDATE_VERSION = "api.php?c=upgrade&a=checkupdate";

    int OPEN_GALLERY_CODE = 110;
    int OPEN_CAMERA_CODE = 111;
    int CROP_PHOTO_CODE = 112;
    int PAY_SUCCESS = 113;
    int PAY = 114;
    int PAY_FAIL = 115;
    int PUB_COMMENT_SUCCESS = 116;
    int PUB_COMMENT = 117;
    int PUB_COMMENT_FAIL = 118;
    int LOGIN_SUCCESS = 119;
    int LOGIN = 120;
    int LOGIN_FAIL = 121;
    int PLAY = 122;
    int PLAY_PAY = 123;
    int JOIN = 123;

    // 加载页面加载时间
    int LOADING_TIME = 60000;
    // 微信
    // appid
    // 请同时修改 androidmanifest.xml里面，.PayActivityd里的属性<data
    // android:scheme="wx481ff0d6a7868ded"/>为新设置的appid
    String APP_ID = "wx481ff0d6a7868ded";
    // 商户号
    String MCH_ID = "1262800401";
    // API密钥，在商户平台设置
    String API_KEY = "47fca64e05e55e6e81db4b185491e23f";
    // 支付宝
    // 商户PID
    String PARTNER = "2088911155821515";
    // 商户收款账号
    String SELLER = "youxuezhifubao@sina.com";
    // 商户私钥，pkcs8格式
    String RSA_PRIVATE = "MIICXgIBAAKBgQDkkPR8u3CwUb1BUGd9UF8NvvdvmQMK8HAiRKb+aw6I4dIpikBnsoe5DCmavn5PYYSMs8cHiTBYbHx0Py0AT4lDfh1bfVfkLV5bq1pH/9UEQqE9PhtCP1llVtRkxPZijgjoLiW1bjaGC1dUppzyvC6CbIrrpSIXXVqnQjhhsW6t+wIDAQABAoGBAK9Nwka8mLSTLL2gho9lilIHqs3MJLpKKScPUqmFwNMZqFA1+inOkyxJeMt32XbWy4wDAt7zyMG5C0c5gTFgz1wilI1hOuS60dZw77zTuFAoyqVMsg48ByQRmfslp8zV7JHcBpqH0bUkZBCs3bqSSOBUsByMwplt1LxtEf3s7caJAkEA9JSsxqOGma0F9SLDMUUNVKW/0gN/oKIUJje2g2yjRI7NqV9sOdVHadUHdn1JbjvYxMJ9HPPjzr1YqOYv3/xoJwJBAO883kC/xinNFdTTLsIZW/bTKG75QOP2usy/g1bOhpkhKRD7kO/9YEZZI7X8eLZH7Tyaez/1+9uW341N9luR/A0CQQDip/1NwDF2rO++cs6tFC78aPZQ68kU1d8SY8MSHFVZrXVOJw4msk4R98eIuSaZr4B9JzfG4wYUi0hkjZ2EV2c1AkEAkggRkmx9RZ7OKK8P56MbI7sY/0Ree8hrlfeA8Ef4mRhIvPkSK6v6THa0+a028e1NvqR9fzljl1Ks+tAQBS1DMQJAD87ZYADaPA64TjIt341ATFXWGxRCESGCK3pfG31xcI1gMsTOAnT3zwTBwDXWIqyzzGslDo6JKY3eBSBI0qvFeg==";
    // 支付宝公钥
    String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";


}
