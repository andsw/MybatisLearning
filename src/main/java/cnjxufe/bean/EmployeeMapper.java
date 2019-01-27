package cnjxufe.bean;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据姓和性别查找员工
     * @param lastName
     * @param gender
     * @return
     */
    Employee getEmployeeByLastNameAndGender(@Param("lastName") String lastName,
                                            @Param("gender") String gender);

    /**
     * 根据性别返回员工集合！
     * @param gender
     * @return
     */
    List<Employee> getEmployeeByGender(String gender);

    /**
     * 返回map的形式表示一个员工，map的键就是列名
     * @param id
     * @return
     */
    Map<String, Object> getEmployeeReturnMap(String id);

    /**
     * 以map的形式返回所有员工信息
     * 用@MapKey 设置map的键为员工类的哪个属性！
     * @param gender
     * @return
     */
    @MapKey("lastName")
    Map<String, Employee> getEmployeesReturnMap(String gender);

    /**
     * 获取在此号码部门里的所有员工信息的链表！
     * @param departmentId
     * @return
     */
    List<Employee> getEmployeesByDepartmentId(Integer departmentId);
}
