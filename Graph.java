public class Graph {
    private Knoten[] knotenfeld;
    private int[][] adjazenzmatrix;
    private int anzknoten = 0;

    public Graph(int maxAnzKnoten) {
        adjazenzmatrix = new int[maxAnzKnoten][maxAnzKnoten];
        knotenfeld = new Knoten[maxAnzKnoten];

        for (int i = 0; i < maxAnzKnoten; i++) {
            for (int j = 0; j < maxAnzKnoten; j++) {
                if(i == j) {
                    adjazenzmatrix[i][j] = 0;
                }
                else {
                    adjazenzmatrix[i][j] = -1;
                }
            }
        }
    }

    /**
     * fügt Inhalt auf den ersten freien Platz in knotenfeld ein
     * @param inhalt
     * @return Index, an welcher Stelle das Datenelement engefügt wurde
     */
    public int knotenHinzufuegen(Datenelement inhalt) {
        if(anzknoten + 1 >= adjazenzmatrix[0].length) {
            System.out.println("Die Matrix hat bereits ihre volle Größe erreicht. Der Knoten kann nicht eingefügt werden!");
            return -1;
        } 
        else {
            knotenfeld[anzknoten] = new Knoten(inhalt);
            anzknoten = anzknoten + 1;
            return anzknoten - 1;
        }
    }

    /**
     * weist dem Feldelement mit den Indezies start und ziel gewichtung zu
     */
    public void kanteHinzufuegen(int start, int ziel, int gewichtung) {
        if(gewichtung < 1)
            System.out.println("Ungültige Gewichtung " + gewichtung + "!");
        else
            adjazenzmatrix[start][ziel] = gewichtung;
    }

    public void matrixAusgeben() {
        System.out.print("\033[1;94m"); // Blue, Bold

        for (Knoten k : knotenfeld) {
            if(k != null)
                System.out.print("\t" + k.daten.gibOrtsname());
            else
            System.out.print("\t-");
        }

        System.out.print("\033[0m"); // Reset formatting

        for (int i = 0; i < adjazenzmatrix.length; i++) {
            System.out.print("\n\033[1;94m");  // Blue, Bold

            if(knotenfeld[i] != null)
                System.out.print(knotenfeld[i].daten.gibOrtsname());
            else
                System.out.print("-");
            
            System.out.print("\033[0m"); // Reset formatting

            for (int e : adjazenzmatrix[i]) {
                if(e > -1)
                    System.out.print("\t " + e);
                else
                    System.out.print("\t" + e);
            }
        }
    }
}