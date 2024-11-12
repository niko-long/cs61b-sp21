package flik;

public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        int j = 0;
        while(i < 500){
            if (!Flik.isSameNumber(i, j)) {
                String.format("i:%d not same as j:%d ??", i, j);
                return;
            }
            i = i + 1;
            j = j + 1;
        }
        System.out.println("i is " + i);
    }
}
