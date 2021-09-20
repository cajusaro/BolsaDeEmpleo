/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import java.util.ArrayList;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o más con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList<Aspirante> aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo() {
        aspirantes = new ArrayList<>();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public ArrayList<Aspirante> darAspirantes() {
        ArrayList<Aspirante> copia = new ArrayList<>(aspirantes);
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     *
     * @param nombreA           El nombre del aspirante - nombreA != null
     * @param profesionA        La profesión del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA Años de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El teléfono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retornó true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        int aspiranteBuscado = buscarAspirante(nombreA);
        boolean agregado = false;
        if (aspiranteBuscado == -1) {
            Aspirante nuevoAspirante = new Aspirante(nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.add(nuevoAspirante);
            agregado = true;
        }
        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes está ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        // TODO: Realizar el ejercicio correspondiente
        int size = aspirantes.size();

        for (int i = 0; i < size - 1; i++) {
            // Se genera el ciclo de la burbuja
            boolean swapped = true;
            for (int j = 0; j < size - i - 1; j++) {

                Aspirante aspAnt = aspirantes.get(j);
                Aspirante aspSig = aspirantes.get(j + 1);
                // Para ordenar en orden descendiente cambiar > por <
                if (aspAnt.darNombre().compareTo(aspSig.darNombre()) > 0) {

                    // Intercambio
                    aspirantes.set(j,aspSig);
                    aspirantes.set(j + 1, aspAnt);
                    swapped = false;
                }
            }
            // Si no ha habido intercambio en la última comparación, entonces el array está ya ordenado.
            if (swapped == true)
                break;
        }
    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selección <br>
     * <b>post: </b>La lista de aspirantes está ordenada por edad
     */
    public void ordenarPorEdad() {
        // TODO: Realizar el ejercicio correspondiente
        int n = aspirantes.size();

        // Los límites entre el array ordenado y desordenado se va desplazando a la derecha (el algoritmo ordenado va creciendo en cada iteración)
        for (int i = 0; i < n-1; i++)
        {
            // Encontrar el mínimo elemento del array que todavía no está ordenado
            int min_idx = i;
            Aspirante aspi = aspirantes.get(i);
            Aspirante aspMin = aspirantes.get(min_idx);

            for (int j = i+1; j < n; j++) {
                Aspirante aspj = aspirantes.get(j);
                if (aspj.darEdad() < aspMin.darEdad()) {
                    min_idx = j;
                    aspMin = aspj;
                }
            }
            // Intercambiar el elemento con valor mínimo por la primera posición del array que todavia no está ordenado
            aspirantes.set(min_idx, aspi);
            aspirantes.set(i, aspMin);
        }
    }

    /**
     * Organiza la lista de aspirantes por profesión usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesión
     */
    public void ordenarPorProfesion() {
        // TODO: Realizar el ejercicio correspondiente
        int size = aspirantes.size();

        for (int i = 0; i < size - 1; i++) {
            // Se genera el ciclo para la burbuja
            boolean swapped = true;
            for (int j = 0; j < size - i - 1; j++) {

                Aspirante aspAnt = aspirantes.get(j);
                Aspirante aspSig = aspirantes.get(j + 1);
                // Para ordenar en orden descendiente cambiar > por <
                if (aspAnt.darProfesion().compareTo(aspSig.darProfesion()) > 0) {

                    // Intercambio
                    aspirantes.set(j,aspSig);
                    aspirantes.set(j + 1, aspAnt);

                    swapped = false;
                }
            }
            // Si no ha habido intercambio en la última comparación, entonces el array está ya ordenado.
            if (swapped == true)
                break;
        }

    }

    /**
     * Organiza la lista de aspirantes por años de experiencia usando el algoritmo de inserción <br>
     * <b>post: </b>La lista de aspirantes está ordenada por años de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        // TODO: Realizar el ejercicio correspondiente

        int n = aspirantes.size();

        for (int i = 1; i < n; ++i) {
            Aspirante aspKey = aspirantes.get(i);
            int j = i - 1;

            /* Mover los elementos que son mayores que el que estamos comparando a una posición más a la derecha de su actual posición */
            while (j >= 0 && aspirantes.get(j).darAniosExperiencia() > aspKey.darAniosExperiencia()) {
                aspirantes.set(j + 1, aspirantes.get(j));
                j = j - 1;
            }
            aspirantes.set(j+1, aspKey);
        }
    }

    /**
     * Busca un Aspirante según su nombre y retorna la posición en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retornó la posición donde se encuentra un aspirante con el nombre dado. Si no se encuentra ningún aspirante con ese nombre se retornó -1.
     */
    public int buscarAspirante(String nombre) {
        int posicion = -1;

        // TODO: Realizar el ejercicio correspondiente
        ordenarPorNombre();
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darNombre().equalsIgnoreCase(nombre)) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Busca un aspirante utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retornó la posición del aspirante con el nombre dado. Si el aspirante no existe se retornó -1.
     */
    public int buscarBinarioPorNombre(String nombre) {
    //public int buscarAspirante(String nombre) {
        int posicion = -1;
        int pos = -1;
        int ini = 0;
        int fin = aspirantes.size() - 1;

        // TODO: Realizar el ejercicio correspondiente
        ordenarPorNombre();

        while (ini <= fin) {
            pos = getMidValue(ini, fin);
            if(aspirantes.get(pos).darNombre().equalsIgnoreCase(nombre)){
                posicion = pos;
                break;
            } else if (aspirantes.get(pos).darNombre().compareToIgnoreCase(nombre) < 0) {
                ini = pos + 1;
            } else fin = pos - 1;
        }
        return posicion;
    }

    public static int getMidValue(int minLimit, int maxLimit) {
        return (maxLimit + minLimit) / 2;
    }

    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante más joven. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMasJoven() {
        int posicion = -1;
       int masJoven = -1;

        // TODO: Realizar el ejercicio correspondiente
        if (aspirantes.size() > 0) {
            masJoven = aspirantes.get(0).darEdad();
            posicion = 0;
        }
        for (int i = 1; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darEdad() < masJoven) {
                masJoven = aspirantes.get(i).darEdad();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante con más edad. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorEdad() {
        int posicion = -1;
        int masEdad = -1;

        // TODO: Realizar el ejercicio correspondiente
        if (aspirantes.size() > 0) {
            masEdad = aspirantes.get(0).darEdad();
            posicion = 0;
        }
        for (int i = 1; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darEdad() > masEdad) {
                masEdad = aspirantes.get(i).darEdad();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Busca el aspirante con más años de experiencia en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorExperiencia() {
        int posicion = -1;
        int masEperiencia = -1;
        // TODO: Realizar el ejercicio correspondiente

        if (aspirantes.size() > 0) {
            masEperiencia = aspirantes.get(0).darAniosExperiencia();
            posicion = 0;
        }
        for (int i = 1; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darAniosExperiencia() > masEperiencia) {
                masEperiencia = aspirantes.get(i).darAniosExperiencia();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se eliminó el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retornó true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) {
        boolean contratado = false;

        // TODO: Realizar el ejercicio correspondiente
        ordenarPorNombre();
        int aspEncontrado = buscarBinarioPorNombre(nombre);
        if (aspEncontrado >= 0){
            aspirantes.remove(aspEncontrado);
            contratado = true;
        }
        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos años de experiencia <br>
     * son menores a la cantidad de años especificada <br>
     *
     * @param aniosExperiencia La cantidad de años con relación a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) {
        int eliminados = 0;

        // TODO: Realizar el ejercicio correspondiente

        for (int i = aspirantes.size()-1; i >= 0; i--) {
            if (aspirantes.get(i).darAniosExperiencia() < aniosExperiencia) {
                aspirantes.remove(i);
                eliminados++;
            }
        }
        return eliminados;
    }
}