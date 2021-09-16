public class Lista_Doble {
        Nodo head = new Nodo();

        Nodo tail = new Nodo();


        Lista_Doble(){
            set_Head(head);
            set_Tail(tail);
            
        }

        public void set_Head(Nodo newHead){
            newHead.next = this.head.next;
        
            this.head = newHead;
    }

    
        public void set_Tail(Nodo newTail){
            newTail.prev = this.tail.prev;
    
            this.tail = newTail;
    }


}
