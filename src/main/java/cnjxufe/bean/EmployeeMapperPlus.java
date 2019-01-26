package cnjxufe.bean;

/**
 * @ClassName: EmployeeMapperPlus
 * @author: hsw
 * @date: 2019/1/26 11:24
 * @Description: TODO
 */
public interface EmployeeMapperPlus {

    /**
     * 使用自定义结果集映射获取员工！
     * @param id
     * @return
     */
    Employee getEmployeeById(String id);

}
