package ru.job4j.ood.lsp.firstrule;

public class FirstRule {
    public static void main(String[] args) {
        Plane airbus20 = new SpecialPlane(80, 1300.0);
        airbus20.transportate();
    }
}
