package designPattern.abstractFactory;

public class DataAccess {
    private static String db = "sqlServer";

    public static IUser createUser() {
        IUser result = null;
        switch(db) {
            case "sqlServer":
                result = new SqlServerUser();
                break;
            case "Access":
                result = new AccessUser();
                break;
        }
        return result;
    }

    public static IDepartment createDepartment() {
        IDepartment result = null;
        switch(db) {
            case "sqlServer":
                result = new SqlServerDepartment();
                break;
            case "Access":
                result = new AccessDepartment();
                break;
        }
        return result;
    }
}
