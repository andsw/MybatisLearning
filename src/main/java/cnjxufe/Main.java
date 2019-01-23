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
        /**
         * 注意：Class.getResourceAsStream()和ClassLoader.getResourceAsStream()的区别！
         * Class.getResourceAsStream() 会指定要加载的资源路径与当前类所在包的路径一致。
         *
         *      例如你写了一个MyTest类在包com.test.mycode 下，那么MyTest.class.getResourceAsStream("name") 会在com.test.mycode包下查找相应的资源。
         *
         *      如果这个name是以 '/' 开头的，那么就会从classpath的根路径下开始查找。
         *
         *
         *  ClassLoader.getResourceAsStream()  无论要查找的资源前面是否带'/' 都会从classpath的根路径下查找。
         *
         *     所以: MyTest.getClassLoader().getResourceAsStream("name") 和
         *
         *             MyTest.getClassLoader().getResourceAsStream("name") 的效果是一样的。
         *
         *
         *  这里的Resources类对应的包路径应该就是左侧的resources文件夹！所以对于配置文件，提供个文件名就够了！
         */
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2. 从SqlSessionFactory中获取SqlSession实例，能直接执行已经映射的sql语句！
        //这里使用这个select方法有两个参数，第一个是sql的唯一标识！第二个是执行sql所需要的参数！
        try (SqlSession openSqlSession = sqlSessionFactory.openSession()) {
            Employee employee = openSqlSession.selectOne("cnjxufe.bean.EmployeeMapper.selectEmp", "0164559");
            System.out.println(employee);
        }

        /**
         * 其他些知识：
         *
         * 二 类加载目录的获得(即当运行时某一类时获得其装载目录)
         *       1.1)通用的方法一(不论是一般的java项目还是web项目,先定位到能看到包路径的第一级目录)
         *
         *         InputStream is=TestAction.class.getClassLoader().getResourceAsStream("test.txt");
         *                   (test.txt文件的路径为 项目名\src\test.txt;类TestAction所在包的第一级目录位于src目录下)
         *
         *         上式中将TestAction，test.txt替换成对应成相应的类名和文件名字即可
         *
         *         1.2)通用方法二 (此方法和1.1中的方法类似,不同的是此方法必须以'/'开头)
         *              InputStream is=Test1.class.getResourceAsStream("/test.txt");
         *                       (test.txt文件的路径为 项目名\src\test.txt,类Test1所在包的第一级目录位于src目录下)
         *
         *
         *
         * 三 web项目根目录的获得(发布之后)
         *     1 从servlet出发
         *
         *     可建立一个servlet在其的init方法中写入如下语句
         *       ServletContext s1=this.getServletContext();
         *    String temp=s1.getRealPath("/"); (关键)
         *     结果形如：D:\工具\Tomcat-6.0\webapps\002_ext\ (002_ext为项目名字)
         *
         *                      如果是调用了s1.getRealPath("")则输出D:\工具\Tomcat-6.0\webapps\002_ext(少了一个"\")
         *
         *    2 从httpServletRequest出发
         *
         *              String cp11111=request.getSession().getServletContext().getRealPath("/");
         *
         *       结果形如:D:\工具\Tomcat-6.0\webapps\002_ext\
         *
         *
         * 五   属性文件的读取:
         *
         *      方法 一
         *
         *            InputStream in = lnew BufferedInputStream( new FileInputStream(name));
         *
         *            Properties p = new Properties();
         *
         *            p.load(in);
         *
         *
         *     注意路径的问题,做执行之后就可以调用p.getProperty("name")得到对应属性的值
         *
         *
         * 方法二
         *
         *             Locale locale = Locale.getDefault();
         *             ResourceBundle localResource = ResourceBundle.getBundle("test/propertiesTest", locale);
         *             String value = localResource.getString("test");
         *             System.out.println("ResourceBundle: " + value);
         *
         *    工程src目录下propertiesTest.properties(名字后缀必须为properties)文件内容如下:
         *
         *                test=hello word
         */
    }

}
