package javazum.uni.floyd.utils;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSession {
    
    private static SqlSessionFactory SQL_SESSION_FACTORY;

    static Reader reader = null;
    
    static{
        try{
            reader = Resources.getResourceAsReader("javazum/uni/floyd/xml/MyBatis-Config.xml");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static SqlSessionFactory getSqlSessionFactory(){
        
        if (SQL_SESSION_FACTORY==null ){
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(reader);
        }
        
        return SQL_SESSION_FACTORY;
    }
    
}
