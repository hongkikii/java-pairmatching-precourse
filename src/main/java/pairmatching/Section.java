package pairmatching;

public class Section {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public Section(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Level getLevel() {
        return level;
    }

    public Course getCourse() {
        return course;
    }

    public boolean isEqual(Section section) {
        return course.equals(section.course) && mission.equals(section.mission);
    }
}
