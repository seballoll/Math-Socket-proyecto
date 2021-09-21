import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class random_list implements Serializable{
            Lista_Doble lista = null;

            public static Lista_Doble setLista(){

                    Lista_Doble lista = new Lista_Doble();
                    int Reto = 7;
                    int Tunel = 4;
                    int Trampa = 3;
                    int currentReto = 0;
                    int currentTunel = 0;
                    int currentTrampa = 0;
                    

                    for (int i = 0; i < 16; i++){
                        int randomNum = ThreadLocalRandom.current().nextInt(0,11);
                        Nodo newNodo = new Nodo();        
                        if(i == 0){
                                newNodo.tipo = "Inicio";
                                lista.set_Head(newNodo);
                                lista.set_Tail(newNodo);
                        }else if(i == 15){
                                newNodo.tipo = "Final";
                         
                        }else if (randomNum % 2 == 0 && currentReto < Reto){
                                newNodo.tipo = "Reto";         
                                currentReto++;                       
                        
                        }else if((randomNum >5 && currentTrampa < Trampa) || currentTunel >= Tunel){
                                newNodo.tipo = "Trampa";
                                currentTrampa++;
                               
                        }else{
                                newNodo.tipo = "Tunel";
                                currentTunel++;

                        }
                        newNodo.prev = lista.tail;
                        lista.tail.next = newNodo;
                        lista.set_Tail(newNodo);                               
                        }        

                    return lista;
            }   
            

            public static void printList(Lista_Doble list){

                Nodo current = list.head;
                int i = 1;
                while(current != null){
                        System.out.println( i + " : " +current.tipo);
                        current = current.next;
                        i++;
                }


            }
            
}
