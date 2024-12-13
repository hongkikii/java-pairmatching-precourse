package pairmatching;

import java.util.EnumSet;
import java.util.Optional;

public enum Course {
    BACKEND("백엔드", "backend-crew.md"),
    FRONTEND("프론트엔드", "frontend-crew.md");

    private final String name;
    private final String fileName;

    Course(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public static boolean isNotContained(String name) {
        Optional<Course> matchedCourse = EnumSet.allOf(Course.class).stream()
                .filter(course -> course.name.equals(name))
                .findFirst();
        if(matchedCourse.isPresent()) {
            return false;
        }
        return true;
    }

    public static Course getBy(String name) {
        return EnumSet.allOf(Course.class).stream()
                .filter(course -> course.name.equals(name))
                .findFirst()
                .orElseThrow();
    }

    public String getFileName() {
        return fileName;
    }
}

