
package javazum.uni.floyd.entity;

public class NodoRelacionEntity {
    private Integer id_nodo_origen;
    private Integer id_nodo_destino;
    private Double distancia;

    public NodoRelacionEntity() {
    }

    public NodoRelacionEntity(Integer id_nodo_origen, Integer id_nodo_destino, Double distancia) {
        this.id_nodo_origen = id_nodo_origen;
        this.id_nodo_destino = id_nodo_destino;
        this.distancia = distancia;
    }

    public Integer getId_nodo_origen() {
        return id_nodo_origen;
    }

    public void setId_nodo_origen(Integer id_nodo_origen) {
        this.id_nodo_origen = id_nodo_origen;
    }

    public Integer getId_nodo_destino() {
        return id_nodo_destino;
    }

    public void setId_nodo_destino(Integer id_nodo_destino) {
        this.id_nodo_destino = id_nodo_destino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "NodoRelacionEntity{" + "id_nodo_origen=" + id_nodo_origen + ", id_nodo_destino=" + id_nodo_destino + ", distancia=" + distancia + '}';
    }
}
