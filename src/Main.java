import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Con Expresiones Regulares\n");
        conExpRegulares();

        System.out.println("\n\nSin Expresiones Regulares\n");
        sinExpRegulares();
    }



    static void conExpRegulares() throws IOException {

        File file = new File("santako.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int noel = 0;
        int reno = 0;
        int duende = 0;

        while (true) {
            String linia = bufferedReader.readLine();
            try {
                Pattern pNoel = Pattern.compile( "\\*<]:-DOo");
                Matcher mNoel = pNoel.matcher(linia);
                while (mNoel.find()){
                    noel++;
                }
                Pattern pReno = Pattern.compile( ">:o\\)");
                Matcher mReno = pReno.matcher(linia);
                while (mReno.find()){
                    reno++;
                }
                Pattern pDuende = Pattern.compile( "<]:-D");
                Matcher mDuende = pDuende.matcher(linia);
                while (mDuende.find()){
                    duende++;
                }

                // Los caracteres de duende tienen el mismo patron que una parte de Noel, por lo que Duende también suma cuando suma Noel pero no a la inversa, por eso restamos las veces que aparece Noel con las que ha aparecido Duende.
                duende -= noel;

                if (noel>0){
                    System.out.print("El señor Noel " + "(" + noel + ") ");
                    noel = 0;
                }
                if (reno>0){
                    System.out.print("Reno " + "(" + reno + ") ");
                    reno = 0;
                }
                if (duende>0){
                    System.out.print( "Duendecillo " + "(" + duende + ") ");
                    duende = 0;
                }
                System.out.println();

                } catch (NullPointerException e) {
                    bufferedReader.close();
                    break;
                }
            }
    }

    static void sinExpRegulares() throws IOException {

        // Con este metodo vamos guardando caracteres del mismo tamaño que los caracteres que buscamos, lo metemos en una string y luego los comparamos con lo que buscamos.
        // Ej. Noel es esta string *<]:-DOo tiene 8 chars, entonces recorremos la linea del archivo leido guardando 8 chars a un string para compararlo con Noel

        File file = new File("santako.txt");
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);

        int noel = 0;
        int reno = 0;
        int duende = 0;


        while (true) {
            String linea = bReader.readLine();
            try {
                for (int i = 0; i < linea.length(); i++) {

                    // Strings que contienen el texto para comparar.
                    String compararNoel ="";
                    String compararReno ="";
                    String compararDuende ="";

                    // Si la posicion de la i es menor o igual al tamaño de los caracteres de Noel entonces coge el char en la posición i y en las 7 siguientes para comparar si son iguales
                    if (i+8 <= linea.length()){
                        for (int j = i; j < i+8; j++) {
                            char d = linea.charAt(j);
                            compararNoel += d;
                        }
                    }
                    // Si la posicion de la i es menor o igual al tamaño de los caracteres de Reno entonces coge el char en la posición i y en las 3 siguientes para comparar si son iguales
                    if (i+4 <= linea.length()){
                        for (int j = i; j < i+4; j++) {
                            char d = linea.charAt(j);
                            compararReno += d;
                        }
                    }
                    // Si la posicion de la i es menor o igual al tamaño de los caracteres de Duende entonces coge el char en la posición i y en las 4 siguientes para comparar si son iguales
                    if (i+5 <= linea.length()){
                        for (int j = i; j < i+5; j++) {
                            char d = linea.charAt(j);
                            compararDuende += d;
                        }
                    }
                    if ("*<]:-DOo".equals(compararNoel)){
                        noel++;
                    } else if (">:o)".equals(compararReno)){
                        reno++;
                    } else if ("<]:-D".equals(compararDuende)){
                        duende++;
                    }
                }

                // Los caracteres de duende tienen el mismo patron que una parte de Noel, por lo que Duende también suma cuando suma Noel pero no a la inversa, por eso restamos las veces que aparece Noel con las que ha aparecido Duende.
                duende -= noel;

                if (noel>0){
                    System.out.print("El señor Noel " + "(" + noel + ") ");
                    noel = 0;
                }
                if (reno>0){
                    System.out.print("Reno " + "(" + reno + ") ");
                    reno = 0;
                }
                if (duende>0){
                    System.out.print( "Duendecillo " + "(" + duende + ") ");
                    duende = 0;
                }
                System.out.println();
            } catch (NullPointerException e) {
                bReader.close();
                break;
            }
        }
    }

}
