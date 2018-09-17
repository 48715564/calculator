package com.cn.webapi;

import com.cn.common.exception.ResponseException;
import com.cn.domain.entity.SysWorkDay;
import com.cn.domain.entity.SysWorkDayExample;
import com.cn.page.AjaxResponse;
import com.cn.service.SysWorkDayService;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for managing SysWorkDay.
 * Created by zhoubo on 2016/12/1.
 */
@Api(value = "SysWorkDay", description = "SysWorkDay相关api", position = 1)
@RestController
@RequestMapping("/api")
public class SysWorkDayController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysWorkDayService sysWorkDayService;

    @ApiOperation(value = "创建一个SysWorkDay", notes = "创建一个SysWorkDay", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @PostMapping("/sysWorkDays")
    public AjaxResponse<SysWorkDay> createSysWorkDay(@ApiParam(hidden = true) String userId,@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token,@RequestBody SysWorkDay sysWorkDay){
        try {
            return sysWorkDayService.save(sysWorkDay);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "更新一个SysWorkDay", notes = "更新一个SysWorkDay", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @PutMapping("/sysWorkDays")
    public AjaxResponse<SysWorkDay> updateSysWorkDay(@ApiParam(hidden = true) String userId,@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token,@RequestBody SysWorkDay sysWorkDay){
        try {
            return sysWorkDayService.save(sysWorkDay);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获得所有的SysWorkDay", notes = "获得所有的SysWorkDay", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @GetMapping("/sysWorkDays")
    public AjaxResponse<List<SysWorkDay>> getAllSysWorkDay(@ApiParam(hidden = true) String userId,@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token){
        try {
            return sysWorkDayService.findList(new SysWorkDayExample());
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据id获得SysWorkDay", notes = "根据id获得SysWorkDay", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @GetMapping("/sysWorkDays/{id}")
    public AjaxResponse<SysWorkDay> getSysWorkDay(@ApiParam(hidden = true) String userId,@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token, @PathVariable java.lang.String id){
        try {
            return sysWorkDayService.getByPK(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据id删除SysWorkDay", notes = "根据id删除SysWorkDay", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @DeleteMapping("/sysWorkDays/{id}")
    public AjaxResponse<String> deleteSysWorkDay(@ApiParam(hidden = true) String userId,@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token, @PathVariable java.lang.String id){
        try {
            return sysWorkDayService.delete(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}