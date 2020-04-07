package com.xinyu.test_boot.global;


public interface GlobalConst {

    public static final class Code {
        //微服务的code
        public final static Integer SUCCESS = 200; //请求成功
        public final static Integer FAIL = 400; //请求失败
        public final static Integer REMOTE_FAIL = 600; //乐高鉴权失败
        public final static Integer AUTH_FAIL = 700; //菜单鉴权失败
    }

    public enum ErrorCode {
        SERVICE_SUCCESS("200", "成功"),
        SYSTEM_ERROR("500", "系统异常"),
        DB_ERROR("501", "数据库错误"),
        
        REQUST_LIMIT("1001","请求次数受限"),
        
        PARAM_ERROR("9000", "参数错误"),
        HTTP_METHOD_NOT_SURPPORT("9001", "http方法不支持"),
        UNKNOWN_ERROR("9002", "未知错误"),
        ;

        private String code;
        private String msg;

        private ErrorCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getMsg() {
            return this.msg;
        }

        public String getCode() {
            return this.code;
        }

    }

    public static final class UserStatus {
        public final static int OPEN = 1; //激活
        public final static int CLOSE = 2; //禁用
    }


    public enum PaymentStatus {
        INITIAL,
        WAIT2PAY,
        PRE_AUTH_COMFIRMED,
        WAIT2_PRE_AUTH_COMFIRM,
        SUCCESS,
        RECEIPT_CONFIRMING,
        REFUNDING,
        REFUND_SUCCESS,
        ROLLBACKING,
        ROLLBACK_SUCCESS,
        ERR_TIMEOUT,
        ERR_INSUFFCIENT_BALANCE,
        ERR_PRE_AUTH_CONFIRM_FAIL,
        ERR_PRE_AUTH_REVOKE_FAIL,
        ERR_REFUND_FAIL,
        CANCLE_SUCCESS,
        ERR_CANCLE_FAIL;
    }

    public static final class MongoTable {
        public final static String MARKETER = "MARKETER";//
    }

}