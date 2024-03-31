import org.opencv.core.Core;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);

        int a = 0;

        while (true) {
            a++;
            System.out.println(a);
            if (a == 3) {
                break;
            }
        }

        System.out.println("dima");
    }
}