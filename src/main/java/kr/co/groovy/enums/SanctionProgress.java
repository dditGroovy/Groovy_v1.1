package kr.co.groovy.enums;

public enum SanctionProgress {
    // 승인 반려 보류 회수 상신
    // CONSENT("승인"), RETURN("반려"), RESERVATION, RETIREVAL, REPORT
    SANCTN010("상신"),
    SANCTN011("회수"),
    SANCTN012("대기"),
    SANCTN013("예정"),
    SANCTN014("반려"),
    SANCTN015("승인");

    private final String label;

    SanctionProgress(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }


}
