public class main {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.knotenHinzufuegen(new Datenelement("A", 0));
        g.knotenHinzufuegen(new Datenelement("B", 0));
        g.knotenHinzufuegen(new Datenelement("C", 0));
        g.kanteHinzufuegen(1, 3, 5);
        g.kanteHinzufuegen(2, 1, 10);

        g.matrixAusgeben();
    }
}
