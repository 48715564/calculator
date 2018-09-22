package com.cn.service;

import com.cn.common.service.CrudService;
import com.cn.common.utils.StringUtils;
import com.cn.domain.entity.SysUser;
import com.cn.domain.entity.SysUserExample;
import com.cn.domain.mapper.SysUserMapper;
import com.cn.page.AjaxResponse;
import com.cn.page.vo.SumRequestVo;
import com.cn.page.vo.SumResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author daocloud
 * @version 0.0.1
 * @date 2018/05/28
 * @time 13:50
 * @function 功能:
 * @describe 版本描述:
 * @modifyLog 修改日志:
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Service
public class CalculatorService{
   public AjaxResponse<SumResponseVo> getResult(SumRequestVo sumRequestVo){
       SumResponseVo sumResponseVo = new SumResponseVo();
       sumResponseVo.setJsts(String.valueOf(new Random().nextInt(100)));
       sumResponseVo.setMswtx(String.valueOf(new Random().nextInt(100)));
       sumResponseVo.setTxje(String.valueOf(new Random().nextInt(100)));
       sumResponseVo.setTxlx(String.valueOf(new Random().nextInt(100)));
       return new AjaxResponse<>(sumResponseVo);
   }
}
