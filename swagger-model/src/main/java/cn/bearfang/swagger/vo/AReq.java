package cn.bearfang.swagger.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 21:45
 **/
@ToString
@Data
@ApiModel(value = "A请求对象", description = "这是个A请求对象")
public class AReq extends BaseReq{

    @ApiModelProperty(value = "秘密语", example = "我是秘密")
    private String secretString;


}
