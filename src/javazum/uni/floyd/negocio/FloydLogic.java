
package javazum.uni.floyd.negocio;

import java.util.ArrayList;
import java.util.List;
import javazum.uni.floyd.dao.ConsultaNodosImplement;
import javazum.uni.floyd.entity.NodoEntity;
import javazum.uni.floyd.entity.NodoRelacionEntity;

public class FloydLogic {
    private static List<List<Double>> matrizDistanciaFloyd = new ArrayList<List<Double>>(); //matriz de distancias
    private static List<List<Integer>> matrizNodosFloyd = new ArrayList<List<Integer>>(); //matriz de nodos
    private static List<NodoEntity> nodos;
    private static List<NodoRelacionEntity> nodosRelacion;
    private static Integer totalNodos;
    
    public static void main(String[] arg){
        obtenerDatosBaseDatos();
        calcularMatricesIniciales();
        ejecutarALgoritmoFloyd();
        imprimirMatrices();
        obtenerRutaPesoFloyd(1, 30); //obtenemos la ruta del 1 al 30
        obtenerRutaPesoFloyd(23, 42); //obtenemos la ruta del 23 al 42
        obtenerRutaPesoFloyd(9, 41); //obtenemos la ruta del 9 al 41
        
        System.out.println("\n");
    }
    private static void obtenerDatosBaseDatos(){
        //obtenemos los datos de los nodos
        ConsultaNodosImplement querys = new ConsultaNodosImplement();
        nodos = querys.getNodosMapa();
        nodosRelacion = querys.getRelacionNodosMapa();
        totalNodos = nodos.size();
    }
    private static void calcularMatricesIniciales(){
        //calculamos la matriz inicial de distancias
        for(int i=0; i<totalNodos; i++){
            List<Double> filaDistancia=new ArrayList<Double>();
            for(int j=0; j<totalNodos; j++){
                //si el origen = al destino
                if(i==j){
                    filaDistancia.add(0.0d);
                }else{
                    //buscamos si existe la relacion i,j; de existir obtenemos la distancia
                    Double distancia = -1.0;
                    for(int k=0; k<nodosRelacion.size(); k++){
                        if( nodos.get(i).getId_nodo_mapa()==nodosRelacion.get(k).getId_nodo_origen() &&
                            nodos.get(j).getId_nodo_mapa()==nodosRelacion.get(k).getId_nodo_destino()){
                            distancia = nodosRelacion.get(k).getDistancia();
                        }
                    }
                    filaDistancia.add(distancia);
                }
            }
            matrizDistanciaFloyd.add(filaDistancia);
        }
        //calculamos la matriz inicial de nodos
        for(int i=0; i<totalNodos; i++){
            List<Integer> puntosIntermedios=new ArrayList<Integer>();
            for(int j=0; j<totalNodos; j++){
                puntosIntermedios.add(j);
            }
            matrizNodosFloyd.add(puntosIntermedios);
        }
    }
    private static void ejecutarALgoritmoFloyd(){
        //ahora sí, algoritmo de floyd!!!!!!!!!!!
        for(int k=0; k<totalNodos; k++){
            for(int i=0; i<totalNodos; i++){
                for(int j=0; j<totalNodos; j++){
                    //hacemos las operaciones siempre y cuando exista distancia con el intermediario k: [i,k,j]
                    //es decir,debe existir la distancia d(i,k) y d(k,j)
                    if(matrizDistanciaFloyd.get(i).get(k)>0.0 && matrizDistanciaFloyd.get(k).get(j)>0.0 ){
                        
                        Double distancia=matrizDistanciaFloyd.get(i).get(k)+matrizDistanciaFloyd.get(k).get(j);
                        
                        if(matrizDistanciaFloyd.get(i).get(j)>0.0){
                            if(matrizDistanciaFloyd.get(i).get(j) > distancia){
                                Integer valor= new Integer(k);
                                matrizDistanciaFloyd.get(i).set(j, distancia);
                                matrizNodosFloyd.get(i).set(j, valor);
                            }
                        }else{
                            if(matrizDistanciaFloyd.get(i).get(j)<0.0){
                                Integer valor= new Integer(k);
                                matrizDistanciaFloyd.get(i).set(j, distancia);
                                matrizNodosFloyd.get(i).set(j, valor);
                            }
                        }
                    }
                }
            }
        }
    }
    private static void imprimirMatrices(){
        System.out.println("Matriz de distancias>>>>>>");
        for(List<Double> distancias: matrizDistanciaFloyd){
            String printDist="";
            for(Double distancia: distancias){
                printDist+="\t"+distancia;
            }
            System.out.println(""+printDist);
        }
         
        System.out.println("\n\nMatriz de nodos>>>>>>");
         
        for(List<Integer> intermedios: matrizNodosFloyd){
            String printInter="";
            for(Integer intermedio: intermedios){
                printInter+="\t"+intermedio;
            }
            System.out.println(""+printInter);
        }
    }
    private static void obtenerRutaPesoFloyd(int nodoOrigen, int nodoDestino){
        Integer indexNodoOrigen = 0;
        Integer indexNodoDestino = 0;
        for(int i=0; i<nodos.size(); i++){
            if(nodos.get(i).getId_nodo_mapa()==nodoOrigen){
                indexNodoOrigen = i;
            }
            if(nodos.get(i).getId_nodo_mapa()==nodoDestino){
                indexNodoDestino = i;
            }
        }
        List<Integer> ruta = new ArrayList<Integer>();
        ruta.add(indexNodoOrigen);
        ruta.add(indexNodoDestino);
        obtenerNodosIntermedios(indexNodoOrigen, indexNodoDestino, ruta, 1);
        
        //imprimimos la ruta
        System.out.println("\n");
        String rutaPrint = "";
        for(Integer nodo: ruta){
            rutaPrint+=nodos.get(nodo).getId_nodo_mapa()+",";
        }
        
        //obtenemos el peso de la ruta
        Double peso = matrizDistanciaFloyd.get(ruta.get(0)).get(ruta.get(ruta.size()-1));
        
        System.out.println("Ruta: ["+rutaPrint.substring(0,rutaPrint.length()-1)+"] Peso["+peso+"]");
    }
    private static void obtenerNodosIntermedios(int nodoOrigen, int nodoDestino, List<Integer> ruta, int indice){
        Integer intermedio = matrizNodosFloyd.get(nodoOrigen).get(nodoDestino);
        if(intermedio != nodoDestino){
            ruta.add(indice,intermedio);
            indice++;
            obtenerNodosIntermedios(intermedio,nodoDestino,ruta,indice);
        }else{
            indice--;
            if(indice - 1 == -1){
                return;
            }
            nodoOrigen = ruta.get(indice-1);
            nodoDestino = ruta.get(indice);
            obtenerNodosIntermedios(nodoOrigen,nodoDestino,ruta,indice);
        }
    }
    
}
