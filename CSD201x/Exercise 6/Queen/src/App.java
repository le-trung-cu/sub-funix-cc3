import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        int n = 5;
        App app = new App(n);
        app.datQuanHauThuK(0, 0);
    }

    private int[] result;
    private int[] dong;
    private int[] cheo_TL_BR;
    private int[] cheo_TR_BL;
    private int n;
    private int num = 0;

    public App(int n) {
        this.n = n;
        result = new int[n];
        dong = new int[n];
        cheo_TL_BR = new int[2 * n - 3];
        cheo_TR_BL = new int[2 * n - 3];

        for (int j = 0; j < n; j++) {
            result[j] = -1;
        }
    }

    public void datQuanHauThuK(int k, int initRow) {

        for (int r = initRow; r < n; r++) {
            if (kiemTraCoTheDatQuanKTrenDongR(k, r)) {
                result[k] = r;
                if (!(k == 0 && r == 0) && !(k == n - 1 && r == n - 1)) cheo_TL_BR[k + r - 1] = 1;

                if (!(k == n - 1 && r == 0) && !(k == 0 && r == n - 1)) cheo_TR_BL[k - r + n - 2] = 1;

                dong[r] = 1;
                if (k == n - 1) {
                    System.out.println(++num + " " + Arrays.toString(result));
                    nhacQuanHauThuK(k);
                    int row = nhacQuanHauThuK(k - 1);
                    datQuanHauThuK(k - 1, row + 1);
                    return;
                }
                datQuanHauThuK(k + 1, 0);
                return;
            }
        }
        if (k == 0) return;

        int row = nhacQuanHauThuK(k - 1);
        if (k - 1 == 0 && row == n - 1) return;

        datQuanHauThuK(k - 1, row + 1);
    }

    public int nhacQuanHauThuK(int k) {

        int r = result[k];
        result[k] = -1;
        dong[r] = 0;
        if (!(k == 0 && r == 0) && !(k == n - 1 && r == n - 1)) {
            cheo_TL_BR[k + r - 1] = 0;
        }
        if (!(k == n - 1 && r == 0) && !(k == 0 && r == n - 1)) {
            cheo_TR_BL[k - r + n - 2] = 0;
        }

        return r;
    }

    public boolean kiemTraCoTheDatQuanKTrenDongR(int k, int r) {
        // kiểm tra trên dòng
        if (dong[r] == 1) {
            return false;
        }

        if (!(k == 0 && r == 0) && !(k == n - 1 && r == n - 1)) {
            if (cheo_TL_BR[k + r - 1] == 1) {
                return false;
            }
        }
        if (!(k == n - 1 && r == 0) && !(k == 0 && r == n - 1)) {
            if (cheo_TR_BL[k - r + n - 2] == 1) {
                return false;
            }
        }
        return true;

    }

}
