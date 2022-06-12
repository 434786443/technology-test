package cn.bearfang.swagger.controller;

import cn.bearfang.swagger.exception.BizException;
import cn.bearfang.swagger.vo.AReq;
import cn.bearfang.swagger.vo.AResp;
import cn.bearfang.swagger.vo.AResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 21:53
 **/
@Api(tags = "秘密花园大门")
@RequestMapping("/secretGarden")
@RestController
public class ModelController {

    @ApiOperation(value = "欢迎来秘密花园", notes = "秘密花园第一道大门")
    @RequestMapping(value="/welcome",method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AResp welcomeGarden(@ApiParam(name = "秘密请求体", value = "这是个秘密请求体") @RequestBody @Validated AReq req){
        String account = req.getAccount();
        AResp aResp = new AResp();
        AResultVO aResultVO = new AResultVO();
        aResultVO.setWelcome("欢迎秘密花园");
        aResp.setCode(0);
        aResp.setMessage("");
        aResp.setResult(aResultVO);

        return aResp;
    }

    @ApiOperation(value = "废弃秘密花园", notes = "烂掉了的秘密花园")
    @RequestMapping(value="/error",method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AResp errorGarden(@ApiParam(name = "秘密请求体", value = "这是个秘密请求体") @RequestBody @Validated AReq req){
        throw new BizException("不要来烂掉了的秘密花园");

    }

//    public AResp conn() throws IOException {
//        URL url = new URL("");
//        URLConnection urlConnection = url.openConnection();
//        Class<?>[] aClass = new Class[1];
//        Object content = url.getContent(aClass);
//    }
}
