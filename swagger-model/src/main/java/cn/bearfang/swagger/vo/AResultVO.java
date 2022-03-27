package cn.bearfang.swagger.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 21:51
 **/
@ToString
@Data
@ApiModel(value = "响应内容体A")
public class AResultVO extends ResultVO{

    private String className;

    private String methodName;

    @ApiModelProperty(value = "欢迎语", example = "欢迎光临啊啊啊")
    private String welcome;
}
