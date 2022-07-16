package TPO;
import java.util.Locale;
import java.util.Scanner;

public class Cliente {
    private String type;
    private int tiempo_atencion;
    private String ind_ticket;

    public Cliente(){
    }

    public void definir(int t){
        type = nomenclatura();
        ind_ticket = type + Integer.toString(t);
        if (type.equals("PAMI")){
            System.out.println("Cuantas recetas tiene: (Solo numeros!!)");
            int cant = input();
            while (cant <= 0) {
                System.out.println("ERROR: numero no valido");
                System.out.println("Cuantas recetas tiene");
                cant = input();
            }
            switch (cant){
                case 1:
                    tiempo_atencion = 10;
                    break;
                case 2, 3:
                    tiempo_atencion = 30;
                    break;
                default:
                    tiempo_atencion = 50;
                    break;
            }
        }
        else{
            switch (type){
                case "O":
                    tiempo_atencion = 30;
                    break;
                case "P":
                    tiempo_atencion = 20;
                    break;
                case "OUT":
                    tiempo_atencion = -1;
                    break;
            }
        }
    }
    private String nomenclatura() {          // Funcion  que determina el tipo de cliente (por nomenclatura)
        Scanner input = new Scanner(System.in);

        System.out.println("Que tipo de servicio se solicita");
        System.out.println("Ingrese 'O' para Obra Social");
        System.out.println("Ingrese 'P' para Obra Social");
        System.out.println("Ingrese 'PAMI' para Obra Social");
        System.out.println("'OUT' para salir ");
        String serv = input.nextLine();
        serv = serv.toUpperCase(Locale.ROOT);
        while (verificar(serv)){
            System.out.println("ERROR: Lo ingresado no es ninguna de las opciones");
            System.out.println("Que tipo de servicio se solicita");
            System.out.println("Ingrese 'O' para Obra Social");
            System.out.println("Ingrese 'P' para Obra Social");
            System.out.println("Ingrese 'PAMI' para Obra Social");
            System.out.println("'OUT' para salir ");
            serv = input.nextLine();
            serv = serv.toUpperCase(Locale.ROOT);
        }
        return serv;
    }
    private boolean verificar(String a){    // Verifica que lo ingresado sea coerente alos tipos existentes
        switch (a){
            case "O":
            case "PAMI":
            case "P":
            case "OUT":
                return false;
            default:
                return true;
        }
    }
    private int input(){ // verifica que no se me ta un string y no se rompa el codigo
        Scanner a = new Scanner(System.in);

        while(!a.hasNextInt()){
            a.next();
            System.out.println("Error: ingrese un numero entero ;)");
        }
        return a.nextInt();
    }
    public String getType(){
        return type;
    }
    public int getTiempo_atencion(){
        return tiempo_atencion;
    }
    public String getInd_ticket(){
        return ind_ticket;
    }
}
