package kr.co.groovy.enums;

public enum SactionProgress {
    // 승인 반려 보류 회수 상신
    // CONSENT("승인"), RETURN("반려"), RESERVATION, RETIREVAL, REPORT
    SANCTN010("승인"),
    SANCTN011("반려"),
    SANCTN012("보류"),
    SANCTN013("회수"),
    SANCTN014("상신");

    private final String label;

    SactionProgress(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }


}
