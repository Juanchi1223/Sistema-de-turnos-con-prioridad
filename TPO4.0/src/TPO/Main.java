package TPO;

public class Main {

    public static void main(String[] args) {
        AdiminstradordeColas a = new AdiminstradordeColas();

        a.inicializar();

        for (int i = 0; i < 40; i++)
            if(!a.ingresarClientes())
                break;

        a.mostrarInforme();
    }
}
