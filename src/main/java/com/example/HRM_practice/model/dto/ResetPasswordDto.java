package com.example.HRM_practice.model.dto;

public class ResetPasswordDto {
    private Integer userId;

    private String oldPassword;

    private String newPassword;

    private String newPasswordCheck;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{userId:").append(userId);
        sb.append(", oldPassword:'").append(oldPassword).append('\'');
        sb.append(", newPassword:'").append(newPassword).append('\'');
        sb.append(", newPasswordCheck:'").append(newPasswordCheck).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
