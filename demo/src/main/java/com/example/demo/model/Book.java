package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotBlank(message = "Tiêu đề không được để trống")
    private String title;
	@NotBlank(message = "Tác giả không được để trống")
    private String author;
	@NotBlank(message = "Ngày xuất bản không được để trống")
    private Date published_day;
	@NotBlank(message = "Số lượng không được để trống")
    private int total_quanlity;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublished_day() {
		return published_day;
	}
	public void setPublished_day(Date published_day) {
		this.published_day = published_day;
	}
	public int getTotal_quanlity() {
		return total_quanlity;
	}
	public void setTotal_quanlity(int total_quanlity) {
		this.total_quanlity = total_quanlity;
	}
}
