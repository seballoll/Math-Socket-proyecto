import java.io.Serializable;

/**
 * lista doble para la lista aleatoria
 */
public class Lista_Doble implements Serializable {
        Nodo head = new Nodo();

        Nodo tail = new Nodo();


        Lista_Doble(){
            set_Head(head);
            set_Tail(tail);
            
        }

    /**
     * elige una nueva cabeza para la lista
     * @param newHead nodo con la proxima cabeza
     */
    public void set_Head(Nodo newHead){
            newHead.next = this.head.next;
        
            this.head = newHead;
    }

    /**
     * elige una nueva cola para la lista
     * @param newTail nodo con la proxima cola
     */
        public void set_Tail(Nodo newTail){
            newTail.prev = this.tail.prev;
    
            this.tail = newTail;
    }


}
