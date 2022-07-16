package TPO;

public class AdiminstradordeColas {
    private ColaPrioridadLD puesto1;
    private ColaPrioridadLD puesto2;
    private ColaPrioridadLD puesto3;
    private ColaPrioridadLD puesto4;
    private ColaPrioridadLD puesto5;
    private ColaPrioridadLD puesto6;

    private int[] espera_tot = new int[6];
    private int[] acolados = new int[6];

    private int ticket;

    public AdiminstradordeColas(){

    }
    public void inicializar(){
        puesto1 = new ColaPrioridadLD();
        puesto2 = new ColaPrioridadLD();
        puesto3 = new ColaPrioridadLD();
        puesto4 = new ColaPrioridadLD();
        puesto5 = new ColaPrioridadLD();
        puesto6 = new ColaPrioridadLD();

        puesto1.inicializarCola();
        puesto2.inicializarCola();
        puesto3.inicializarCola();
        puesto4.inicializarCola();
        puesto5.inicializarCola();
        puesto6.inicializarCola();

        for(int i = 0; i < 6; i++){
            espera_tot[i] = 0;
            acolados[i] = 0;
        }

        ticket = 0;
    }
    public boolean ingresarClientes(){
        ticket += 1;
        System.out.println("-------------- Cliente N° "+ ticket +"--------------");
        Cliente cliente = new Cliente();
        cliente.definir(ticket);
        if (cliente.getTiempo_atencion() < 0){
            return false;
        }
        else{
            switch (cliente.getType()){
                case "O":
                    acolarGeneric(cliente, puesto1, 0);
                    break;
                case "P":
                    acolarGeneric(cliente, puesto2, 1);
                    break;
                case "PAMI":
                    acolarGeneric(cliente, puesto3, 2);
                    break;
            }
        }
        return true;
    }
    private void acolarGeneric(Cliente c, ColaPrioridadLD cola, int indice){
        if (acolados[indice] >= 7){
            if (acolados[3] >= 7){
                if(acolados[4] >= 7){
                    puesto6.acolarPrioridad(c, c.getTiempo_atencion());
                    espera_tot[5] += c.getTiempo_atencion();
                    acolados[5] += 1;
                }
                else{
                    puesto5.acolarPrioridad(c, c.getTiempo_atencion());
                    espera_tot[4] += c.getTiempo_atencion();
                    acolados[4] += 1;
                }
            }
            else{
                puesto4.acolarPrioridad(c, c.getTiempo_atencion());
                espera_tot[3] += c.getTiempo_atencion();
                acolados[3] += 1;
            }
        }
        else{
            cola.acolarPrioridad(c, c.getTiempo_atencion());
            espera_tot[indice] += c.getTiempo_atencion();
            acolados[indice] += 1;
        }
    }
    public void mostrarInforme(){

        mostrarCola(puesto1, "P1", 1);
        mostrarCola(puesto2, "P2", 2);
        mostrarCola(puesto3, "P3", 3);
        mostrarCola(puesto4, "P4", 4);
        mostrarCola(puesto5, "P5", 5);
        mostrarCola(puesto6, "P6", 6);
    }
    private void mostrarCola(ColaPrioridadLD c, String puesto, int indice){
        int tiempo_calculado = 0;
        String c_ticket;
        int c_tiempoatt;
        int ta_horas;
        int ta_min;
        int tc_horas;
        int tc_min;

        System.out.printf("%5s | %3s | %5s | %5s ", "Ticket", "Puesto", "tiempo atencion", "tiempo calculado");
        System.out.println();

        while (!c.colaVacia()) {
            Cliente a = c.getPrimero();
            c_ticket = a.getInd_ticket();
            c_tiempoatt = a.getTiempo_atencion();
            ta_horas = convHoras(c_tiempoatt);
            ta_min = convMin(c_tiempoatt);
            tiempo_calculado += c_tiempoatt;
            tc_horas = convHoras(tiempo_calculado);
            tc_min = convMin(tiempo_calculado);

            //System.out.printf("%6s | %6s | %15s | %8s", c_ticket, puesto, c_tiempoatt, tiempo_calculado);
            //System.out.println();
            System.out.printf("%6s | %6s | %12d:%02d | %6d:%02d", c_ticket, puesto, ta_horas, ta_min,tc_horas,tc_min);
            System.out.println();
            c.desacolar();
        }
        int espera = espera_tot[indice - 1];
        int e_horas = convHoras(espera);
        int e_minutos = convMin(espera);

        System.out.printf("Total del puesto %d es de %02d:%02d ", indice, e_horas, e_minutos);
        System.out.println();
        System.out.println();
    }
    private int convMin(int tiempo){
        return tiempo%60;
    }
    private int convHoras(int tiempo){
        return tiempo/60;
    }
}
