package TrabalhosPraticos;

import java.util.Locale;
import java.util.Scanner;

public class WineSalesManagementV2 {
     static int numero_vendas;
    static int maior;
    static String maior_vendedor;

    static String[] vendedores = new String[]{"Artur", "Berto", "Carlos"};
    static String[] tipo_de_vinho = new String[]{"tinto", "branco"};

    public static void main(String[] args) {

        Locale.setDefault(new Locale(".", ","));
        try (Scanner ler = new Scanner(System.in)) {
            int garrafas;
            double valor_venda;
            String vinho;
            String nome;

            //inicializar as variáveis
            int num = 0;
            int quantidade_total = 0;
            int quantidade_branco = 0;
            int quantidade_tinto = 0;
            double venda_total = 0;

            System.out.println("Quantas vendas foram realizadas?");

            numero_vendas = ler.nextInt();

            double[][] dados_vendas = new double[numero_vendas][4];

            while (num < numero_vendas) {

                System.out.println("Quem realizou a venda?");

                nome = ler.next();

                System.out.println("Que tipo de vinho vendeu?");

                vinho = ler.next();

                System.out.println("Quantas garrafas vendeu?");

                garrafas = ler.nextInt();

                System.out.println("Qual foi o valor da venda?");

                valor_venda = ler.nextDouble();

                dados_vendas[num][0] = codigoVendedor(nome); //inserir valores na primeira coluna 

                dados_vendas[num][1] = codigoVinho(vinho); //inserir valores na segunda coluna  

                dados_vendas[num][2] = garrafas; //inserir valores na terceira coluna

                dados_vendas[num][3] = valor_venda; //inserir valores na quarta coluna 
                num++;
            }

            int t = calcularTotal(quantidade_total, dados_vendas);
            String s = maiorVendedorTinto(dados_vendas);
            double k = vendaMaiorValor(dados_vendas);
            double m = calcularTotalVenda(venda_total, dados_vendas);

            //resultado final 
            System.out.println("------------------------------------------------------------------------");
            System.out.println("A quantidade total de garrafas vendidas foi " + calcularTotal(quantidade_total, dados_vendas) + ".");
            System.out.println("A percentagem de vendas por tipo de vinho foi:");
            System.out.println("-> Vinho Branco: " + String.format("%.2f", percentagemBranco(quantidade_branco, dados_vendas, t)) + "%");
            System.out.println("-> Vinho Tinto: " + String.format("%.2f", percentagemTinto(quantidade_tinto, dados_vendas, t)) + "%");
            if (s.equalsIgnoreCase("a")) {
                System.out.println("Não houve vendas de vinho tinto.");
            } else {
                System.out.println("O maior vendedor de tinto foi o " + s + " e vendeu " + maior + " garrafas.");
            }
            System.out.println("O vendedor que realizou a venda de maior valor foi o " + maior_vendedor + " no valor de " + k + "€.");
            System.out.println("A quantidade média de garrafas por venda foi " + String.format("%.1f", (t / (double) numero_vendas)) + " garrafas.");
            System.out.println("O valor médio por venda foi " + String.format("%.1f", (m / (double) numero_vendas)) + "€.");
            System.out.println("------------------------------------------------------------------------");

            //demonstração da matriz
            for (int linha = 0; linha < numero_vendas; linha++) {

                for (int coluna = 0; coluna < 4; coluna++) {

                    System.out.print(dados_vendas[linha][coluna] + " ");
                }
                System.out.println();
            }
        }
    }

    private static int codigoVendedor(String nome) {
        if (nome.equalsIgnoreCase("Artur")) {
            return 0;
        }
        if (nome.equalsIgnoreCase("Berto")) {
            return 1;
        }
        if (nome.equalsIgnoreCase("Carlos")) {
            return 2;
        }
        return -1;
    }

    private static int codigoVinho(String vinho) {
        if (vinho.equalsIgnoreCase("tinto")) {
            return 0;
        }
        if (vinho.equalsIgnoreCase("branco")) {
            return 1;
        }
        return -1;
    }

    private static int calcularTotal(int quantidade_total, double[][] dados_vendas) {
        for (int i = 0; i < numero_vendas; i++) {
            quantidade_total = quantidade_total + (int) dados_vendas[i][2];
        }
        return quantidade_total;
    }

    private static double percentagemTinto(int quantidade_tinto, double[][] dados_vendas, int t) {
        int quantidade_total_tinto = 0;
        for (int n = 0; n < numero_vendas; n++) {
            if (dados_vendas[n][1] == 0) {
                quantidade_tinto = (int) dados_vendas[n][2];
            } else {
                quantidade_tinto = 0;
            }
            quantidade_total_tinto = quantidade_total_tinto + quantidade_tinto;
        }
        double percTinto = (quantidade_total_tinto / (double) t) * 100;
        return percTinto;
    }

    private static double percentagemBranco(int quantidade_branco, double[][] dados_vendas, int t) {
        int quantidade_total_branco = 0;
        for (int n = 0; n < numero_vendas; n++) {
            if (dados_vendas[n][1] == 1) {
                quantidade_branco = (int) dados_vendas[n][2];
            } else {
                quantidade_branco = 0;
            }
            quantidade_total_branco = quantidade_total_branco + quantidade_branco;
        }
        double percBranco = (quantidade_total_branco / (double) t) * 100;
        return percBranco;
    }

    private static String maiorVendedorTinto(double[][] dados_vendas) {
        String nome_maior_vendedor = "a";
        int maior_quantidade_tinto;
        int maior_vendedor_tinto;
        for (int n = 0; n < numero_vendas; n++) {
            if (dados_vendas[n][1] == 0) {
                maior_quantidade_tinto = (int) dados_vendas[n][2];
                maior_vendedor_tinto = (int) dados_vendas[n][0];
                if (maior_quantidade_tinto > maior) {
                    nome_maior_vendedor = vendedores[maior_vendedor_tinto];
                    maior = maior + maior_quantidade_tinto;
                } else if (nome_maior_vendedor.equals(vendedores[maior_vendedor_tinto])) {
                    maior = maior + maior_quantidade_tinto;
                }
            }
        }
        return nome_maior_vendedor;
    }

    private static double vendaMaiorValor(double[][] dados_vendas) {
        double maior_valor = 0;
        int codigomaiorvendedor;
        for (int n = 0; n < numero_vendas; n++) {
            if (dados_vendas[n][3] > maior_valor) {
                maior_valor = dados_vendas[n][3];
                codigomaiorvendedor = (int) dados_vendas[n][0];
                maior_vendedor = vendedores[codigomaiorvendedor];
            }
        }
        return maior_valor;
    }

    private static double calcularTotalVenda(double venda_total, double[][] dados_vendas) {
        for (int i = 0; i < numero_vendas; i++) {
            venda_total = venda_total + dados_vendas[i][3];
        }
        return venda_total;
    }
}
