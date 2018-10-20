package cnjxufe;

import cnjxufe.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hsw
 * @create 2018-10-20  22:54
 *
 * 总结下第一个mybatis程序的编写步骤：
 * 0. 导入相关依赖 mybatis mysql-connector-java log4j
 * 1. 在数据库中新建一个数据库一张数据表（mybatis.employee）
 * 2. 创建bean（Employee.java属性名与列名一致！）
 * 3. 打开mybatis官方文档，复制mybatis配置文件模板并粘贴到新建的mybatis_config.xml文件中修改数据库连接配置！
 * 4. 新建Main类作为程序执行入口类，psvm！
 * 5. 加入创建sqlSessionFactory的代码。
 * 6. 新建sql语句（select * from tbl_employee where id = #{id}）映射文件
 * 7. 在Main中的SqlSessionFactory获取SqlSession对象，然后按id查询员工并返回员工类对象！
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //1. 从配置文件中获取SqlSessionFactory实例
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2. 从SqlSessionFactory中获取SqlSession实例，能直接执行已经映射的sql语句！
        SqlSession openSqlSession = sqlSessionFactory.openSession();
        //这里使用这个select方法有两个参数，第一个是sql的唯一标识！第二个是执行sql所需要的参数！
        try{
            Employee employee = openSqlSession.selectOne("cnjxufe.bean.EmployeeMapper.selectEmp", "0164559");
            System.out.println(employee);
        }finally {
            openSqlSession.close();

        }
    }

}
