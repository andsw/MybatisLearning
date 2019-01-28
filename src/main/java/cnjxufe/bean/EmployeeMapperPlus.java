package cnjxufe.bean;

import java.util.List;

/**
 * @ClassName: EmployeeMapperPlus
 * @author: hsw
 * @date: 2019/1/26 11:24
 * @Description: TODO
 */
public interface EmployeeMapperPlus {

    /**
     * 使用自定义结果集映射获取员工！
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(String id);

    /**
     * 带有级联搜索的示例方法
     *
     * @param id
     * @return
     */
    Employee getEmployeeByIdWithDepartment(String id);

    /**
     * 通过id分步查询员工信息！
     *
     * @param id
     * @return
     */
    Employee getEmployeeByStepAndId(String id);

    /**
     * 带有鉴别器的由部门id获取所有员工信息的示例！
     * @param departmentId
     * @return
     */
    List<Employee> getEmployeesByDepartmentIdWithDiscriminator(Integer departmentId);

}
