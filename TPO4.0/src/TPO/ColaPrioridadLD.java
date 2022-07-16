package TPO;


public class ColaPrioridadLD{
    ColaPrioridadLD.NodoPrioridad primero;

    public ColaPrioridadLD() {
    }

    public void inicializarCola() {
        this.primero = null;
    }

    public void acolarPrioridad(Cliente x, int prioridad) {
        ColaPrioridadLD.NodoPrioridad nuevo = new ColaPrioridadLD.NodoPrioridad();
        nuevo.prioridad = prioridad;
        nuevo.info = x;
        if (!this.colaVacia() && prioridad >= this.primero.prioridad) {
            ColaPrioridadLD.NodoPrioridad aux;
            for(aux = this.primero; aux.sig != null && aux.sig.prioridad <= prioridad; aux = aux.sig) {
            }

            nuevo.sig = aux.sig;
            aux.sig = nuevo;
        } else {
            nuevo.sig = this.primero;
            this.primero = nuevo;
        }

    }

    public void desacolar() {
        this.primero = this.primero.sig;
    }

    public boolean colaVacia() {
        return this.primero == null;
    }

    public Cliente getPrimero() {
        return this.primero.info;
    }

    public int prioridad() {
        return this.primero.prioridad;
    }

    private class NodoPrioridad {
        Cliente info;
        int prioridad;
        ColaPrioridadLD.NodoPrioridad sig;

        private NodoPrioridad() {
        }
    }
}
