package org.coderdojo.rest.game;

public class GameLogic {
    private static int[] MOVE_ORDER = {4, 0, 2, 6, 8, 1, 3, 5, 7};

    private GameLogic() {

    }

    /**
     * @param stare
     * @return -1 if table is full, the index of the computer move otherwise
     */
    public static int nextPosition(Pozitie[] stare) {
        for (int i = 0; i < 9; i++) {
            if (stare[MOVE_ORDER[i]] == Pozitie.GOL) {
                return MOVE_ORDER[i];
            }
        }

        return -1;
    }

    public static int alternativeNextPosition(Pozitie[] stare) {
        // pot sa castig?
        int i = isWinningMove(stare, Pozitie.O);

        // pot sa pierd?
        if (i == -1) {
            i = isWinningMove(stare, Pozitie.X);
        }

        if (i == -1) {
            i = stare[4] == Pozitie.GOL ? 4 : -1;
        }

        if (i == -1) {
            i = stare[4] == Pozitie.GOL ? 1 : -1;
        }

        if (i == -1) {
            i = stare[4] == Pozitie.GOL ? 3 : -1;
        }

        if (i == -1) {
            i = stare[4] == Pozitie.GOL ? 5 : -1;
        }

        if (i == -1) {
            i = stare[4] == Pozitie.GOL ? 7 : -1;
        }

        return i;
    }

    private static boolean isFull(Pozitie[] stare) {
        for (int i = 0; i < 9; i++) {
            if (stare[i] == Pozitie.GOL) {
                return false;
            }
        }

        return true;
    }

    private static int isWinningMove(Pozitie[] stare, Pozitie tip) {
        // prima linie
        int i = twoInARow(stare, tip, 0, 1, 2);

        // a doua linie
        if (i == -1) {
            i = twoInARow(stare, tip, 3, 4, 5);
        }

        // a treia linie
        if (i == -1) {
            i = twoInARow(stare, tip, 6, 7, 8);
        }

        // prima coloana
        if (i == -1) {
            i = twoInARow(stare, tip, 0, 3, 6);
        }

        // a doua coloana
        if (i == -1) {
            i = twoInARow(stare, tip, 1, 4, 7);
        }

        // a treia coloana
        if (i == -1) {
            i = twoInARow(stare, tip, 2, 5, 8);
        }

        // o diagonala
        if (i == -1) {
            i = twoInARow(stare, tip, 0, 4, 8);
        }

        // cealalta diagonala
        if (i == -1) {
            i = twoInARow(stare, tip, 2, 4, 6);
        }

        return i;
    }

    private static int twoInARow(Pozitie[] stare, Pozitie tip, int p1, int p2, int p3) {
        if (stare[p1] == tip && stare[p2] == tip && stare[p3] == Pozitie.GOL) {
            return p3;
        }

        if (stare[p1] == tip && stare[p2] == Pozitie.GOL && stare[p3] == tip) {
            return p2;
        }

        if (stare[p1] == Pozitie.GOL && stare[p2] == tip && stare[p3] == tip) {
            return p1;
        }

        return -1;
    }
}
