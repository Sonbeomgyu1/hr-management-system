package com.apple.shop.Attendance;


public enum AttendanceType {
    WORK("정상 근무", "#4CAF50"),       // 초록
    VACATION("휴가", "#2196F3"),        // 파랑
    SICK("병가", "#FF9800"),            // 주황
    DISPATCH("파견", "#9C27B0"),        // 보라
    EARLY_LEAVE("조퇴", "#FFC107"),     // 노랑
    ABSENCE("무단결근", "#F44336");      // 빨강

    private final String label;
    private final String color;

    AttendanceType(String label, String color) {
        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public String getColor() {
        return color;
    }
}