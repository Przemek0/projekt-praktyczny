package pl.sdacademy.javafxui.examples;

public class MyClass {
    private String text;

    public MyClass() {
        this.text = "Obiekt stworzony poprawnie";
    }

    //tworzymy statyczną metodę, która zwraca kontruktor do siebie samej
    public static MyClass getInstance() {
        return new MyClass();
    }

    public String getText() {
        return text;
    }
}
