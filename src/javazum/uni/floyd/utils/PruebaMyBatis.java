package javazum.uni.floyd.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PruebaMyBatis {
    public static void main(String[] args){
        SqlSessionFactory sf = MyBatisSession.getSqlSessionFactory();
        SqlSession session = sf.openSession();
        try{
            System.out.println("count:"+session.selectList("nodoConsultas.getAllNodosMapa").size());
            System.out.println("count:"+session.selectList("nodoConsultas.getAllNodosRelacionMapa").size());
        }finally{
            session.close();
        }
    }
}
