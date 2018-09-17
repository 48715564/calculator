package com.cn.service;

import com.cn.common.service.CrudService;
import com.cn.common.utils.StringUtils;
import com.cn.domain.entity.SysUser;
import com.cn.domain.entity.SysUserExample;
import com.cn.domain.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class SysUserService extends CrudService<SysUserMapper,SysUser,SysUserExample,String> {
    public static final String key = "users";

    @Autowired
    private CacheService cacheService;

    public SysUser getUserByUserName(String username) {
        SysUser sysUser = (SysUser) cacheService.getHashMapByKey(key, username, SysUser.class);
        if (sysUser == null) {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUsernameEqualTo(username);
            List<SysUser> list = dao.selectByExample(sysUserExample);
            if (list.size() > 0) {
                sysUser = list.get(0);
                cacheService.saveHashMapCache(key, username, sysUser);
            }
        }
        return sysUser;
    }

    public SysUser getUserByUserID(String id) {
        SysUser sysUser = null;
        sysUser = (SysUser) cacheService.getHashMapByKey(key, id, SysUser.class);
        if (sysUser==null) {
            sysUser = dao.selectByPrimaryKey(id);
        }
        cacheService.saveHashMapCache(key, id, sysUser);
        return sysUser;
    }

    @Transactional
    public SysUser saveHyUser(SysUser sysUser) {
        if (sysUser.getIsNewRecord()) {
            sysUser.preInsert();
            dao.insert(sysUser);
        } else {
            sysUser.preUpdate();
            dao.updateByPrimaryKeySelective(sysUser);
        }
        if(StringUtils.isBlank(sysUser.getUsername())) {
            sysUser.setUsername(this.getUserByUserID(sysUser.getId()).getUsername());
        }
        cacheService.removeHashMapCache(key, sysUser.getId());
        cacheService.removeHashMapCache(key, sysUser.getUsername());
        return sysUser;
    }
}
