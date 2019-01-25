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

}
