import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Procesos {
    HashMap<String, ArrayList<String>> paises;
    String nombrePais;
    String continuar;

    public Procesos() {
        paises = new HashMap<String, ArrayList<String>>();
        iniciar();
    }

    private void iniciar() {
        String menu = "1. Registrar país\n2. Registrar ciudades\n3. Consultar ciudades\n4. Consultar ciudad\n5. Salir";
        int opc;
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Menú", JOptionPane.PLAIN_MESSAGE));
            seleccionar(opc);
        } while (opc != 5);
    }

    private void seleccionar(int opc) {
        switch (opc) {
            case 1:
                registrarPais();
                break;
            case 2:
                registrarCiudades();
                break;
            case 3:
                consultarCiudades();
                break;
            case 4:
                buscarCiudad();
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Adiós", "Salir", JOptionPane.PLAIN_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void buscarCiudad() {
        nombrePais = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ciudad a buscar", "Buscar Ciudad",
                JOptionPane.PLAIN_MESSAGE);

        if (nombrePais == null || nombrePais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de ciudad", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean ciudadEncontrada = false;

        for (String pais : paises.keySet()) {
            ArrayList<String> ciudades = paises.get(pais);
            if (ciudades.contains(nombrePais)) {
                JOptionPane.showMessageDialog(null, "La ciudad " + nombrePais + " se encuentra en " + pais,
                        "Ciudad Encontrada", JOptionPane.PLAIN_MESSAGE);
                ciudadEncontrada = true;
                break;
            }
        }

        if (!ciudadEncontrada) {
            JOptionPane.showMessageDialog(null, "No se encontró la ciudad en ningún país", "Ciudad no Encontrada",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void consultarCiudades() {
        nombrePais = JOptionPane.showInputDialog(null, "Ingrese el nombre del país para consultar sus ciudades",
                "Consultar Ciudades", JOptionPane.PLAIN_MESSAGE);

        if (nombrePais == null || nombrePais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de país", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!paises.containsKey(nombrePais)) {
            JOptionPane.showMessageDialog(null, "No hay países con este nombre", "País no Encontrado",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            ArrayList<String> ciudades = paises.get(nombrePais);
            if (ciudades.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay ciudades registradas para este país", "Sin Ciudades",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                String listaCiudades = String.join(", ", ciudades);
                JOptionPane.showMessageDialog(null, "Las ciudades de " + nombrePais + " son: " + listaCiudades,
                        "Ciudades Registradas", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void registrarCiudades() {
        nombrePais = JOptionPane.showInputDialog(null, "Ingrese el nombre del país para registrar ciudades",
                "Registrar Ciudades", JOptionPane.PLAIN_MESSAGE);

        if (nombrePais == null || nombrePais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de país", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!paises.containsKey(nombrePais)) {
            JOptionPane.showMessageDialog(null, "Registrando país...", "Registrar País", JOptionPane.PLAIN_MESSAGE);
            paises.put(nombrePais, new ArrayList<String>());
        }

        int cantidadCiudades = 0;
        try {
            cantidadCiudades = Integer.parseInt(
                    JOptionPane.showInputDialog(null, "Ingrese la cantidad de ciudades a registrar", "Cantidad de Ciudades",
                            JOptionPane.PLAIN_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad de ciudades inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < cantidadCiudades; i++) {
            String ciudad = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ciudad para " + nombrePais,
                    "Registrar Ciudad", JOptionPane.PLAIN_MESSAGE);
            if (ciudad == null || ciudad.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de ciudad", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            paises.get(nombrePais).add(ciudad);
        }
    }

    private void registrarPais() {
        nombrePais = JOptionPane.showInputDialog(null, "Ingrese el nombre del país", "Registrar País",
                JOptionPane.PLAIN_MESSAGE);

        if (nombrePais == null || nombrePais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de país", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (paises.containsKey(nombrePais)) {
            JOptionPane.showMessageDialog(null, "El país ya está registrado", "Registro Duplicado",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Registrando país...", "Registrar País", JOptionPane.PLAIN_MESSAGE);
            paises.put(nombrePais, new ArrayList<String>());
        }
    }
}
