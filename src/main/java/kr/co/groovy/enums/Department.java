package kr.co.groovy.enums;

public enum Department {
    DEPT010("인사"), DEPT011("회계"), DEPT012("영업"), DEPT013("홍보"), DEPT014("총무"), DEPT015("경영자");
    private final String label;

    Department(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
