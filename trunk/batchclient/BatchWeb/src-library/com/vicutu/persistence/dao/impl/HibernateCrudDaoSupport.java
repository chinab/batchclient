package com.vicutu.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.persistence.dao.BaseCrudDao;
import com.vicutu.persistence.dao.utils.BeanUtils;
import com.vicutu.persistence.dao.utils.HibernateUtils;
import com.vicutu.persistence.dao.utils.StringUtil;

/**
 * 基于Hibernate的Crud DAO基础实现，所有使用Hibernate并支持Crud操作的DAO都继承该类。<BR>
 * 所有方法都必须声明为抛出Spring统一的DataAccessException异常，以便于做统一的异常处理。<BR>
 * 可用的异常类如下：
 * 
 * <pre>
 * DataAccessException
 *    CleanupFailureDataAccessException
 *    ConcurrencyFailureException
 *         OptimisticLockingFailureException
 *              ObjectOptimisticLockingFailureException
 *                   HibernateOptimisticLockingFailureException
 *         PessimisticLockingFailureException
 *              CannotAcquireLockException
 *              CannotSerializeTransactionException
 *              DeadlockLoserDataAccessException
 *    DataAccessResourceFailureException
 *         CannotCreateRecordException
 *         CannotGetCciConnectionException
 *         CannotGetJdbcConnectionException
 *    DataIntegrityViolationException
 *    DataRetrievalFailureException
 *         IncorrectResultSetColumnCountException
 *         IncorrectResultSizeDataAccessException
 *              EmptyResultDataAccessException
 *         LobRetrievalFailureException
 *         ObjectRetrievalFailureException
 *              HibernateObjectRetrievalFailureException
 *    DataSourceLookupFailureException
 *    InvalidDataAccessApiUsageException
 *    InvalidDataAccessResourceUsageException
 *         BadSqlGrammarException
 *         CciOperationNotSupportedException
 *         HibernateQueryException
 *         IncorrectUpdateSemanticsDataAccessException
 *              JdbcUpdateAffectedIncorrectNumberOfRowsException
 *         InvalidResultSetAccessException
 *         InvalidResultSetAccessException
 *         RecordTypeNotSupportedException
 *         TypeMismatchDataAccessException
 *    PermissionDeniedDataAccessException
 *    UncategorizedDataAccessException
 *         HibernateJdbcException
 *         HibernateSystemException
 *         SQLWarningException
 *         UncategorizedSQLException
 * </pre>
 * 
 * @author Wang Yuxing
 * @version 1.0
 * @param <E>
 */
public class HibernateCrudDaoSupport<E> extends HibernateDaoSupport implements BaseCrudDao<E> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<E> pojoClass;

	@SuppressWarnings("unchecked")
	public HibernateCrudDaoSupport() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			pojoClass = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}
	}

	protected Class<E> getPojoClass() {
		return pojoClass;
	}

	@Override
	public void save(E entity) throws DataAccessException {
		super.getHibernateTemplate().save(entity);
	}

	@Override
	public void save(Collection<E> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			Iterator<E> it = entities.iterator();
			for (E e = null; it.hasNext();) {
				e = it.next();
				save(e);
			}
		}
	}

	@Override
	public void delete(E entity) throws DataAccessException {
		super.getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Collection<E> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			Iterator<E> it = entities.iterator();
			for (E e = null; it.hasNext();) {
				e = it.next();
				save(e);
			}
		}
	}

	@Override
	public void update(E entity) throws DataAccessException {
		super.getHibernateTemplate().update(entity);
	}

	@Override
	public void update(Collection<E> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			Iterator<E> it = entities.iterator();
			for (E e = null; it.hasNext();) {
				e = it.next();
				update(e);
			}
		}
	}

	@Override
	public void saveOrUpdate(E entity) throws DataAccessException {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(Collection<E> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			Iterator<E> it = entities.iterator();
			for (E e = null; it.hasNext();) {
				e = it.next();
				saveOrUpdate(e);
			}
		}
	}

	@Override
	public void merge(E entity) throws DataAccessException {
		super.getHibernateTemplate().merge(entity);
	}

	@Override
	public void merge(Collection<E> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			Iterator<E> it = entities.iterator();
			for (E e = null; it.hasNext();) {
				e = it.next();
				merge(e);
			}
		}
	}

	@Override
	public E findByUniqueParam(String uniqueParamName, String value) throws DataAccessException {
		List<E> result = this.findByParam(uniqueParamName, value);
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() throws DataAccessException {
		Criteria criteria = getSession().createCriteria(this.getPojoClass());
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByParam(String paramName, Object value) throws DataAccessException {
		if (StringUtils.isEmpty(paramName) || value == null) {
			return null;
		}
		StrBuilder buf = new StrBuilder();
		buf.append("FROM ").append(this.getPojoClass().getSimpleName()).append(" WHERE ").append(paramName).append(
				" = :condition");
		List<E> entities = this.getHibernateTemplate().findByNamedParam(buf.toString(), "condition", value);
		return entities;
	}

	@Override
	public List<E> findByParamPagination(String paramName, Object value, int pageSize, int pageNumber)
			throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(paramName, value);
		return findByParamsPagination(paramMap, null, null, pageSize, pageNumber);
	}

	@Override
	public List<E> findByParamPagination(String paramName, Object value, String orderParam, boolean isDescending,
			int pageSize, int pageNumber) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(paramName, value);
		return findByParamsPagination(paramMap, orderParam, isDescending, pageSize, pageNumber);
	}

	@Override
	public List<E> findByParams(Map<String, Object> paramMap) {
		return findByParams(paramMap, null, null);
	}

	@Override
	public List<E> findByParams(String extraCondition, Map<String, Object> extraParams) throws DataAccessException {
		return this.findByParams(null, extraCondition, extraParams);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findByParams(Map<String, Object> paramMap, String extraCondition, Map<String, Object> extraParams)
			throws DataAccessException {
		if ((paramMap == null || paramMap.size() == 0)
				&& (extraCondition == null || extraParams == null || extraParams.size() == 0)) {
			return this.findAll();
		}
		StringBuilder hqlSb = new StringBuilder("FROM ");
		hqlSb.append(this.getPojoClass().getSimpleName());
		hqlSb.append(" WHERE ");
		if (paramMap != null && paramMap.size() > 0) {
			Iterator<String> mapKeys = paramMap.keySet().iterator();
			for (int i = 0; mapKeys.hasNext(); i++) {
				if (i != 0) {
					hqlSb.append(" AND ");
				}
				String paramName = mapKeys.next();
				hqlSb.append(paramName + " =:" + paramName);
			}
		}
		if (extraCondition != null && extraCondition.length() > 0) {
			hqlSb.append(" ").append(extraCondition);
		}

		String hql = hqlSb.toString();
		logger.debug("Query by : {}", hql);
		// 设置参数名与参数值
		int paramsSize = ((paramMap == null) ? 0 : paramMap.size()) + ((extraParams == null) ? 0 : extraParams.size());
		logger.debug("Query by : {} with {} parameters", hql, paramsSize);
		String[] queryParamNames = new String[paramsSize];
		Object[] queryParams = new Object[paramsSize];
		int i = 0;
		if (paramMap != null && paramMap.size() > 0) {
			Iterator<String> mapKeys = paramMap.keySet().iterator();
			for (; mapKeys.hasNext(); i++) {
				Object curKey = mapKeys.next();
				queryParamNames[i] = curKey.toString();
				queryParams[i] = paramMap.get(curKey);
			}
		}
		if (extraCondition != null && extraParams != null && extraParams.size() > 0) {
			Iterator<String> mapKeys = extraParams.keySet().iterator();
			for (; mapKeys.hasNext(); i++) {
				Object curKey = mapKeys.next();
				queryParamNames[i] = curKey.toString();
				queryParams[i] = extraParams.get(curKey);
			}
		}
		List<E> entities = this.getHibernateTemplate().findByNamedParam(hql, queryParamNames, queryParams);
		return entities;
	}

	@Override
	public List<E> findByParamsPagination(Map<String, Object> paramMap, int pageSize, int pageNumber)
			throws DataAccessException {
		return this.findByParamsPagination(paramMap, null, null, pageSize, pageNumber);
	}

	@Override
	public List<E> findByParamsPagination(String condition, Map<String, Object> params, int pageSize, int pageNumber)
			throws DataAccessException {
		return this.findByParamsPagination(null, condition, params, pageSize, pageNumber);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findByParamsPagination(Map<String, Object> paramMap, String extraCondition,
			Map<String, Object> extraParams, int pageSize, int pageNumber) throws DataAccessException {
		StringBuilder hqlSb = new StringBuilder(" FROM ");
		hqlSb.append(this.getPojoClass().getSimpleName());// 实体名称
		if (paramMap != null && paramMap.size() > 0) {
			// 增加查询条件
			hqlSb.append(" WHERE ");
			Iterator<String> keys = paramMap.keySet().iterator();
			for (int i = 0; keys.hasNext(); i++) {
				if (i != 0) {
					hqlSb.append(" AND ");
				}
				String paramName = keys.next();
				hqlSb.append(paramName).append(" =:").append(paramName);
			}
		}
		// 增加额外的查询条件
		if (extraCondition != null && extraCondition.length() > 0) {
			hqlSb.append(extraCondition);
		}
		// 开始查询
		Query q = this.getSession().createQuery(hqlSb.toString());
		q.setMaxResults(pageSize);
		q.setFirstResult(pageNumber * pageSize);
		if (paramMap != null && paramMap.size() > 0) {
			// 设值
			Iterator<String> keys = paramMap.keySet().iterator();
			for (int i = 0; keys.hasNext(); i++) {
				String key = keys.next();
				q.setParameter(key, paramMap.get(key));
			}
		}
		if (extraCondition != null && extraParams != null && extraParams.size() > 0) {
			// 设值
			Iterator<String> keys = extraParams.keySet().iterator();
			for (int i = 0; keys.hasNext(); i++) {
				String key = keys.next();
				q.setParameter(key, extraParams.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<E> findByParamsPagination(Map<String, Object> paramMap, String orderParam, boolean isDescending,
			int pageSize, int pageNumber) throws DataAccessException {
		return this.findByParamsPagination(paramMap, null, null, orderParam, isDescending, pageSize, pageNumber);
	}

	@Override
	public List<E> findByParamsPagination(Map<String, Object> paramMap, String extraCondition,
			Map<String, Object> extraParams, String orderParam, boolean isDescending, int pageSize, int pageNumber)
			throws DataAccessException {
		String[] attributeNames = HibernateUtils.getEntityAttributes(this.getSessionFactory(), this.getPojoClass());
		StringBuilder hqlSb = new StringBuilder(" SELECT ");
		StringUtil.mergeString(attributeNames, ",", hqlSb);
		hqlSb.append(" FROM ");
		hqlSb.append(this.getPojoClass().getSimpleName());// 实体名称
		if (paramMap != null && paramMap.size() > 0) {
			// 增加查询条件
			hqlSb.append(" WHERE ");
			Iterator<String> keys = paramMap.keySet().iterator();
			for (int i = 0; keys.hasNext(); i++) {
				if (i != 0) {
					hqlSb.append(" AND ");
				}
				String paramName = keys.next();
				hqlSb.append(paramName).append(" =:").append(paramName);
			}
		}
		// 增加额外的查询条件
		if (extraCondition != null && extraCondition.length() > 0) {
			hqlSb.append(extraCondition);
		}
		if (orderParam != null) {// 排序
			hqlSb.append(" ORDER BY ").append(orderParam);
			if (isDescending) {
				hqlSb.append(" DESC"); // 降序
			} else {
				hqlSb.append(" ASC"); // 升序
			}
		}
		Query q = this.getSession().createQuery(hqlSb.toString());
		if (paramMap != null && paramMap.size() > 0) {
			// 查询条件设值
			Iterator<String> keys = paramMap.keySet().iterator();
			for (int i = 0; keys.hasNext(); i++) {
				String key = keys.next();
				q.setParameter(key, paramMap.get(key));
			}
		}
		if (extraCondition != null && extraParams != null && extraParams.size() > 0) {
			// 设值
			Iterator<String> keys = extraParams.keySet().iterator();
			for (int i = 0; keys.hasNext(); i++) {
				String key = keys.next();
				q.setParameter(key, extraParams.get(key));
			}
		}
		List<E> pagedList = new ArrayList<E>();
		ScrollableResults entities = q.scroll();
		// 按照分页条件取一页的实体返回
		if (entities.first() == true) {
			entities.scroll(pageSize * pageNumber);
			for (int i = 0; i < pageSize; i++, entities.scroll(1)) {
				Object[] row = entities.get();
				if (row == null) {
					break;
				}
				E e = null;
				try {
					e = (E) this.getPojoClass().getConstructor().newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
					throw new ObjectRetrievalFailureException("初始化实体类" + this.getPojoClass().getName() + "失败", e1);
				}
				try {
					for (int j = 0; j < row.length; j++) {
						BeanUtils.forceSetProperty(e, attributeNames[j], row[j]);
					}
				} catch (NoSuchFieldException e1) {
					logger.info("set attributes error", e1);
				}
				pagedList.add(e);
			}
		}
		return pagedList;
	}

	@Override
	public List<E> findAllByPagination(int pageSize, int PageNumber) throws DataAccessException {
		return findByParamPagination("", "", pageSize, PageNumber);
	}

	@Override
	public List<E> findAllByPagination(String orderParam, boolean isDescending, int pageSize, int pageNumber)
			throws DataAccessException {
		return this.findByParamsPagination(null, orderParam, isDescending, pageSize, pageNumber);
	}

	@Override
	public long countAll() throws DataAccessException {
		return this.countByParams(null);
	}

	@Override
	public long countByParam(String paramName, Object value) throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, value);
		return this.countByParams(params);
	}

	@Override
	public long countByParams(Map<String, Object> paramMap) throws DataAccessException {
		return this.countByParams(paramMap, null, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public long countByParams(Map<String, Object> paramMap, String extraCondition, Map<String, Object> extraParams)
			throws DataAccessException {
		String hql = "SELECT COUNT(*) FROM ";
		Iterator<Long> it = null;
		if ((paramMap == null || paramMap.size() == 0) && extraCondition == null
				&& (extraParams == null || extraParams.size() == 0)) {
			hql = hql + this.getPojoClass().getSimpleName();
			it = this.getSession().createQuery(hql).list().iterator();
		} else {
			StringBuilder hqlSb = new StringBuilder(hql);
			hqlSb.append(this.getPojoClass().getSimpleName());
			hqlSb.append(" WHERE ");
			Iterator<String> mapKeys = null;
			if (paramMap != null && paramMap.size() > 0) {
				mapKeys = paramMap.keySet().iterator();
				for (int i = 0; mapKeys.hasNext(); i++) {
					if (i != 0) {
						hqlSb.append(" AND ");
					}
					String paramName = mapKeys.next();
					hqlSb.append(paramName + " =:" + paramName);
				}
			}
			if (extraCondition != null && extraCondition.length() > 0) {
				hqlSb.append(" ").append(extraCondition);
			}
			hql = hqlSb.toString();
			logger.debug("Query by : " + hql);
			// 设置参数名与参数值
			String[] queryParamNames = null;
			Object[] queryParams = null;
			int paramsSize = ((paramMap == null) ? 0 : paramMap.size())
					+ ((extraParams == null) ? 0 : extraParams.size());
			queryParamNames = new String[paramsSize];
			queryParams = new Object[paramsSize];
			int i = 0;
			if (paramMap != null && paramMap.size() > 0) {
				mapKeys = paramMap.keySet().iterator();
				for (; mapKeys.hasNext(); i++) {
					Object curKey = mapKeys.next();
					queryParamNames[i] = curKey.toString();
					queryParams[i] = paramMap.get(curKey);
				}
			}
			// Extra conditions
			if (extraCondition != null && extraParams != null && extraParams.size() > 0) {
				mapKeys = extraParams.keySet().iterator();
				for (; mapKeys.hasNext(); i++) {
					Object curKey = mapKeys.next();
					queryParamNames[i] = curKey.toString();
					queryParams[i] = extraParams.get(curKey);
				}
			}
			// for (int j = 0; j < paramsSize; j++) {
			// logger.debug(" " + queryParamNames[j]);
			// logger.debug(" " + queryParams[j]);
			// }
			it = this.getHibernateTemplate().findByNamedParam(hql, queryParamNames, queryParams).iterator();
			// } else {
			// hql = hql + this.getPojoClass().getSimpleName();
			// it = this.getSession().createQuery(hql).list().iterator();
		}
		if (it.hasNext()) {
			return Long.parseLong(it.next().toString());
		}
		return 0;
	}
}
