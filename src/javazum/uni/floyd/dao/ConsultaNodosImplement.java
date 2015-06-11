package javazum.uni.floyd.dao;

import java.util.List;
import javazum.uni.floyd.entity.NodoEntity;
import javazum.uni.floyd.entity.NodoRelacionEntity;
import javazum.uni.floyd.utils.MyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ConsultaNodosImplement implements ConsultaNodosInterfaz{
    SqlSessionFactory sf = MyBatisSession.getSqlSessionFactory();
    
    @Override
    public List<NodoEntity> getNodosMapa() {
        SqlSession session = sf.openSession();
        try{
            return session.selectList("nodoConsultas.getAllNodosMapa");
        }finally{
            session.close();
        }
    }

    @Override
    public List<NodoRelacionEntity> getRelacionNodosMapa() {
        SqlSession session = sf.openSession();
        try{
            return session.selectList("nodoConsultas.getAllNodosRelacionMapa");
        }finally{
            session.close();
        }
    }    
}
