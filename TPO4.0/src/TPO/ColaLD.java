package TPO;

public class ColaLD implements ColaTDA {
    Nodo primero;
    Nodo ultimo;

    public ColaLD() {
    }

    public void inicializarCola() {
        this.primero = null;
        this.ultimo = null;
    }

    public void acolar(Object x) {
        Nodo aux = new Nodo();
        aux.info = x;
        aux.sig = null;
        if (this.ultimo != null) {
            this.ultimo.sig = aux;
        }

        this.ultimo = aux;
        if (this.primero == null) {
            this.primero = this.ultimo;
        }

    }

    public void desacolar() {
        this.primero = this.primero.sig;
        if (this.primero == null) {
            this.ultimo = null;
        }

    }

    public boolean colaVacia() {
        return this.ultimo == null;
    }

    public Object primero() {
        return this.primero.info;
    }
}
