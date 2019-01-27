package cnjxufe.bean;

import java.util.List;

/**
 * @ClassName: DepartmentMapper
 * @author: hsw
 * @date: 2019/1/27 10:53
 * @Description: department的映射类
 */
public interface DepartmentMapper {
    /**
     * 暂时还没有list属性的查询
     * @return
     */
    Department getDepartmentById();

    /**
     * 根据id获取部门的同时也获取所有在此部门的员工。
     * @param departmentId
     * @return
     */
    Department getDepartmentByIdWithAllEmployees(Integer departmentId);

    /**
     * 分布查询，需要员工信息再加载！
     * @param departmentId
     * @return
     */
    Department getDepartmentByIdAndStep(Integer departmentId);
}
