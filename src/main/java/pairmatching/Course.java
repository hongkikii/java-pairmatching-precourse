package pairmatching;

import java.util.EnumSet;
import java.util.Optional;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
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
}

