package test.cnjxufe.bean;

import cnjxufe.bean.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author PC
 */
public class MybatisTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    private SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            System.out.println("文件mybatis-config.xml读取失败！");
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    /**
    * Method: getId()
    * 接口式编程时比较受欢迎的
    */
    @Test
    public void mybatis() {
        SqlSessionFactory factory = getSqlSessionFactory();
        try (SqlSession sqlSession = factory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeById("0164559");
            System.out.println(employee);
        }
    }

    /**
     * 测试其他sql操作（增删改）
     * 这里的增删改操纵需要手动commit。
     *（比如删除的地方出现错误，没有commit，那么insert也就不会生效！）
     * 但factory.openSession(true);可以自动提交！
     */
    @Test
    public void test0() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //增加员工
        Employee employee = new Employee("000001",
                "heshao", 'M', "985934131@qq.com");
        int insertNum = mapper.insertEmployee(employee);
        System.out.println("插入记录成功条数 = "  + insertNum);
        //删除
        boolean deleteSuccessfully = mapper.deleteEmployeeById("0164558");
        System.out.println("删除成功？  " + deleteSuccessfully);
        //修改
        employee.setEmail("2127804711@qq.com");
        long updateNum = mapper.updateEmployee(employee);
        System.out.println("修改记录成功条数：" + updateNum);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试传递多参数到映射文件的情况
     */
    @Test
    public void test1() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeByLastNameAndGender("bat", "M");
        System.out.println(employee);
        sqlSession.close();
    }

    /**
     * 测试返回的是集合对象的查询
     */
    @Test
    public void test2() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.getEmployeeByGender("M");
        employees.forEach(System.out::println);

        //返回一个员工的map
        System.out.println("\ngetEmployeeReturnMap:   ");
        Map<String, Object> singleEmployeeMap = mapper.getEmployeeReturnMap("0164551");
        for (Map.Entry<String, Object> entry : singleEmployeeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //返回多个员工的信息
        System.out.println("\ngetEmployeesReturnMap:  ");
        Map<String, Employee> employeesMap = mapper.getEmployeesReturnMap("M");
        for (Map.Entry<String, Employee> entry : employeesMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        sqlSession.close();
    }

    /**
     * 即使去掉驼峰命名设置也能成功!
     */
    @Test
    public void test3() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
        Employee employee = mapper.getEmployeeById("0164559");
        System.out.println(employee);
    }

    /**
     * 级联查询
     */
    @Test
    public void test4() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
        Employee employee = mapper.getEmployeeByIdWithDepartment("000000");
        System.out.println(employee);

        //分步查询
        Employee employee1 = mapper.getEmployeeByStepAndId("000000");
        System.out.println(employee1.getEmail());
        System.out.println(employee1.getDepartment());
        sqlSession.close();
    }

    /**
     * 自定义collection结果集自定义关联集合封装规则！
     *
     * 根据id获取部门的同时也获取所有在此部门的员工
     */
    @Test
    public void test5() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = mapper.getDepartmentByIdWithAllEmployees(1);
        System.out.println(department);

        /* 自定义关联集合结果集加上分步查询即只需要部门信息，不包含所有的员工信息即只查询部门表！

        */
        Department department1 = mapper.getDepartmentByIdAndStep(4);
        System.out.println(department1.getDepartmentName());
        System.out.println(department1.getEmployees());
        sqlSession.close();
    }

    /**
     * 测试discriminator
     * 规则：
     *     如果查出是女生，则把部门信息查询出来，否则不查询。
     *     如果为男生，则将last_name的值赋给email。
     */
    @Test
    public void test6() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapperPlus mapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
        List<Employee> employees = mapperPlus.getEmployeesByDepartmentIdWithDiscriminator(4);
        System.out.println(employees);
        sqlSession.close();
    }
}
