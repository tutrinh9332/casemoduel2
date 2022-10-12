package Models;

public enum RoleType {
    ADMIN("admin"), USER("user");
    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
