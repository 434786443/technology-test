package cn.bearfang.swagger.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 21:45
 **/
@ToString
@Data
public class BaseResp<R extends ResultVO> implements Serializable {

    @ApiModelProperty(value = "响应码", example = "0")
    private int code;

    @ApiModelProperty(value = "相应报文", example = "ok")
    private String message;

    @ApiModelProperty(value = "响应内容")
    private R result;
}
