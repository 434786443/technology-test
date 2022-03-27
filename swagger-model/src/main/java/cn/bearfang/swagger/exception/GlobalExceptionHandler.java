package cn.bearfang.swagger.exception;

import cn.bearfang.swagger.vo.BaseResp;
import cn.bearfang.swagger.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 22:43
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResp methodArgHandler(MethodArgumentNotValidException e){
        BaseResp<ResultVO> resultVOBaseResp = new BaseResp<>();
        resultVOBaseResp.setCode(40001);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String s = allErrors.stream().map(ObjectError::getDefaultMessage).findFirst().get();
        resultVOBaseResp.setMessage(s);
        return resultVOBaseResp;
    }

    @ExceptionHandler(value = BizException.class)
    public BaseResp bizExpHandler(BizException e){
        BaseResp<ResultVO> resultVOBaseResp = new BaseResp<>();
        resultVOBaseResp.setCode(40001);
        resultVOBaseResp.setMessage(e.getMessage());
        return resultVOBaseResp;
    }
}
