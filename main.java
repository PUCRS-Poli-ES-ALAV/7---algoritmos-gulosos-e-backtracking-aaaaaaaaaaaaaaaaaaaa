import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    private static void troco(Float valor,float[] moedas){
        int iterations = 0;
        //float[] moedas = {100, 25, 10, 5, 1};
        int[] quant_moedas = {0, 0, 0, 0, 0};
        for(int i = 0; i < moedas.length; i++){
            iterations++;
            while (valor >= moedas[i]) {
                quant_moedas[i]++;
                valor -= moedas[i];
            }
        }
        System.out.println("moedas");
        for(int i = 0; i < quant_moedas.length; i++){
            System.out.print(moedas[i] + " ");
            System.out.println(quant_moedas[i]);
        }
        System.out.println("operações (troco): " + iterations);
    }

    /// INTERVALO
    static class Intervalo {
        int inicio;
        int fim;

        Intervalo(int inicio, int fim) {
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        public String toString() {
            return "[" + inicio + "," + fim + "]";
        }
    }

    public static List<Intervalo> selecionarIntervalos(List<Intervalo> intervalos) {
        intervalos.sort((a, b) -> Integer.compare(a.fim, b.fim));

        List<Intervalo> selecionados = new ArrayList<>();
        int fimUltimo = Integer.MIN_VALUE;
        int iteracoes = 0;

        for (Intervalo intervalo : intervalos) {
            iteracoes++;
            if (intervalo.inicio > fimUltimo) {
                selecionados.add(intervalo);
                fimUltimo = intervalo.fim;
            }
        }

        System.out.println("Número de iterações (intervalos): " + iteracoes);
        return selecionados;
    }

    /// N-RAINHAS

    // Armazenam o estado do tabuleiro e soluções
    private static List<int[]> solutions;
    private static boolean[] cols;
    private static boolean[] diag1;
    private static boolean[] diag2;
    private static long queenIterations;  // contador de iterações nas N-rainhas

    private static void solveNQueens(int n) {
        solutions = new ArrayList<>();
        cols  = new boolean[n];
        diag1 = new boolean[2*n - 1];
        diag2 = new boolean[2*n - 1];
        queenIterations = 0;

        int[] sol = new int[n];
        backtrack(0, sol, n);

        System.out.printf("Foram encontradas %d soluções para n = %d%n", solutions.size(), n);
        System.out.printf("Total de iterações (N-rainhas): %d%n%n", queenIterations);

        for (int i = 0; i < solutions.size(); i++) {
            System.out.printf("Solução %d:%n", i + 1);
            printBoard(solutions.get(i));
        }
    }

    // Cada iteração tenta uma coluna nova: incrementamos queenIterations
    private static void backtrack(int row, int[] sol, int n) {
        if (row == n) {
            solutions.add(sol.clone());
            return;
        }
        for (int col = 0; col < n; col++) {
            queenIterations++;
            int d1 = row - col + (n - 1);
            int d2 = row + col;
            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                cols[col] = diag1[d1] = diag2[d2] = true;
                sol[row] = col;
                backtrack(row + 1, sol, n);
                cols[col] = diag1[d1] = diag2[d2] = false;
            }
        }
    }

    private static void printBoard(int[] sol) {
        int n = sol.length;
        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < n; col++) {
                sb.append(col == sol[row] ? 'Q' : '.');
                if (col < n - 1) sb.append(' ');
            }
            System.out.println(sb);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- TROCO ---
        System.out.print("Digite um valor para troco: ");
        Float valor = sc.nextFloat();
        float[] moedas = {100,25,10,5,1 };

        troco(valor,moedas);

         moedas = new float[]{100, 50, 25, 10, 1};
        troco(valor,moedas);

        System.out.println();

        // --- INTERVALO ---
        List<Intervalo> intervalos = new ArrayList<>();
        intervalos.add(new Intervalo(1, 4));
        intervalos.add(new Intervalo(3, 5));
        intervalos.add(new Intervalo(0, 6));
        intervalos.add(new Intervalo(5, 7));
        intervalos.add(new Intervalo(3, 9));
        intervalos.add(new Intervalo(5, 9));
        intervalos.add(new Intervalo(6, 10));
        intervalos.add(new Intervalo(8, 11));
        intervalos.add(new Intervalo(8, 12));
        intervalos.add(new Intervalo(2, 14));
        intervalos.add(new Intervalo(12, 16));

        System.out.println("Intervalos selecionados:");
        List<Intervalo> resultado = selecionarIntervalos(intervalos);
        for (Intervalo i : resultado) {
            System.out.println(i);
        }
        System.out.println();

        // --- N-RAINHAS ---
        System.out.print("Digite o valor de n para as N-rainhas (n ≥ 2): ");
        int n = sc.nextInt();
        if (n < 2) {
            System.out.println("n deve ser pelo menos 2.");
        } else {
            solveNQueens(n);
        }

        sc.close();
    }

}
