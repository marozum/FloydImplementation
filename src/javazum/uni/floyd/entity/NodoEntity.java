package javazum.uni.floyd.entity;

public class NodoEntity {
    private Integer id_nodo_mapa;
    private Integer posicion_x;
    private Integer posicion_y;

    public NodoEntity() {
    }

    public NodoEntity(Integer id_nodo_mapa, Integer posicion_x, Integer posicion_y) {
        this.id_nodo_mapa = id_nodo_mapa;
        this.posicion_x = posicion_x;
        this.posicion_y = posicion_y;
    }

    public Integer getId_nodo_mapa() {
        return id_nodo_mapa;
    }

    public void setId_nodo_mapa(Integer id_nodo_mapa) {
        this.id_nodo_mapa = id_nodo_mapa;
    }

    public Integer getPosicion_x() {
        return posicion_x;
    }

    public void setPosicion_x(Integer posicion_x) {
        this.posicion_x = posicion_x;
    }

    public Integer getPosicion_y() {
        return posicion_y;
    }

    public void setPosicion_y(Integer posicion_y) {
        this.posicion_y = posicion_y;
    }

    @Override
    public String toString() {
        return "NodoEntity{" + "id_nodo_mapa=" + id_nodo_mapa + ", posicion_x=" + posicion_x + ", posicion_y=" + posicion_y + '}';
    }
}
