package test.cnjxufe.bean;

import cnjxufe.bean.Employee;
import cnjxufe.bean.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.IOException;
import java.io.InputStream;

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
     */
    @Test
    public void test0() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //增加员工
        Employee employee = new Employee("000000",
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

}
