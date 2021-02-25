package org.jayjay.air.common.constant;

/**
 * @author jayjay
 * @date 2019/11/1419:02
 * @Description: 状态码
 */
public enum ResultCode  {


    /**
     * 操作成功
     */
    SUCCESS(200,"请求成功"),

    /**
     * 对象创建成功
     */
    CREATED(201,"对象创建成功"),

    /**
     * 请求已经被接受
     */
    ACCEPTED(202,"请求已经被接受"),

    /**
     * 操作已经执行成功，但是没有返回数据
     */
    NO_CONTENT(204,"操作已经执行成功，但是没有返回数据"),

    /**
     * 资源已被移除
     */
    MOVED_PERM(301,"资源已被移除"),

    /**
     * 重定向
     */
    SEE_OTHER(303,"重定向"),

    /**
     * 资源没有被修改
     */
    NOT_MODIFIED(304,"资源没有被修改"),

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    BAD_REQUEST(400,"参数列表错误（缺少，格式不匹配）"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401,"未登录"),

    /**
     * 访问受限，授权过期
     */
    FORBIDDEN(403,"拒绝访问"),

    /**
     * 资源，服务未找到
     */
    NOT_FOUND(404,"资源，服务未找到"),

    /**
     * 不允许的http方法
     */
    BAD_METHOD(405,"不允许的http方法"),

    /**
     * 	服务器无法根据客户端请求的内容特性完成请求
     */
    Not_Acceptable(406,"服务器无法根据客户端请求的内容特性完成请求"),

    /**
     * 请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权
     */
    Proxy_Authentication_Required(407,"请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权"),

    /**
     * 服务器等待客户端发送的请求时间过长，超时
     */
    Request_TimeOut(408,"服务器等待客户端发送的请求时间过长，超时"),

    /**
     * 	服务器完成客户端的 PUT 请求时可能返回此代码，服务器处理请求时发生了冲突
     */
    CONFLICT(409,"服务器完成客户端的 PUT 请求时可能返回此代码，服务器处理请求时发生了冲突"),

    /**
     * 客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置
     */
    Gone(410,"客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置"),

    /**
     * 服务器无法处理客户端发送的不带Content-Length的请求信息
     */
    Length_Required(411,"服务器无法处理客户端发送的不带Content-Length的请求信息"),

    /**
     * 客户端请求信息的先决条件错误
     */
    Precondition_Failed(412,"客户端请求信息的先决条件错误"),

    /**
     * 由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息
     */
    Request_Entity_Too_Large(413,"由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息"),

    /**
     * 请求的URI过长（URI通常为网址），服务器无法处理
     */
    Request_URI_Too_Large(414,"请求的URI过长（URI通常为网址），服务器无法处理"),

    /**
     * 服务器无法处理请求附带的媒体格式
     */
    UNSUPPORTED_TYPE(415,"服务器无法处理请求附带的媒体格式"),
    /**
     * 客户端请求的范围无效
     */
    Requested_range_not_satisfiable(416,"客户端请求的范围无效"),

    /**
     * 服务器无法满足Expect的请求头信息
     */
    Expectation_Failed(417,"服务器无法满足Expect的请求头信息"),

    /**
     * 系统内部错误
     */
    ERROR(500,"请求失败，请联系管理员"),

    /**
     * 接口未实现
     */
    NOT_IMPLEMENTED(501,"接口未实现"),

    /**
     * 作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应
     */
    Bad_Gateway(502,"作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应"),

    /**
     * 由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
     */
    Service_Unavailable(503,"由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中"),

    /**
     * 充当网关或代理的服务器，未及时从远端服务器获取请求
     */
    Gateway_Time_out(504,"充当网关或代理的服务器，未及时从远端服务器获取请求"),

    /**
     * 服务器不支持请求的HTTP协议的版本，无法完成处理
     */
    HTTP_Version_not_supported(505,"服务器不支持请求的HTTP协议的版本，无法完成处理"),
    ;

    private int code;

    private String msg;

    private ResultCode(int code, java.lang.String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public java.lang.String getMsg() {
        return msg;
    }

    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }
}
