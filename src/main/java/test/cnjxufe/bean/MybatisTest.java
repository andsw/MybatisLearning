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

}
