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
            if (start > knotenfeld.length || ziel > knotenfeld.length)
                System.out.println("Ungültige Operation: Hinzufügen einer Kante zwischen nicht existenten Knoten");
            else
                adjazenzmatrix[start][ziel] = gewichtung;
    }

    public void tiefensuche(int StartKnoten) {
        // Besucht-Status der Knoten zurücksetzen
        for (int i = 0; i < anzknoten; i++) {
            knotenfeld[i].besucht = false;
        }

        tiefensucheDurchfuehren(StartKnoten);
    }

    private void tiefensucheDurchfuehren(int knotenNr) {
        knotenfeld[knotenNr].besucht = true;
        System.out.println(knotenfeld[knotenNr].daten.gibOrtsname());

        for (int i = 0; i < anzknoten; i++) {
            if(adjazenzmatrix[knotenNr][i] > 0) {
                if(!knotenfeld[i].besucht) {
                    tiefensucheDurchfuehren(i);
                }
            }
        }
    }

    public void matrixAusgeben() {
        System.out.print("\033[1;94m"); // Blue, Bold

        // Get max oName length
        int maxNameLaenge = 0;
        for (int i = 0; i < knotenfeld.length; i++) {
            if (knotenfeld[i] == null)
                break;
            
            String n = knotenfeld[i].daten.gibOrtsname();
            if (n.length() > maxNameLaenge)
                maxNameLaenge = n.length();
        }

        // Spacing above row labels
        for (int j = 0; j < maxNameLaenge; j++)
            System.out.print(" ");

        // collum labels
        for (Knoten k : knotenfeld) {
            if(k != null)
                System.out.print("\t" + k.daten.gibOrtsname());
            else
            System.out.print("\t-"); // null name label
        }

        System.out.print("\033[0m"); // Reset formatting

        // rows
        for (int i = 0; i < adjazenzmatrix.length; i++) {

            // row labels
            System.out.print("\n\033[1;94m");  // Blue, Bold

            if(knotenfeld[i] != null) {
                String oName = knotenfeld[i].daten.gibOrtsname();
                System.out.print(oName);

                // Spacing next to row labels
                for (int j = 0; j < maxNameLaenge - oName.length() + 3; j++)
                    System.out.print(" ");
            }
            else {
                System.out.print("-"); // null name label

                // Spacing next to row labels
                for (int j = 0; j < maxNameLaenge - 1 + 3; j++)
                    System.out.print(" ");
            }
            
            System.out.print("\033[0m"); // Reset formatting

            // Adjazentmatrix
            for (int e : adjazenzmatrix[i]) {
                if(e > -1)
                    System.out.print("\t " + e);
                else
                    System.out.print("\t" + e);
            }
        }

        System.out.print("\n");
    }
}