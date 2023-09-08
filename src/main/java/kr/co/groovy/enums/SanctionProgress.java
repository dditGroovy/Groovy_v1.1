package kr.co.groovy.enums;

public enum SanctionProgress {
    // 승인 반려 보류 회수 상신
    // CONSENT("승인"), RETURN("반려"), RESERVATION, RETIREVAL, REPORT
    SANCTN010("승인"),
    SANCTN011("반려"),
    SANCTN012("보류"),
    SANCTN013("회수"),
    SANCTN014("상신"),
    SANCTN015("대기"),
    SANCTN016("예정");

    private final String label;

    SanctionProgress(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }


}
