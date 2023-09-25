package ru.job4j.ood.lsp.secondrule;

public class SecondRule {
    public static void main(String[] args) throws Exception {
        Plane airbus20 = new SpecialPlane(80, 1300.0);
        airbus20.transportate();
    }
}
