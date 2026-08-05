package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "enrollments")
public class Enrollment {

    @Id
    private String id;
    private String userId;
    private String courseId;
    private String status;
    private String studentEmail;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(
            String studentEmail) {

        this.studentEmail =
                studentEmail;
    }


    // getters setters
}