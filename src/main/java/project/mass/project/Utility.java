package project.mass.project;

import java.util.List;

public class Utility {

    public static Boolean validateString(String str) {
        return str != null && !str.isEmpty() && !str.isBlank();
    }

    public static boolean hasNotNull(List<Object> listOfObjects) {
        if (listOfObjects == null) {
            return false;
        }
        for (Object o : listOfObjects) {
            if (o != null) {
                return true;
            }
        }
        return false;
    }
}
