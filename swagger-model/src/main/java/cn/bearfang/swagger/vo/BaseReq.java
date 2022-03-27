package cn.bearfang.swagger.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 21:45
 **/
@ToString
@Data
public class BaseReq implements Serializable {


    @ApiModelProperty(value = "账号", example = "bearfang")
    @NotEmpty(message = "账号不可为空")
    private String account;

}
