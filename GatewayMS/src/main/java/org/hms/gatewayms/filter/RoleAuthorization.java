package org.hms.gatewayms.filter;

import java.util.Set;

public final class RoleAuthorization {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String HEADER_USER_ROLE = "X-User-Role";
    public static final String HEADER_USER_ID = "X-User-Id";

    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/user/login",
            "/user/register"
    );

    private static final Set<String> ADMIN_ONLY_PATHS = Set.of(
            "/user/registerAdmin",
            "/user/registerDoctor",
            "/user/getRegistrationCounts",
            "/profile/doctor/add",
            "/profile/doctor/getAll",
            "/profile/patient/getAll",
            "/appointment/visitCount",
            "/appointment/countReasons",
            "/appointment/report/getAllPrescriptions"
    );

    private RoleAuthorization() {
    }

    public static boolean isPublicPath(String path) {
        return PUBLIC_PATHS.contains(path);
    }

    public static boolean isAdminOnlyPath(String path) {
        return ADMIN_ONLY_PATHS.contains(path);
    }
}
