package com.lwgk.network;

/**
 * Api接口
 * Created by luning on 2017/12/29.
 */
public interface ApiService {
    //登录
    String login              = "app/login/login";
    //微信登录
    String wxLogin            = "app/login/wx/android/login";
    //微信绑定
    String bindWx             = "app/login/wx/bind";
    //注册
    String regist             = "app/user/registerUser";
    //    检查手机是否注册
    String checkUserByPhone   = "app/user/checkUserByPhone";
    //    判断邀请码输入是否正确
    String checkInvitecode    = "app/user/checkInvitecode";
    //    找回密码
    String findPwd            = "app/user/findPwd";
    //    手机验证码获取
    String getCheckCode       = "app/user/getCheckCode";
    //    秘籍页
    String index              = "app/index/all";
    //    秘笈页搜索功能
    String search             = "app/index/search";
    //    获取所有的基础数据
    String basicdata          = "app/basicdata/all";
    //    查看单个微课
    String getMicroCourse     = "app/microCourse/getMicroCourse";
    //    微课列表
    String getMicroCourseMore = "app/microCourse/more";
    //    查看单个高分资料
    String getMaterials       = "app/materials/getMaterials";
    //    高分资料列表
    String getMaterialsMore   = "app/materials/more";
    //    查看单个公告
    String getNotice          = "app/notice/getNotice";
    //    公告列表
    String getNoticeMore      = "app/notice/more";
    //    查看单个咨询
    String getNews            = "app/news/getNews";
    //    相关咨询列表
    String getNewsMore        = "app/news/more";

    //收藏
    String favorite          = "app/favorite/favorite";
    //取消收藏
    String unfavorite        = "app/favorite/un";
    //查询评论
    String selectAllComments = "app/comments/selectAllComments";
    //    添加评论
    String addComments       = "app/comments/addComments";
    //点赞
    String like              = "app/like/like";

    //活动列表单张图片
    String onActive   = "app/activity/getActivity";
    //活动首页
    String active     = "app/activity/selectAllActivity";
    //听课列表
    String course     = "app/course/selectAllCourse";
    //听课列表相关课程
    String Likecourse = "app/course/getLikeCourse";

    //搜索界面热门搜索词汇
    String getAllHotKeyword = "app/hotKeyword/getAllHotKeyword";

    String getVersion     = "app/version/latestVer";
    //新手礼包活动
    String giftAct        = "app/gifts/index";
    //领取礼包
    String gotoGetgiftAct = "app/gifts/got";

    //个人中心上传个人头像
    String uploadProfile  = "app/user/upload/profile";
    // 个人中心请求资料
    String userMine       = "app/user/mine";
    // 更改账号
    String updatePhone    = "app/user/updatePhone";
    //判断验证码
    String checkCode      = "app/user/checkCode";
    //修改密码
    String updatePassword = "app/user/updatePassword";
    //新增收货地址
    String addAddress     = "app/address/add";
    //收货地址列表
    String listAddress    = "app/address/list";

    //删除收货地址
    String ldelAddress = "app/address/del";

    //设置为默认地址
    String defaultAddress = "app/address/defaultAddress";

    //意见反馈
    String addFeedback  = "app/feedback/addFeedback";
    //立即购买
    String buynow       = "app/pay/selectAllActivity";
    //添加订单
    String addOrder     = "app/order/add";
    //支付宝支付接口
    String aliPay       = "pay/payAliyun/payApp";
    //微信支付接口
    String wxPay        = "pay/payWx/appUnifiedorder";
    //微信支付接口
    String getOrder     = "app/order/getMyOrders";
    // 订单详情
    String getOrderInfo = "app/order/getOrderInfo";

    //取消订单
    String cancelOrder      = "app/order/cancelOrder";
    //我的课程列表接口
    String myCourses        = "app/course/myCourses";
    //我的课程收藏
    String myCoursesCollect = "app/course/myFavCourse";
    //我的课程免费
    String myCoursesFree    = "app/course/myFreeCourses";
    //我的课程付费/course/myPaymentCourses
    String myCoursesPay     = "app/course/myPaymentCourses";
    //我的评论列表接口
    String getMyComments    = "app/comments/getMyComments";
    //新增学习计划
    String addPlan          = "app/plan/add";
    // 学习提醒列表
    String getAllPlan       = "app/plan/getAllPlan";
    //删除学习提醒
    String delMyPlan        = "app/plan/delMyPlan";
    //打开或者关闭提醒学习
    String isOpen           = "app/plan/isOpen";
    // 学习提醒详情页
    String getPlan          = "app/plan/getPlan";
    // 听课详情页接口
    String getCourse        = "app/course/getCourse";

    // 我的学习记录
    String myPlanLogs = "app/plan/myPlanLogs";


    String completePlan = "app/plan/completePlan";
    //我的收藏的接口
    String myFavorites  = "app/favorite/myFavorites";
    //我的优惠券接口
    String myVouchers   = "app/voucher/myVouchers";

    // 注册邀请
    String myInvitation = "app/invite/myInvitation";

    // 我的消息接口
    String getMyMsg = "app/userMsg/getMyMsg";
    String delMsg   = "app/userMsg/delMsg";


    //添加到课程借口
    String addMyCourse = "app/addMyCourse/addMyCourse";
    // 立即学习
    String study       = "app/course/study";
    // 立即预约
    String reserve     = "app/userReserve/reserve";


    // 判断是否登录
    String isLogin              = "app/user/isLogin";
    //修改昵称
    String changeNickName       = "app/user/update";
    //拼团课程详情
    String collageDetails       = "app/gb/course/";
    //拼团信息
    String collageMessage       = "app/gb/team/";
    //拼团订单
    String collageOreder        = "app/gb/my/order";
    //从订单进拼团信息
    String orderCollagerMessage = "app/gb/team/order/";


    //申論测试地址
     String BASE_URL = "https://www.932edu.com/argumentation_test/";
    //申論正式地址
//    String BASE_URL = "http://api.932edu.com/argumentation/";

    //行测功能-测试环境
    String XCTestUrl           = "https://www.932edu.com/xc_test/";
    //行测功能-正式环境
//    String XCTestUrl           = "https://api.932edu.com/xc/";
    //题库页（前置页）
    String XCItemBank          = "rules/get";
    //题库页（保存）
    String XCSPItemBank        = "rules/set";
    //行测首页
    String XCHome              = "index/index";
    //行测首页banner
    String XCHomeBanner        = "banner/list";
    //行测轮播图点击事件
    String XCBannerGo          = "banner/go";
    //行测随机自测
    String XCRandom            = "exercise/random";
    //行测收藏
    String XCcollection        = "fav/fav";
    //行测取消收藏
    String XCunCollection      = "fav/unFav";
    //行测标记
    String XCmark              = "exercise/mark";
    //行测取消标记
    String XCunMark            = "exercise/unmark";
    //行测单题答案提交
    String XCsingle_item       = "exercise/single/save";
    //行测答案提交
    String XCexerciseSubmit    = "exercise/paper/submit";
    //行测专项练习
    String XCSpecialExercises  = "exercise/catory";
    //行测打榜活动首页
    String XCActivityHome      = "acts/index";
    //行测往期活动列表页
    String XCPastChallenge     = "acts/history/list";
    //行测往期活动详情页
    String XCchallengeDetails  = "acts/history/detail";
    //行测打榜挑战赛题
    String XCchallengeUrl      = "exercise/acts";
    //行测错题解析
    String XCWrongAnalysis     = "exercise/intro/error";
    //行测错题详情页
    String XCWrongDetails      = "exercise/errors/detail";
    //行测全部解析
    String XCWholeAnalysis     = "exercise/intro/all";
    //行测收藏详情页
    String XCCollectionDetails = "fav/detail";
    //行测单题解析
    String XCSignAnalysis      = "exercise/intro/single";
    //行测继续练习
    String XCContinuePractice  = "exercise/doit";
    //行测打榜的全部奖品页
    String XCAllPrizes         = "acts/goods/all";
    //行测错题汇总详情页删除错题
    String XCDelete            = "exercise/err/del";
    //行测模考首页
    String DryRunHome          = "mocks/entries";
    //行测模考报名
    String DryRunSignUp        = "mocks/enroll";
    //模考进入考场
    String DryRunIntoRoom      = "mocks/start";
    //模考往期列表
    String DryRunPast          = "mocks/history";
    //模考往期
    String DryRunPastUrl       = "mocks/history/detail";
    //刷题记录进模考
    String DryRunRecord        = "mocks/from/exercise";
    //模考答案提交
    String DryRunSubmit        = "mocks/submit";
    //模考放弃考试
    String DryRunGiveUp        = "mocks/giveUp";
    //模考开始答题
    String DryRunStart         = "mocks/begin";
    //模考获取系统时间
    String DryRunTime          = "mocks/time";//接口废弃


    //刷题记录
    String REFRESHPROBLEMRECODE  = "exercise/records/list";
    //刷题详情
    String REFRESHPROBLEMDETAILS = "exercise/records/detail";
    //错题汇总
    String ERORRRECODE           = "exercise/errors/list";
    //我的收藏
    String COLLECTION            = "fav/my";

    //商城类
    //能量钻石明细
    String ENERGYDRILLINGDETAILACCOUNT = "account/logs";
    //账户详情，也就是钻石数量
    String ACCOUNTACCOUNT              = "account/info";
    //商城列表
    String GOODSINDEX                  = "goods/index";
    //商品详情
    String GOODSDETAIL                 = "goods/detail";
    //奖品详情
    String ACTSGOODSDETAIL             = "acts/goods/detail";
    //商品兑换
    String GOODSBUY                    = "goods/buy";
    //奖品领取
    String ACTSGOODSGOT                = "acts/goods/got";
    //能量钻实物兑换,实物兑换下单
    String GOODSORDERADD               = "goods/order/add";
    //活动实物领奖
    String HTSGOODSORDER               = "acts/goods/order";

    //行测反馈接口
    String Feedback = "app/feedback/xc/addFeedback";


    //正常获取数据
    String RESULTCODE         = "200";
    //物流列表
    String LOGISTICSLIST      = "app/logistics/list";
    //物流详情
    String LOGISTICSDETAIL    = "app/logistics/detail";
    //订单列表
    String ORDERLIST          = "order/list";
    //消息列表
    String MSGLIST            = "msg/list";
    //我的任务列表
    String TASKSLIST          = "tasks/list";
    //订单详情
    String ORDERDETAIL        = "order/detail";
    //分享回调接口
    String TASKSDONE          = "tasks/done";
    //注册邀请
    String TASKSREGINV        = "tasks/regInv";
    //消息跳转
    String MSGGO              = "msg/go";
    //消息的个数
    String MSGCOUNT           = "msg/count";
    //收集崩溃日志接口
    String LOGSADD            = "logs/add";
    //根据rul跳转到本app的指定界面
    String MJGO               = "mj/go";
    //模考报告
    String MOCKSDETAIL        = "mocks/detail";
    //第一次选择职位
    String POSTIONAREA        = "postion/area";
    //第二次选择职位
    String POSTIONLIST        = "postion/list";
    //选择完提交职位
    String ENROLLWITHPOSITION = "mocks/enroll/with/position";

    //申论页面列表
    String SHENLUN_LIST = "index/statics";
    //申论单提列表
    String indexCatory  = "index/catory";

    //视频解析接口
    String VIDEO_ANASYC = "intro/video";
    //视频解析购买接口(申论行测)
    String VIDEO_BUY = "intro/buy/info";
    //行测视频解析列表
    String VIDEO_LIST_XC = "intro/list/xc";
    //申论视频解析列表
    String VIDEO_LIST_SL = "intro/list/sl";
    //申论视频解析列表购买支付按钮接口
    String ANASYC_VIDEO_PAY = "order/intro/add";
    //申论解析页面接口
    String SL_ANASYC_DATA = "question/intro";

    //行测必做真题接口
    String XC_RELATEST = "index/exam";

    //行测真题答题接口
    String XC_RELATEST_ANSWER = "index/get";

    //视频的清晰度
    String VEDIOINFO = "vedio/info?vid=";

    String MID     = "MID";
    String COLLECT = "COLLECTION";
    String ERROR   = "ERROR";
}



