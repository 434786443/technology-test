package cn.bearfang.swagger.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 21:54
 **/
@ToString
@Data
@ApiModel(value = "A响应体", description = "这是个A响应体")
public class AResp extends BaseResp<AResultVO>{

}
