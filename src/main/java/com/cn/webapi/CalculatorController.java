package com.cn.webapi;

import com.cn.common.exception.ResponseException;
import com.cn.domain.entity.SysUser;
import com.cn.page.AjaxResponse;
import com.cn.page.vo.SumRequestVo;
import com.cn.page.vo.SumResponseVo;
import com.cn.service.CalculatorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Calculator", description = "计算器相关api", position = 1)
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    @Autowired
    CalculatorService calculatorService;
    @ApiOperation(value = "获得计算结果", notes = "获得计算结果", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @PostMapping("/getResult")
    public AjaxResponse<SumResponseVo> getResult(@ApiParam(hidden = true) String userId, @ApiParam(value = "用户token", required = true) @RequestHeader("token") String token,SumRequestVo sumRequestVo){
        try {
            return calculatorService.getResult(sumRequestVo);
        }catch (Exception e){
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
