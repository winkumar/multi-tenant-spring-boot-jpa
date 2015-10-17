package demo;

public class CurrentTenantId {
    private static ThreadLocal<String> threadLocalTenantId = new ThreadLocal<String>();
    
    public static String get() {
        return threadLocalTenantId.get();
    }
    
    public static void set(String tenantId) {
        threadLocalTenantId.set(tenantId);
    }
    
    public static void clear() {
        threadLocalTenantId.remove();
    }

}
