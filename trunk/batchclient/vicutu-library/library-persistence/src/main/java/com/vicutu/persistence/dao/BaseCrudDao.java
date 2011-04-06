package com.vicutu.persistence.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface BaseCrudDao<E> extends BaseDao {

	/**
	 * 持久化一个实体。
	 * 
	 * @param entity 处于临时状态的实体。
	 * @throws DataAccessException
	 */
	void save(E entity) throws DataAccessException;

	/**
	 * 持久化多个实体。
	 * 
	 * @param entities 处于临时状态的实体的集合。
	 * @throws DataAccessException
	 */
	void save(Collection<E> entities) throws DataAccessException;

	/**
	 * 更新实体。
	 * 
	 * @param entity 处于持久化状态的实体。
	 * @throws DataAccessException
	 */
	void update(E entity) throws DataAccessException;

	/**
	 * 更新多个实体。
	 * 
	 * @param entities 处于持久化状态的实体的集合。
	 * @throws DataAccessException
	 */
	void update(Collection<E> entities) throws DataAccessException;

	/**
	 * 持久化或者更新实体。
	 * 
	 * @param entity 处于临时或者持久化状态的实体。
	 * @throws DataAccessException
	 */
	void saveOrUpdate(E entity) throws DataAccessException;

	/**
	 * 持久化或者更新多个实体。
	 * 
	 * @param entities 处于临时或者持久化状态的实体的集合。
	 * @throws DataAccessException
	 */
	void saveOrUpdate(Collection<E> entities) throws DataAccessException;

	/**
	 * 更新处于游离状态的实体，更新后实体对象仍然处于游离状态。
	 * 
	 * @param entity 处于游离状态的实体。
	 * @throws DataAccessException
	 */
	void merge(E entity) throws DataAccessException;

	/**
	 * 保存处于游离状态的多个实体，更新后实体对象仍然处于游离状态。
	 * 
	 * @param entities 处于游离状态的实体的集合。
	 * @throws DataAccessException
	 */
	void merge(Collection<E> entities) throws DataAccessException;

	/**
	 * 删除一个持久化的实体。
	 * 
	 * @param entity 处于持久化状态的实体。
	 * @throws DataAccessException
	 */
	void delete(E entity) throws DataAccessException;

	/**
	 * 删除多个持久化的实体。
	 * 
	 * @param entities 处于持久化状态的实体的集合。
	 * @throws DataAccessException
	 */
	void delete(Collection<E> entities) throws DataAccessException;

	/**
	 * 按照唯一的（Unique）属性名和属性值，查询得到一个实体对象。
	 * 
	 * @param paramName 实体唯一属性名
	 * @param value 属性值
	 * @return 持久化实体
	 * @throws DataAccessException
	 */
	E findByUniqueParam(String uniqueParamName, String value) throws DataAccessException;

	/**
	 * 按照指定的属性值查询多个实体对象。
	 * 
	 * @param paramName 实体属性名。
	 * @param value 对应实体属性名的属性值。
	 * @return 持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParam(String paramName, Object value) throws DataAccessException;

	/**
	 * 按照指定属性值查找多个实例，并按照分页条件分页，返回指定页的实体集合。
	 * 
	 * @param paramName 作为查询条件的属性名。
	 * @param value 查询条件属性值。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamPagination(String paramName, Object value, int pageSize, int pageNumber)
			throws DataAccessException;

	/**
	 * 按照指定属性值、排序条件和分页条件进行查找指定页的多个实例。
	 * 
	 * @param paramName 作为查询条件的属性名。
	 * @param value 查询条件属性值。
	 * @param orderParam 排序的属性名，null为没有排序条件
	 * @param isDescending 是否是降序排序，当orderParam可用是才有效。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamPagination(String paramName, Object value, String orderParam, boolean isDescending,
			int pageSize, int pageNumber) throws DataAccessException;

	/**
	 * 按照指定的属性值映射查找多个实体对象。
	 * 
	 * @param paramMap 实体类属性名和属性值的映射。
	 * @return 持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParams(Map<String, Object> paramMap) throws DataAccessException;

	/**
	 * 按照指定的条件表达式查找多个实体对象。
	 * <pre>
	 * Map params = new HashMap();
	 * params.put("name", "Jack");
	 * params.put("age", "16");
	 * List result = findByParams("userName = :name and userAge = :age", params);
	 * </prc>
	 * @param extraCondition 包含形如":param"的条件表达式
	 * @param extraParams 条件表达式中的参数值
	 * @return 持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParams(String extraCondition, Map<String, Object> extraParams) throws DataAccessException;

	/**
	 * 按照指定的属性值映射查找多个实体对象。
	 * <pre>
	 * Map params1 = new HashMap();
	 * params1.put("name", "Jack");
	 * Map params2 = new HashMap();
	 * params.put("age1", "16");
	 * params.put("age2", "18");
	 * List result = findByParams(params1, " and (userAge = :age1 or userAge = :age2)", params);
	 * </prc>
	 * @param paramMap 实体类属性名和属性值的映射。
	 * @param extraCondition 额外的查询条件，跟在paramMap的后面，类似“ OR XX = :xx”的形式，没有则为null。
	 * @param extraParams 配合extraCondition使用，用于保存extraCondition中的变量。
	 * @return 持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParams(Map<String, Object> paramMap, String extraCondition, Map<String, Object> extraParams)
			throws DataAccessException;

	/**
	 * 按照指定属性值映射、分页条件查找多个实例。
	 * @param paramMap 查询条件，
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamsPagination(Map<String, Object> paramMap, int pageSize, int pageNumber)
			throws DataAccessException;

	/**
	 * 按照指定属性值映射查找多个实例，并按照分页条件分页，返回指定页的实体列表。
	 * @param condition 查询条件，类似“ WHERE AA = :aa OR XX = :xx”的形式，没有则为null。
	 * @param params 配合condition使用，用于保存condition中的变量值。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamsPagination(String condition, Map<String, Object> params, int pageSize, int pageNumber)
			throws DataAccessException;

	/**
	 * 按照指定属性值映射查找多个实例，并按照分页条件分页，返回指定页的实体列表。
	 * 
	 * @param paramMap 查询条件，如果需要更灵活的条件，使用extraCondition。
	 * @param extraCondition 额外的查询条件，跟在paramMap的后面，类似“ OR XX = :xx”的形式，没有则为null。
	 * @param extraParams 配合extraCondition使用，用于保存extraCondition中的变量。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamsPagination(Map<String, Object> paramMap, String extraCondition,
			Map<String, Object> extraParams, int pageSize, int pageNumber) throws DataAccessException;

	/**
	 * 按照指定属性值映射、排序条件和分页条件进行查找指定页的多个实例。
	 * @param paramMap 查询条件。
	 * @param orderParam 排序的属性名，null为没有排序条件
	 * @param isDescending 是否是降序排序，当orderParam可用是才有效。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return  指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamsPagination(Map<String, Object> paramMap, String orderParam, boolean isDescending, int pageSize,
			int pageNumber) throws DataAccessException;

	/**
	 * 按照指定参数值映射和额外的查询条件、排序条件和分页条件查找多个实例。
	 * @param paramMap 查询条件。
	 * @param extraCondition 额外查询条件，跟在paramMap的后面，类似“ OR XX = :xx”的形式，没有则为null。
	 * @param extraParams 配合extraCondition使用，用于保存extraCondition中的变量。
	 * @param orderParam 排序的属性名，null为没有排序条件
	 * @param isDescending 是否是降序排序，当orderParam可用是才有效。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findByParamsPagination(Map<String, Object> paramMap, String extraCondition,
			Map<String, Object> extraParams, String orderParam, boolean isDescending, int pageSize, int pageNumber)
			throws DataAccessException;

	/**
	 * 按照泛型的实体类型查询得到所有持久化实体。
	 * 如果实体类被设置为缓存的，则该方法首先从缓存中获取。
	 * @return 所有持久化实体的集合
	 * @throws DataAccessException
	 */
	List<E> findAll() throws DataAccessException;

	/**
	 * 在所有的持久化实体中查询指定页的实体集合。
	 * 
	 * @param pageSize 每页大小
	 * @param PageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findAllByPagination(int pageSize, int pageNumber) throws DataAccessException;

	/**
	 * 在所有的持久化实体中按照排序方式查询指定页的实体集合。
	 * 
	 * @param orderParam 排序的属性名，null为没有排序条件
	 * @param isDescending 是否是降序排序，当orderParam可用是才有效。
	 * @param pageSize 每页大小。
	 * @param pageNumber 查询的页码，0表示第一页。
	 * @return 指定页的持久化实体集合
	 * @throws DataAccessException
	 */
	List<E> findAllByPagination(String orderParam, boolean isDescending, int pageSize, int pageNumber)
			throws DataAccessException;

	/**
	 * 统计所有持久化实体对象的数量。
	 * 
	 * @return 所有持久化实体对象的数量
	 * @throws DataAccessException
	 */
	long countAll() throws DataAccessException;

	/**
	 * 按条件统计持久化实体对象的数量。
	 * 
	 * @param paramName
	 * @param value
	 * @return 持久化实体的数量
	 * @throws DataAccessException
	 */
	long countByParam(String paramName, Object value) throws DataAccessException;

	/**
	 * 按给定的限制条件统计持久化实体对象的数量。
	 * 
	 * @param paramMap 查询条件参数
	 * @return 符合条件的持久化实体的数量
	 * @throws DataAccessException
	 */
	long countByParams(Map<String, Object> paramMap) throws DataAccessException;

	/**
	 * 按给定的限制条件统计实体对象的数量。
	 * @param paramMap 查询条件参数
	 * @param extraCondition 额外查询条件，跟在paramMap的后面，类似“ OR XX = :xx”的形式，没有则为null。
	 * @param extraParams 配合extraCondition使用，用于保存extraCondition中的变量。
	 * @return 符合条件的持久化实体的数量
	 * @throws DataAccessException
	 */
	long countByParams(Map<String, Object> paramMap, String extraCondition, Map<String, Object> extraParams)
			throws DataAccessException;
}
