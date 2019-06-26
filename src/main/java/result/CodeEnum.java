package result;

import lombok.Data;

public enum CodeEnum {

    /**
     * 全局返回值
     */
    //成功
    OK(200,"OK"),
    //服务器不理解客户端的请求，未做任何处理
    BAD(400,"Bad Request"),
    //用户未提供身份验证凭据，或者没有通过身份验证
    UNAUTHORIZED(401,"Unauthorized"),
    //用户通过了身份验证，但是不具有访问资源所需的权限
    FORBIDDEN(403,"Forbidden"),
    //所请求的资源不存在，或不可用
    NOT_FOUND(404,"Not Found"),
    //用户已经通过身份验证，但是所用的 HTTP 方法不在他的权限之内
    METHOD_NOT_ALLOWED(405,"Method Not Allowed"),
    //所请求的资源已从这个地址转移，不再可用
    GONE(410,"Gone"),
    //户端要求的返回格式不支持
    UNSUPPORTED_MEDIA_TYPE(415,"Unsupported Media Type"),
    //客户端上传的附件无法处理，导致请求失败
    UNPROCESSABLE_ENTITY(422,"Unprocessable Entity"),
    //客户端的请求次数超过限额
    TOO_MANY_REQUESTS(429,"Too Many Requests"),
    //客户端请求有效，服务器处理时发生了意外
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    //服务器无法处理请求，一般用于网站维护状态
    SERVICE_UNAVAILABLE(503,"Service Unavailable"),

    /**
     * get 返回值
     */
    //get 方法操作成功
    GET_OK(200,"GET OK"),
    GET_REDIRECT(302,"GET See Other"),

    /**
     * post 返回值
     */
    //post 方法操作成功
    POST_OK(201,"POST Created"),
    POST_REDIRECT(303,"POST See Other"),

    /**
     * put 返回值
     */
    //put 方法操作成功
    PUT_OK(200,"PUT OK"),
    PUT_REDIRECT(303,"PUT See Other"),

    /**
     * patch 返回值
     */
    //patch 方法操作成功
    PATCH_OK(200,"PATCH OK"),

    /**
     * delete 返回值
     */
    //delete 方法操作失败
    DELETE_ERROR(204,"DELETE No Content"),
    DELETE_REDIRECT(303,"DELETE See Other");

    Integer code;
    String desc;

    CodeEnum(Integer code, String desc) {
    }

    public static void main(String[] args) {
        System.out.println(CodeEnum.BAD);
    }
}
