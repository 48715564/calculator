/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.cn.common.service;

import com.cn.common.persistence.BaseEntity;
import com.cn.common.persistence.CrudMapper;
import com.cn.page.AjaxResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Service基类
 */
public abstract class CrudService<D extends CrudMapper<Example,Entity,T>, Entity extends BaseEntity,Example,T> extends BaseService<Entity> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public AjaxResponse<Entity> getByPK(T id) {
		return getAjaxResponse(dao.selectByPrimaryKey(id));
	}

	/**
	 * 获取单条数据
	 * @param example
	 * @return
	 */
	public AjaxResponse<Entity> get(Example example) {
		List<Entity> list = dao.selectByExample(example);
		if(list!=null&&list.size()>0){
			return getAjaxResponse(list.get(0));
		}
		return null;
	}

	/**
	 * 查询列表数据
	 * @param example
	 * @return
	 */
	public AjaxResponse<List<Entity>> findList(Example example) {
		return getAjaxResponseList(dao.selectByExample(example));
	}

	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param example
	 * @return
	 */
	public AjaxResponse<PageInfo<Entity>> findPage(Integer page, Integer limit, Example example) {
		PageHelper.startPage(page, limit);
		List<Entity> list = dao.selectByExample(example);
		return getAjaxResponsePage(list);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional
	public AjaxResponse<Entity> save(Entity entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insertSelective(entity);
		}else{
			entity.preUpdate();
			dao.updateByPrimaryKeySelective(entity);
		}
		return getAjaxResponse(entity);
	}

	/**
	 * 根据条件更新数据
     * @param entity
     */
	@Transactional
	public AjaxResponse<String> updateByExample(Entity entity, Example example) {
			dao.updateByExample(entity,example);
            return getMsgAjaxResponse("更新成功！");
	}

	/**
	 * 根据条件更新值不为空的数据
     * @param entity
     */
	@Transactional
	public AjaxResponse<String> updateByExampleSelective(Entity entity, Example example) {
		dao.updateByExampleSelective(entity,example);
        return getMsgAjaxResponse("更新成功！");
	}

	/**
	 * 保存数据（插入或更新）
     * @param entity
     */
	@Transactional
	public AjaxResponse<String> updateByPrimaryKey(Entity entity) {
		dao.updateByPrimaryKey(entity);
        return getMsgAjaxResponse("更新成功！");
	}

	/**
	 * 删除数据
	 * @param id
	 */
	@Transactional
	public AjaxResponse<String> delete(T id) {
		dao.deleteByPrimaryKey(id);
        return getMsgAjaxResponse("删除成功！");
	}


	/**
	 * 删除全部数据
	 * @param ids
	 */
	@Transactional
	public AjaxResponse<String> deleteAll(Collection<T> ids) {
		for (T id : ids) {
			dao.deleteByPrimaryKey(id);
		}
        return getMsgAjaxResponse("删除成功！");
	}

}
