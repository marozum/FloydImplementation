package javazum.uni.floyd.dao;

import java.util.List;
import javazum.uni.floyd.entity.NodoEntity;
import javazum.uni.floyd.entity.NodoRelacionEntity;

public interface ConsultaNodosInterfaz {
    public List<NodoEntity> getNodosMapa();
    public List<NodoRelacionEntity> getRelacionNodosMapa();
}
