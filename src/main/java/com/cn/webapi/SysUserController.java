package com.cn.webapi;

import com.cn.common.exception.ResponseException;
import com.cn.domain.entity.SysUser;
import com.cn.domain.entity.SysUserExample;
import com.cn.page.AjaxResponse;
import com.cn.service.SysUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for managing SysUser.
 * Created by zhoubo on 2016/12/1.
 */
@Api(value = "SysUser", description = "SysUser相关api", position = 1)
@RestController
@RequestMapping("/api")
public class SysUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "创建一个SysUser", notes = "创建一个SysUser", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @PostMapping("/sysUsers")
    public AjaxResponse<SysUser> createSysUser(@ApiParam(hidden = true) String userId, @ApiParam(value = "用户token", required = true) @RequestHeader("token") String token, @RequestBody SysUser sysUser){
        try {
            return sysUserService.save(sysUser);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "更新一个SysUser", notes = "更新一个SysUser", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @PutMapping("/sysUsers")
    public AjaxResponse<SysUser> updateSysUser(@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token, @RequestBody SysUser sysUser){
        try {
            return sysUserService.save(sysUser);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获得所有的SysUser", notes = "获得所有的SysUser", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @GetMapping("/sysUsers")
    public AjaxResponse<List<SysUser>> getAllSysUser(@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token){
        try {
            return sysUserService.findList(new SysUserExample());
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据id获得SysUser", notes = "根据id获得SysUser", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @GetMapping("/sysUsers/{id}")
    public AjaxResponse<SysUser> getSysUser(@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token, @PathVariable String id){
        try {
            return sysUserService.getByPK(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据id删除SysUser", notes = "根据id删除SysUser", position = 1)
    @ApiResponses({@ApiResponse(code = 200, message = "运行结果")})
    @DeleteMapping("/sysUsers/{id}")
    public AjaxResponse<String> deleteSysUser(@ApiParam(value = "用户token", required = true) @RequestHeader("token") String token, @PathVariable String id){
        try {
            return sysUserService.delete(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}