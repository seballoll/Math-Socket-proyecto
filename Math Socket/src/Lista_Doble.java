import java.io.Serializable;

/**
 * genera una lista doblemente enlazada
 */
public class Lista_Doble implements Serializable {
        Nodo head = new Nodo();

        Nodo tail = new Nodo();


        Lista_Doble(){
            set_Head(head);
            set_Tail(tail);
            
        }

    /**
     * selecciona la cabeza de la lista
     * @param newHead nodo a seleccionar
     */
    public void set_Head(Nodo newHead){
            newHead.next = this.head.next;
        
            this.head = newHead;
    }

    /**
     * selecciona la cola de la lista
     * @param newTail nodo a seleccionar
     */
    public void set_Tail(Nodo newTail){
            newTail.prev = this.tail.prev;
    
            this.tail = newTail;
    }


}
