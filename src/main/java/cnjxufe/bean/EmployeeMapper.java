package cnjxufe.bean;

/**
 * @author hsw
 * @create 2018-11-10  20:26
 */
public interface EmployeeMapper {

    /**
     * 通过号码查找员工
     * @param id
     * @return
     */
    Employee getEmployeeById(String id);

    /**
     * 添加员工
     * 增删改操作可以返回boolean、int、long及其包装类。
     * 且不用在映射文件中标识。
     * @param employee
     * @return
     */
    int insertEmployee(Employee employee);

    /**
     * 更新employee
     * @param employee
     * @return
     */
    long updateEmployee(Employee employee);

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    boolean deleteEmployeeById(String id);

}
